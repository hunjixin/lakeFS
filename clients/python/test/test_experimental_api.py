# coding: utf-8

"""
    lakeFS API

    lakeFS HTTP API

    The version of the OpenAPI document: 1.0.0
    Contact: services@treeverse.io
    Generated by OpenAPI Generator (https://openapi-generator.tech)

    Do not edit the class manually.
"""  # noqa: E501


import unittest

import lakefs_sdk
from lakefs_sdk.api.experimental_api import ExperimentalApi  # noqa: E501
from lakefs_sdk.rest import ApiException


class TestExperimentalApi(unittest.TestCase):
    """ExperimentalApi unit test stubs"""

    def setUp(self):
        self.api = lakefs_sdk.api.experimental_api.ExperimentalApi()  # noqa: E501

    def tearDown(self):
        pass

    def test_get_otf_diffs(self):
        """Test case for get_otf_diffs

        get the available Open Table Format diffs  # noqa: E501
        """
        pass

    def test_otf_diff(self):
        """Test case for otf_diff

        perform otf diff  # noqa: E501
        """
        pass


if __name__ == '__main__':
    unittest.main()
