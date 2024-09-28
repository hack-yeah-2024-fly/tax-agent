import os
from langchain_openai import ChatOpenAI
from langchain_core.messages import SystemMessage, HumanMessage

class OpenAiConnector:
    def __init__(self):
        self.chat = ChatOpenAI(model="gpt-3.5-turbo", openai_api_key=os.getenv("OPENAI_API_KEY"))

    def set_context(self, context):
        self.context = context

    def ask_question(self, question):
        messages = [
            SystemMessage(content=self.context),
            HumanMessage(content=question)
        ]
        response = self.chat.invoke(messages)
        return response.content
