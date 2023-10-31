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



from pydantic import BaseModel, Field, StrictInt, StrictStr

class StatsEvent(BaseModel):
    """
    StatsEvent
    """
    var_class: StrictStr = Field(..., alias="class", description="stats event class (e.g. \"s3_gateway\", \"openapi_request\", \"experimental-feature\", \"ui-event\")")
    name: StrictStr = Field(..., description="stats event name (e.g. \"put_object\", \"create_repository\", \"<experimental-feature-name>\")")
    count: StrictInt = Field(..., description="number of events of the class and name")
    __properties = ["class", "name", "count"]

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
    def from_json(cls, json_str: str) -> StatsEvent:
        """Create an instance of StatsEvent from a JSON string"""
        return cls.from_dict(json.loads(json_str))

    def to_dict(self):
        """Returns the dictionary representation of the model using alias"""
        _dict = self.dict(by_alias=True,
                          exclude={
                          },
                          exclude_none=True)
        return _dict

    @classmethod
    def from_dict(cls, obj: dict) -> StatsEvent:
        """Create an instance of StatsEvent from a dict"""
        if obj is None:
            return None

        if not isinstance(obj, dict):
            return StatsEvent.parse_obj(obj)

        _obj = StatsEvent.parse_obj({
            "var_class": obj.get("class"),
            "name": obj.get("name"),
            "count": obj.get("count")
        })
        return _obj


