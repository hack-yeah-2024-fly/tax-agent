from pinecone import Pinecone
from pinecone import ServerlessSpec
import os

class PineconeConnector:
    def __init__(self):
        self.pc = Pinecone(api_key=os.getenv("PINECONE_API_KEY"))

    def create_index(self, name):
        self.pc.create_index(
            name=name,
            dimension=1536,
            metric="cosine",
            spec=ServerlessSpec(
                cloud="aws",
                region="us-east-1"
            )
        )

    