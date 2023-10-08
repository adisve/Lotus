from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from domain.users import upsert_user, User

app = FastAPI()

# CORS
origins = [
    "http://localhost:8000",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.post("/user")
async def upsert_user_(user: User) -> dict:
    result = await upsert_user(user)
    return {"message": f"Question upserted successfully", "result": f"{result}"}


@app.get("/ping")
async def ping() -> str:
    return "pong"
