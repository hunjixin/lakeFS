# coding: utf-8

"""
    lakeFS API

    lakeFS HTTP API

    The version of the OpenAPI document: 1.0.0
    Contact: services@treeverse.io
    Generated by OpenAPI Generator (https://openapi-generator.tech)

    Do not edit the class manually.
"""  # noqa: E501


from __future__ import annotations
import pprint
import re  # noqa: F401
import json


from typing import Optional
from pydantic import BaseModel, Field, StrictBool, StrictStr

class StorageConfig(BaseModel):
    """
    StorageConfig
    """
    blockstore_type: StrictStr = Field(...)
    blockstore_namespace_example: StrictStr = Field(...)
    blockstore_namespace_validity_regex: StrictStr = Field(..., alias="blockstore_namespace_ValidityRegex")
    default_namespace_prefix: Optional[StrictStr] = None
    pre_sign_support: StrictBool = Field(...)
    pre_sign_support_ui: StrictBool = Field(...)
    import_support: StrictBool = Field(...)
    import_validity_regex: StrictStr = Field(...)
    __properties = ["blockstore_type", "blockstore_namespace_example", "blockstore_namespace_ValidityRegex", "default_namespace_prefix", "pre_sign_support", "pre_sign_support_ui", "import_support", "import_validity_regex"]

    class Config:
        """Pydantic configuration"""
        allow_population_by_field_name = True
        validate_assignment = True

    def to_str(self) -> str:
        """Returns the string representation of the model using alias"""
        return pprint.pformat(self.dict(by_alias=True))

    def to_json(self) -> str:
        """Returns the JSON representation of the model using alias"""
        return json.dumps(self.to_dict())

    @classmethod
    def from_json(cls, json_str: str) -> StorageConfig:
        """Create an instance of StorageConfig from a JSON string"""
        return cls.from_dict(json.loads(json_str))

    def to_dict(self):
        """Returns the dictionary representation of the model using alias"""
        _dict = self.dict(by_alias=True,
                          exclude={
                          },
                          exclude_none=True)
        return _dict

    @classmethod
    def from_dict(cls, obj: dict) -> StorageConfig:
        """Create an instance of StorageConfig from a dict"""
        if obj is None:
            return None

        if not isinstance(obj, dict):
            return StorageConfig.parse_obj(obj)

        _obj = StorageConfig.parse_obj({
            "blockstore_type": obj.get("blockstore_type"),
            "blockstore_namespace_example": obj.get("blockstore_namespace_example"),
            "blockstore_namespace_validity_regex": obj.get("blockstore_namespace_ValidityRegex"),
            "default_namespace_prefix": obj.get("default_namespace_prefix"),
            "pre_sign_support": obj.get("pre_sign_support"),
            "pre_sign_support_ui": obj.get("pre_sign_support_ui"),
            "import_support": obj.get("import_support"),
            "import_validity_regex": obj.get("import_validity_regex")
        })
        return _obj


