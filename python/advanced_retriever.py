import pickle
from typing import List, Tuple
from langchain.vectorstores import FAISS
from langchain.schema import Document
from langchain.retrievers import ContextualCompressionRetriever
from langchain.retrievers.document_compressors import LLMChainExtractor
from langchain_openai import ChatOpenAI
from langchain_core.prompts import ChatPromptTemplate

class AdvancedRetriever:
    def __init__(self, openai_api_key: str):
        self.openai_api_key = openai_api_key
        self.vector_store_path = r"./polish_tax_kb/vector_storage/polish_tax_kb.pkl"
        self.vector_store = self._load_vector_store()
        self.retriever = self._create_retriever()
        self.llm = ChatOpenAI(temperature=0, api_key=self.openai_api_key)

    def _load_vector_store(self) -> FAISS:
        with open(self.vector_store_path, "rb") as f:
            return pickle.load(f)

    def _create_retriever(self) -> ContextualCompressionRetriever:
        base_retriever = self.vector_store.as_retriever(
            search_type="mmr",
            search_kwargs={"k": 5, "fetch_k": 20}
        )
        
        compressor = LLMChainExtractor.from_llm(self.llm)
        
        return ContextualCompressionRetriever(
            base_compressor=compressor,
            base_retriever=base_retriever
        )

    async def retrieve_and_process(self, query: str) -> str:
        retrieved_docs = await self.retriever.aget_relevant_documents(query)
        
        context = "\n".join([doc.page_content for doc in retrieved_docs])
        
        prompt = ChatPromptTemplate.from_template(
            "Based on the following context about Polish PCC-3 tax regulations, "
            "provide a concise summary of relevant information for the query. "
            "Context: {context}\n\nQuery: {query}"
        )
        
        chain = prompt | self.llm
        pre_response = chain.invoke({"context": context, "query": query}).content
        
        return pre_response