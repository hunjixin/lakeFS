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


from typing import List, Optional
from pydantic import BaseModel, Field, StrictStr, conlist, validator
from lakefs_sdk.models.otf_diff_entry import OtfDiffEntry

class OtfDiffList(BaseModel):
    """
    OtfDiffList
    """
    diff_type: Optional[StrictStr] = None
    results: conlist(OtfDiffEntry) = Field(...)
    __properties = ["diff_type", "results"]

    @validator('diff_type')
    def diff_type_validate_enum(cls, value):
        """Validates the enum"""
        if value is None:
            return value

        if value not in ('created', 'dropped', 'changed'):
            raise ValueError("must be one of enum values ('created', 'dropped', 'changed')")
        return value

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
    def from_json(cls, json_str: str) -> OtfDiffList:
        """Create an instance of OtfDiffList from a JSON string"""
        return cls.from_dict(json.loads(json_str))

    def to_dict(self):
        """Returns the dictionary representation of the model using alias"""
        _dict = self.dict(by_alias=True,
                          exclude={
                          },
                          exclude_none=True)
        # override the default output from pydantic by calling `to_dict()` of each item in results (list)
        _items = []
        if self.results:
            for _item in self.results:
                if _item:
                    _items.append(_item.to_dict())
            _dict['results'] = _items
        return _dict

    @classmethod
    def from_dict(cls, obj: dict) -> OtfDiffList:
        """Create an instance of OtfDiffList from a dict"""
        if obj is None:
            return None

        if not isinstance(obj, dict):
            return OtfDiffList.parse_obj(obj)

        _obj = OtfDiffList.parse_obj({
            "diff_type": obj.get("diff_type"),
            "results": [OtfDiffEntry.from_dict(_item) for _item in obj.get("results")] if obj.get("results") is not None else None
        })
        return _obj


