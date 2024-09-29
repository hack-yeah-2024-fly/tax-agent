import { useState, useCallback } from "react";

export type MessageProps = {
  id: string;
  content: string;
  timestamp: string;
  unread?: boolean;
  sender: "Assistant" | "You";
  attachment?: {
    fileName: string;
    type: string;
    size: string;
  };
};

const useTaxAdvisorChat = () => {
  const [messages, setMessages] = useState<MessageProps[]>([]);

  const addMessage = useCallback(
    (content: string, sender: "Assistant" | "You") => {
      const newMessage: MessageProps = {
        id: Date.now().toString(),
        content,
        sender,
        timestamp: new Date().toLocaleTimeString([], {
          hour: "2-digit",
          minute: "2-digit",
        }),
      };
      setMessages((prevMessages) => [...prevMessages, newMessage]);
    },
    []
  );

  const clearMessages = useCallback(() => {
    setMessages([]);
  }, []);

  return {
    messages,
    addMessage,
    clearMessages,
  };
};

export default useTaxAdvisorChat;
