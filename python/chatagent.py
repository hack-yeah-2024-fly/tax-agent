from typing import Dict, TypedDict, AsyncGenerator
from langgraph.graph import StateGraph, END
from langchain_core.prompts import ChatPromptTemplate
from langchain_openai import ChatOpenAI
from langchain_community.chat_models import ChatPerplexity
from typing import List, Tuple
from langchain.schema import Document
from advanced_retriever import AdvancedRetriever
from dotenv import load_dotenv
import asyncio

load_dotenv()
yourllm = 2 # 1:OpenAI | 2: Perplexity  | 3: Local
OPENAI_API_KEY = "Keep The Key"
PERPLEXITY_API_KEY = "Keep The Key"

#intializing the retriver
retriever = AdvancedRetriever(OPENAI_API_KEY)

class State(TypedDict):
    query: str
    category: str
    sentiment: str
    response: str
    mode: str
    pre_response: str

def get_openai_model():
    return ChatOpenAI(temperature=0, api_key=OPENAI_API_KEY)

def get_perplexity_model():
    return ChatPerplexity(
            model="llama-3.1-sonar-small-128k-online",
            temperature=0.4,
            api_key=PERPLEXITY_API_KEY,
            stream=False,
        )

def get_perplexity_model2():
    return ChatPerplexity(
            model="llama-3.1-sonar-small-128k-online",
            temperature=0.4,
            api_key=PERPLEXITY_API_KEY,
            stream=True,
        )

# Add a new function to retrieve documents
async def retrieve_documents(state: State) -> State:
    pre_response = await retriever.retrieve_and_process(state["query"])
    return {"pre_response": pre_response}

def categorize(state: State) -> State:
    """Categorize the customer query into Polandtaxes, PolishPCC3, or General."""
    prompt = ChatPromptTemplate.from_template(
        "Analyze the following customer query and categorize it as 'Polandtaxes', 'PolishPCC3',  or 'General'. \
              Choose 'Polandtaxes' for queries about the Polish tax system, rates, or non-PCC-3 forms; \
                'PolishPCC3' for questions directly related to PCC-3 or civil law transaction taxes; \
                    and 'General' for queries not specific to Polish taxes or PCC-3, or if too vague to determine. \
                        Consider context and implied meaning, not just keywords. If a query fits multiple categories, \
                            select the most specific and relevant one. Provide your categorization and a brief \
                                explanation for your choice."
        "Query to categorize: {query}"
    )
    if yourllm==1:
        chain = prompt | get_openai_model()
    elif yourllm==2:
        chain = prompt | get_perplexity_model()
    else:
        chain = prompt | ChatOpenAI(temperature=0, base_url="http://localhost:1234/v1", api_key="foo")
    category = chain.invoke({"query": state["query"]}).content
    return {"category": category}

def analyze_sentiment(state: State) -> State:
    """Analyze the sentiment of the customer query as Positive, Neutral, or Negative."""
    prompt = ChatPromptTemplate.from_template(
        "Analyze the sentiment of the following customer query. "
        "Respond with either 'Positive', 'Neutral', or 'Negative'. Query: {query}"
    )
    if yourllm==1:
        chain = prompt | get_openai_model()
    elif yourllm==2:
        chain = prompt | get_perplexity_model()
    else:
        chain = prompt | ChatOpenAI(temperature=0, base_url="http://localhost:1234/v1", api_key="foo")
    sentiment = chain.invoke({"query": state["query"]}).content
    return {"sentiment": sentiment}

def handle_polandtaxes(state: State) -> State:
    """Provide a technical support response to the query."""
    prompt = ChatPromptTemplate.from_template(
        "Only Remember: You are an expert chatbot on Poland's tax system. \
            Provide accurate, concise information about Polish taxes, including rates, \
                deadlines, exemptions, and filing procedures. Explain concepts simply, \
                    offer general guidance, and stay updated on recent tax law changes. \
                        Maintain professionalism, focus solely on Polish taxes, and admit \
                            when you lack specific information. Your goal is to help users \
                                understand Polish taxes without offering personal opinions or \
                                    advice on tax evasion."
        "Important & Respond for: Provide Poland taxes query support response to the following query: {query}"
    )
    if yourllm==1:
        chain = prompt | get_openai_model()
    elif yourllm==2:
        chain = prompt | get_perplexity_model()
    else:
        chain = prompt | ChatOpenAI(temperature=0, base_url="http://localhost:1234/v1", api_key="foo")
    response = chain.invoke({"query": state["query"]}).content
    return {"response": response}

def handle_PCC3(state: State) -> State:
    prompt = ChatPromptTemplate.from_template(
        "You are an AI assistant for the Polish PCC-3 tax form. Use the following pre-processed information "
        "and the original query to provide a detailed response. If the pre-processed information is not directly "
        "relevant, focus on answering the query based on your knowledge of PCC-3 regulations.\n\n"
        "Pre-processed information: {pre_response}\n\n"
        "Original query: {query}\n\n"
        "Provide a detailed response:"
    )

    if yourllm == 1:
        chain = prompt | get_openai_model()
    elif yourllm == 2:
        chain = prompt | get_perplexity_model2()
    else:
        chain = prompt | ChatOpenAI(temperature=0, base_url="http://localhost:1234/v1", api_key="foo")
    
    response = chain.invoke({"query": state["query"], "pre_response": state["pre_response"]}).content
    return {"response": response}

def handle_general(state: State) -> State:
    """Provide a general support response to the query."""
    prompt = ChatPromptTemplate.from_template(
        "This is user query: {query}. Strictly follow this: If this query is not related to polish taxes then say in a very simple way that you answer only to polish tax questions."
    )
    if yourllm==1:
        chain = prompt | get_openai_model()
    elif yourllm==2:
        chain = prompt | get_perplexity_model()
    else:
        chain = prompt | ChatOpenAI(temperature=0, base_url="http://localhost:1234/v1", api_key="foo")
    response = chain.invoke({"query": state["query"]}).content
    return {"response": response}

def escalate(state: State) -> State:
    """Escalate the query to a human agent due to negative sentiment."""
    return {"response": "This query has been escalated to a human agent due to its negative sentiment."}

def route_query(state: State) -> str:
    """Route the query based on its sentiment and category."""
    if state["category"] == "Polandtaxes":
        return "handle_polandtaxes"
    elif state["category"] == "PolishPCC3":
        return "retrieve_documents"
    elif state["category"] == "General":
        return "handle_general"
    else:
        return "retrieve_documents"

def workflowmain():
    workflow = StateGraph(State)

    workflow.add_node("categorize", categorize)
    workflow.add_node("retrieve_documents", retrieve_documents)
    workflow.add_node("handle_polandtaxes", handle_polandtaxes)
    workflow.add_node("handle_PCC3", handle_PCC3)
    workflow.add_node("handle_general", handle_general)
    workflow.add_node("escalate", escalate)

    workflow.add_conditional_edges(
        "categorize",
        route_query,
        {
            "handle_polandtaxes": "handle_polandtaxes",
            "handle_PCC3": "retrieve_documents",
            "handle_general": "handle_general",
            "escalate": "escalate"
        }
    )

    workflow.add_edge("retrieve_documents", "handle_PCC3")
    workflow.add_edge("handle_polandtaxes", END)
    workflow.add_edge("handle_PCC3", END)
    workflow.add_edge("handle_general", END)
    workflow.add_edge("escalate", END)

    workflow.set_entry_point("categorize")

    return workflow.compile()

async def run_customer_support(query: str, app) -> str:
    """Process a customer query through the LangGraph workflow."""
    results = await app.ainvoke({"query": query})
    
    # Print category and sentiment internally
    print(f"Query: {query}")
    print(f"Category: {results['category']}")
    #print(f"Sentiment: {results['sentiment']}")
    print(f"Response: {results['response']}")
    
    # Return only the response
    return results["response"]

# Keep the app instance running
#app = asyncio.run(workflowmain())
async def main():
    app = workflowmain()

    query = "I have borrowed PLN 21,000 from my friend asha to renovate my flat. (PCC-3)"
    result = await run_customer_support(query, app)
    print(f"Query: {query}")
    print(result)
    print("\n")

if __name__ == "__main__":
    asyncio.run(main())