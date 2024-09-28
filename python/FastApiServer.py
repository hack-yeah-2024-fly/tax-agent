from fastapi import FastAPI
import chatagent
from pydantic import BaseModel

class Message(BaseModel):
    question: str

app = FastAPI()
chat_app = chatagent.workflowmain()

@app.get("/session")
def get_session_id():
    return {"sessionId": "123"}

@app.get("/conversation")
def get_conversation_stream(message: Message):
    print(message.question)
    return chatagent.run_customer_support(message.question, chat_app)


@app.post("/message")
def send_message():
    return {"answer": "Druk PCC-3 to formularz służący do zgłoszenia i opłacenia podatku od czynności cywilnoprawnych (PCC) w Polsce. Podatek ten jest pobierany m.in. przy nabyciu nieruchomości, samochodów, udziałów w spółkach czy przeniesieniu praw majątkowych. Druk PCC-3 należy złożyć w urzędzie skarbowym właściwym dla miejsca zamieszkania podatnika w terminie 14 dni od dnia powstania obowiązku podatkowego."}
