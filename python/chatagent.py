from typing import Dict, TypedDict, AsyncGenerator
from langgraph.graph import StateGraph, END
from langchain_core.prompts import ChatPromptTemplate
from langchain_openai import ChatOpenAI
from dotenv import load_dotenv
import asyncio

load_dotenv()
yourllm = 1 # 1:OpenAI | 2: Local
OPENAI_API_KEY = "Keep The Key"


class State(TypedDict):
    query: str
    category: str
    sentiment: str
    response: str

def get_openai_model():
    return ChatOpenAI(temperature=0, api_key=OPENAI_API_KEY)

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
    else:
        chain = prompt | ChatOpenAI(temperature=0, base_url="http://localhost:1234/v1", api_key="foo")
    response = chain.invoke({"query": state["query"]}).content
    return {"response": response}

def handle_PCC3(state: State) -> State:
    """Provide a billing support response to the query."""
    prompt = ChatPromptTemplate.from_template(
        "Only Remember: You are an AI assistant for the Polish PCC-3 tax form. Provide clear, accurate information\
              on filling out the form, including its purpose, deadlines, calculations, and submission\
                  process. Explain form sections, clarify terms, and offer general guidance on \
                    tax scenarios. Maintain a helpful tone, prioritize accuracy, and admit if you're \
                        unsure about any details. Stay updated on current PCC-3 regulations and \
                            Polish tax law."
        "Important & Respond for: Provide Polish PCC-3 tax form query support response to the following query: {query}"
    )
    if yourllm==1:
        chain = prompt | get_openai_model()
    else:
        chain = prompt | ChatOpenAI(temperature=0, base_url="http://localhost:1234/v1", api_key="foo")
    response = chain.invoke({"query": state["query"]}).content
    return {"response": response}

def handle_general(state: State) -> State:
    """Provide a general support response to the query."""
    prompt = ChatPromptTemplate.from_template(
        "This is user query: {query}. Strictly follow this: If this query is not related to polish taxes then say in a very simple way that you answer only to polish tax questions."
    )
    if yourllm==1:
        chain = prompt | get_openai_model()
    else:
        chain = prompt | ChatOpenAI(temperature=0, base_url="http://localhost:1234/v1", api_key="foo")
    response = chain.invoke({"query": state["query"]}).content
    return {"response": response}

def escalate(state: State) -> State:
    """Escalate the query to a human agent due to negative sentiment."""
    return {"response": "This query has been escalated to a human agent due to its negative sentiment."}

def route_query(state: State) -> str:
    """Route the query based on its sentiment and category."""
    if state["sentiment"] == "Negative":
        return "escalate"
    elif state["category"] == "Polandtaxes":
        return "handle_polandtaxes"
    elif state["category"] == "PolishPCC3":
        return "handle_PCC3"
    else:
        return "handle_general"

def workflowmain():
    workflow = StateGraph(State)
    
    # Add nodes
    workflow.add_node("categorize", categorize)
    workflow.add_node("analyze_sentiment", analyze_sentiment)
    workflow.add_node("handle_polandtaxes", handle_polandtaxes)
    workflow.add_node("handle_PCC3", handle_PCC3)
    workflow.add_node("handle_general", handle_general)
    workflow.add_node("escalate", escalate)
    
    # Add edges
    workflow.add_edge("categorize", "analyze_sentiment")
    workflow.add_conditional_edges(
        "analyze_sentiment",
        route_query,
        {
            "handle_polandtaxes": "handle_polandtaxes",
            "handle_PCC3": "handle_PCC3",
            "handle_general": "handle_general",
            "escalate": "escalate"
        }
    )
    
    workflow.add_edge("handle_polandtaxes", END)
    workflow.add_edge("handle_PCC3", END)
    workflow.add_edge("handle_general", END)
    workflow.add_edge("escalate", END)
    
    workflow.set_entry_point("categorize")
    
    app = workflow.compile()
    return app

async def run_customer_support(query: str, app) -> str:
    """Process a customer query through the LangGraph workflow."""
    results = await app.ainvoke({"query": query})
    
    # Print category and sentiment internally
    print(f"Query: {query}")
    print(f"Category: {results['category']}")
    print(f"Sentiment: {results['sentiment']}")
    print(f"Response: {results['response']}")
    
    # Return only the response
    return results["response"]

# Keep the app instance running
#app = asyncio.run(workflowmain())

if __name__ == "__main__":
    app = workflowmain()

    query = "How to get my PIT 3 form. Can you help?"
    result = run_customer_support(query, app)
    print(f"Query: {query}")
    print(f"Category: {result['category']}")
    print(f"Sentiment: {result['sentiment']}")
    print(f"Response: {result['response']}")
    print("\n")