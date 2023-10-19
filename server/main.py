import domain.articles as articles
from docs.employee import articles_
from domain.users import User, upsert_user
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from models.article import Article

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


@app.post("/article")
async def upsert_article(article: Article):
    result = await articles.upsert(article)
    return {"message": f"Article upserted successfully", "result": f"{result}"}


@app.get("/populate-articles")
async def populate_articles() -> str:
    for article in articles_:
        await articles.upsert(article)
    return "Articles populated"


@app.get("/ping")
async def ping() -> str:
    return "pong"
