"""
    lakeFS API

    lakeFS HTTP API  # noqa: E501

    The version of the OpenAPI document: 0.1.0
    Contact: services@treeverse.io
    Generated by: https://openapi-generator.tech
"""


import unittest

import lakefs_client
from lakefs_client.api.internal_api import InternalApi  # noqa: E501


class TestInternalApi(unittest.TestCase):
    """InternalApi unit test stubs"""

    def setUp(self):
        self.api = InternalApi()  # noqa: E501

    def tearDown(self):
        pass

    def test_create_branch_protection_rule_preflight(self):
        """Test case for create_branch_protection_rule_preflight

        """
        pass

    def test_get_setup_state(self):
        """Test case for get_setup_state

        check if the lakeFS installation is already set up  # noqa: E501
        """
        pass

    def test_post_stats_events(self):
        """Test case for post_stats_events

        post stats events, this endpoint is meant for internal use only  # noqa: E501
        """
        pass

    def test_set_garbage_collection_rules_preflight(self):
        """Test case for set_garbage_collection_rules_preflight

        """
        pass

    def test_setup(self):
        """Test case for setup

        setup lakeFS and create a first user  # noqa: E501
        """
        pass

    def test_setup_comm_prefs(self):
        """Test case for setup_comm_prefs

        setup communications preferences  # noqa: E501
        """
        pass

    def test_update_branch_token(self):
        """Test case for update_branch_token

        modify branch staging token  # noqa: E501
        """
        pass

    def test_upload_object_preflight(self):
        """Test case for upload_object_preflight

        """
        pass


if __name__ == '__main__':
    unittest.main()
