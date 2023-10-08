from pydantic import BaseModel, Field
from enum import Enum


class RoleEnum(str, Enum):
    MANAGER = "manager"
    EMPLOYEE = "employee"
    HUMAN_RESOURCES = "human_resources"


class User(BaseModel):
    name: str = Field(..., examples=["John Doe", "Jane Doe"])
    email: str = Field(..., examples=["example@gmail.com"])
    password: str = Field(..., examples=["GHNRFASFEIOFJ"])
    role: RoleEnum = Field(
        ..., examples=[RoleEnum.EMPLOYEE, RoleEnum.MANAGER, RoleEnum.HUMAN_RESOURCES]
    )