import lakefs


def test_reference_log(setup_branch_with_commits):
    branch = setup_branch_with_commits

    for i, c in enumerate(branch.log(max_amount=199)):
        assert c.message == f"commit {i}"

    commits = list(branch.log(max_amount=2000))
    for i, c in enumerate(commits[:-1]):  # Ignore initial commit
        assert c.message == f"commit {i}"
    assert len(commits) == 200

    assert len(list(branch.log(limit=True, amount=10, max_amount=100))) == 10


def test_reference_diff(setup_branch_with_commits):
    branch = setup_branch_with_commits

    commits = list(branch.log(max_amount=2))
    assert len(list(branch.diff(branch.commit_id()))) == 0
    changes = list(branch.diff(commits[0].id, type="two_dot"))
    assert len(changes) == 0

    changes = list(branch.diff(commits[1].id, type="two_dot"))
    assert len(changes) == 1
    assert changes[0].path == "test1"
    assert changes[0].type == "removed"

    other_branch = lakefs.Repository(branch.repo_id).branch("other_branch").create("test_branch")
    other_branch.object("prefix1/test1").upload(data="data1")
    other_branch.object("prefix2/test2").upload(data="data2")
    other_branch.commit("other commit")

    changes = list(branch.diff(other_branch))
    assert len(changes) == 2

    changes = list(branch.diff(other_branch, prefix="prefix2"))
    assert len(changes) == 1
    assert changes[0].path == "prefix2/test2"
    assert changes[0].type == "added"


def test_reference_merge_into(setup_branch_with_commits):
    branch = setup_branch_with_commits
    repo = lakefs.Repository(branch.repo_id)
    main = repo.branch("main")

    commits = list(branch.log(max_amount=2))
    other_branch = repo.branch("test_reference_merge_into").create(main)
    ref = repo.ref(commits[1].id)
    ref.merge_into(other_branch, message="Merge1")
    assert other_branch.commit_message() == "Merge1"
    assert list(other_branch.log(max_amount=2))[1].id == commits[1].id

    branch.merge_into(other_branch.id, message="Merge2")
    assert other_branch.commit_message() == "Merge2"
    assert list(other_branch.log(max_amount=3))[2].id == commits[0].id
