import sys
import os
sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from fastapi import FastAPI, Query
from fastapi.responses import StreamingResponse
import chatagent
from pydantic import BaseModel
import json

class Message(BaseModel):
    question: str

app = FastAPI()

chat_app = chatagent.workflowmain()

@app.get("/session")
def get_session_id():
    return {"sessionId": "123"}

@app.get("/conversation")
async def get_conversation_stream(question: str = Query(..., description="The user's question")):
    print(f"Received question: {question}")
    result = await chatagent.run_customer_support(question, chat_app)
    print(result)
    return {"response": result}

@app.post("/message")
def send_message():
    return {"answer": "Druk PCC-3 to formularz służący do zgłoszenia i opłacenia podatku od czynności cywilnoprawnych (PCC) w Polsce. Podatek ten jest pobierany m.in. przy nabyciu nieruchomości, samochodów, udziałów w spółkach czy przeniesieniu praw majątkowych. Druk PCC-3 należy złożyć w urzędzie skarbowym właściwym dla miejsca zamieszkania podatnika w terminie 14 dni od dnia powstania obowiązku podatkowego."}