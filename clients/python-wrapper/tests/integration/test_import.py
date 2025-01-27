from time import sleep

from lakefs.exceptions import ImportManagerException, ConflictException
from tests.utests.common import expect_exception_context

_IMPORT_PATH = "s3://esti-system-testing-data/import-test-data/"

_FILES_TO_CHECK = ["nested/prefix-1/file002005",
                   "nested/prefix-2/file001894",
                   "nested/prefix-3/file000005",
                   "nested/prefix-4/file000645",
                   "nested/prefix-5/file001566",
                   "nested/prefix-6/file002011",
                   "nested/prefix-7/file000101", ]


def test_import_manager(setup_repo):
    _, repo = setup_repo
    branch = repo.branch("import-branch").create("main")
    mgr = branch.import_data(commit_message="my imported data", metadata={"foo": "bar"})

    #  No import running
    with expect_exception_context(ImportManagerException):
        mgr.cancel()

    # empty import
    res = mgr.run()
    assert res.error is None
    assert res.completed
    assert res.commit.id == branch.commit_id()
    assert res.commit.message == "my imported data"
    assert res.commit.metadata.get("foo") == "bar"
    assert res.ingested_objects == 0

    # Import with objects and prefixes
    dest_prefix = "imported/new-prefix/"
    mgr.prefix(_IMPORT_PATH + "prefix-1/",
               dest_prefix + "prefix-1/").prefix(_IMPORT_PATH + "prefix-2/",
                                                 dest_prefix + "prefix-2/")
    for o in _FILES_TO_CHECK:
        mgr.object(_IMPORT_PATH + o, dest_prefix + o)

    mgr.commit_message = "new commit"
    mgr.commit_metadata = None
    res = mgr.run()
    assert res.error is None
    assert res.completed
    assert res.commit.id == branch.commit_id()
    assert res.commit.message == mgr.commit_message
    assert res.commit.metadata.get("foo") is None
    assert res.ingested_objects == 4207

    # Conflict since import completed
    with expect_exception_context(ConflictException):
        mgr.cancel()


def test_import_manager_cancel(setup_repo):
    _, repo = setup_repo
    branch = repo.branch("import-branch").create("main")
    expected_commit_id = branch.commit_id()
    expected_commit_message = branch.commit_message()

    mgr = branch.import_data("my imported data", metadata={"foo": "bar"})
    mgr.prefix(_IMPORT_PATH, "import/")

    mgr.start()
    sleep(1)
    mgr.cancel()

    status = mgr.status()
    assert branch.commit_id() == expected_commit_id
    assert branch.commit_message() == expected_commit_message
    assert not status.completed
    assert "Canceled" in status.error.message
    assert len(mgr.sources) == 0
