import motor.motor_asyncio
from dotenv import dotenv_values
from models.user import User

config = dotenv_values(".env")
client = motor.motor_asyncio.AsyncIOMotorClient(config["MONGOURI"])
db = client.lotus
collection = db.user


async def upsert_user(user: User) -> dict:
    raw_user = user.model_dump()
    result = await collection.find_one_and_update(
        raw_user,
        {"$set": raw_user},
        upsert=True,
        return_document=True,
    )
    return result
