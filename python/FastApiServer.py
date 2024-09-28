from fastapi import FastAPI
from fastapi.responses import StreamingResponse
from tenacity import sleep

from constants import OPEN_AI_API_KEY
import os

os.environ["OPENAI_API_KEY"] = OPEN_AI_API_KEY

app = FastAPI()
print(os.getenv("OPENAI_API_KEY"))


@app.get("/session")
def get_session_id():
    return {"sessionId": "123"}


async def fake_streamer():
    for i in range(5):
        sleep(1)
        yield b"some fake bytes"


@app.get("/conversation")
async def get_conversation_stream():
    return StreamingResponse(fake_streamer())


@app.post("/message")
def send_message():
    # openAiConnector = OpenAiConnector()
    # openAiConnector.set_context("You are a helpful tax assistant specialized in polish tax law")
    # return {"answer": openAiConnector.ask_question("Co to jest druk pcc-3?")}
    return {"answer": "Druk PCC-3 to formularz służący do zgłoszenia i opłacenia podatku od czynności cywilnoprawnych (PCC) w Polsce. Podatek ten jest pobierany m.in. przy nabyciu nieruchomości, samochodów, udziałów w spółkach czy przeniesieniu praw majątkowych. Druk PCC-3 należy złożyć w urzędzie skarbowym właściwym dla miejsca zamieszkania podatnika w terminie 14 dni od dnia powstania obowiązku podatkowego."}
