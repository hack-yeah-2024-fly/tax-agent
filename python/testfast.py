from fastapi.testclient import TestClient
from FastApiServer import app

client = TestClient(app)

def test_get_session_id():
    response = client.get("/session")
    assert response.status_code == 200
    assert "sessionId" in response.json()

def test_get_conversation_stream():
    response = client.get("/conversation", params={"question": "Test question"})
    assert response.status_code == 200
    assert "response" in response.json()

def test_send_message():
    response = client.post("/message")
    assert response.status_code == 200
    assert "answer" in response.json()