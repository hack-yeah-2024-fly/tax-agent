# https://github.com/NirDiamant/GenAI_Agents/blob/main/all_agents_tutorials/customer_support_agent_langgraph.ipynb
# Requirements : langgraph langchain-core langchain-openai python-dotenv
from typing import Dict, TypedDict
from langgraph.graph import StateGraph, END
from langchain_core.prompts import ChatPromptTemplate
from langchain_openai import ChatOpenAI
from dotenv import load_dotenv
import os

yourllm = 1 # 1:OpenAI | 2: Local

# Load environment variables and set OpenAI API key
load_dotenv()
if yourllm==1:
    os.environ["OPENAI_API_KEY"] = os.getenv('OPENAI_API_KEY')

class State(TypedDict):
    query: str
    category: str
    sentiment: str
    response: str

def categorize(state: State) -> State:
    """Categorize the customer query into Technical, Billing, or General."""
    prompt = ChatPromptTemplate.from_template(
        "Categorize the following customer query into one of these categories: "
        "Technical, Billing, General. Query: {query}"
    )
    if yourllm==1:
        chain = prompt | ChatOpenAI(temperature=0)
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
        chain = prompt | ChatOpenAI(temperature=0)
    else:
        chain = prompt | ChatOpenAI(temperature=0, base_url="http://localhost:1234/v1", api_key="foo")
    sentiment = chain.invoke({"query": state["query"]}).content
    return {"sentiment": sentiment}

def handle_technical(state: State) -> State:
    """Provide a technical support response to the query."""
    prompt = ChatPromptTemplate.from_template(
        "Provide a technical support response to the following query: {query}"
    )
    if yourllm==1:
        chain = prompt | ChatOpenAI(temperature=0)
    else:
        chain = prompt | ChatOpenAI(temperature=0, base_url="http://localhost:1234/v1", api_key="foo")
    response = chain.invoke({"query": state["query"]}).content
    return {"response": response}

def handle_billing(state: State) -> State:
    """Provide a billing support response to the query."""
    prompt = ChatPromptTemplate.from_template(
        "Provide a billing support response to the following query: {query}"
    )
    if yourllm==1:
        chain = prompt | ChatOpenAI(temperature=0)
    else:
        chain = prompt | ChatOpenAI(temperature=0, base_url="http://localhost:1234/v1", api_key="foo")
    response = chain.invoke({"query": state["query"]}).content
    return {"response": response}

def handle_general(state: State) -> State:
    """Provide a general support response to the query."""
    prompt = ChatPromptTemplate.from_template(
        "Provide a general support response to the following query: {query}"
    )
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
    elif state["category"] == "Technical":
        return "handle_technical"
    elif state["category"] == "Billing":
        return "handle_billing"
    else:
        return "handle_general"

def workflowmain():
    # Create the graph
    workflow = StateGraph(State)

    # Add nodes
    workflow.add_node("categorize", categorize)
    workflow.add_node("analyze_sentiment", analyze_sentiment)
    workflow.add_node("handle_technical", handle_technical)
    workflow.add_node("handle_billing", handle_billing)
    workflow.add_node("handle_general", handle_general)
    workflow.add_node("escalate", escalate)

    # Add edges
    workflow.add_edge("categorize", "analyze_sentiment")
    workflow.add_conditional_edges(
        "analyze_sentiment",
        route_query,
        {
            "handle_technical": "handle_technical",
            "handle_billing": "handle_billing",
            "handle_general": "handle_general",
            "escalate": "escalate"
        }
    )
    workflow.add_edge("handle_technical", END)
    workflow.add_edge("handle_billing", END)
    workflow.add_edge("handle_general", END)
    workflow.add_edge("escalate", END)

    # Set entry point
    workflow.set_entry_point("categorize")

    # Compile the graph
    app = workflow.compile()
    return app


def run_customer_support(query: str, app) -> Dict[str, str]:
    """Process a customer query through the LangGraph workflow.
    
    Args:
        query (str): The customer's query
        
    Returns:
        Dict[str, str]: A dictionary containing the query's category, sentiment, and response
    """
    results = app.invoke({"query": query})
    return {
        "category": results["category"],
        "sentiment": results["sentiment"],
        "response": results["response"]
    }

if __name__ == "__main__":
    app = workflowmain()
    # escalate
    query = "My internet connection keeps dropping. Can you help?"
    result = run_customer_support(query, app)
    print(f"Query: {query}")
    print(f"Category: {result['category']}")
    print(f"Sentiment: {result['sentiment']}")
    print(f"Response: {result['response']}")
    print("\n")

    # handle_technical

    query = "I need help talking to chatGPT"
    result = run_customer_support(query, app)
    print(f"Query: {query}")
    print(f"Category: {result['category']}")
    print(f"Sentiment: {result['sentiment']}")
    print(f"Response: {result['response']}")
    print("\n")

    # handle_billing

    query = "where can i find my receipt?"
    result = run_customer_support(query, app)
    print(f"Query: {query}")
    print(f"Category: {result['category']}")
    print(f"Sentiment: {result['sentiment']}")
    print(f"Response: {result['response']}")
    print("\n")

    # handle_general

    query = "What are your business hours?"
    result = run_customer_support(query, app)
    print(f"Query: {query}")
    print(f"Category: {result['category']}")
    print(f"Sentiment: {result['sentiment']}")
    print(f"Response: {result['response']}")