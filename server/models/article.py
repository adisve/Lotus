from pydantic import BaseModel, Field
from models.user import RoleEnum


class Article(BaseModel):
    title: str = Field(..., examples=["Article title"])
    role: RoleEnum = Field(
        ..., examples=[RoleEnum.EMPLOYEE, RoleEnum.MANAGER]
    )
    content: str = Field(..., examples=["Article content"])
