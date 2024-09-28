import os 
import constants
import sys
from OpenAiConnector import OpenAiConnector
from PineconeConnector import PineconeConnector
from AirtableConnector import AirtableConnector

os.environ["OPENAI_API_KEY"] = constants.OPEN_AI_API_KEY
os.environ["PINECONE_API_KEY"] = constants.PINECONE_API_KEY
os.environ["AIRTABLE_API_KEY"] = constants.AIRTABLE_API_KEY

# openAiConnector = OpenAiConnector()
# openAiConnector.set_context("You are a helpful assistant")
# print(openAiConnector.ask_question("What is the weather in Tokyo?"))

# pineconeConnector = PineconeConnector()
# pineconeConnector.create_index("test")

# airtableConnector = AirtableConnector()
# airtableConnector.save_record()


