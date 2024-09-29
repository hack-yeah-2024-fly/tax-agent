import { Box, CircularProgress, Sheet, Stack } from "@mui/joy";
import { useState } from "react";
import AvatarWithStatus from "../chat/components/AvatarWithStatus";
import ChatBubble from "../chat/components/ChatBubble";
import MessageInput from "../chat/components/MessageInput";
import { AdvisorHeader } from "./components/advisor-header";
import { useTaxAdvisorChatContext } from "./context/TaxAdvisorChatContext";
import { useGetSessionId, useSendMessage } from "../../api/hooks";

export const TaxAdvisorChat: React.FC = () => {
  const { data: sessionId, isLoading } = useGetSessionId();
  const [textAreaValue, setTextAreaValue] = useState("");
  const { messages, addMessage, clearMessages } = useTaxAdvisorChatContext();

  const { mutateAsync: sendMessage } = useSendMessage();

  if (isLoading) return <CircularProgress />;

  console.log(sessionId);

  return (
    <Sheet
      sx={{
        height: { xs: "calc(100dvh - var(--Header-height))", md: "100dvh" },
        display: "flex",
        flexDirection: "column",
        backgroundColor: "background.level1",
      }}
    >
      <AdvisorHeader onNewQuery={clearMessages} />
      <Box
        sx={{
          display: "flex",
          flex: 1,
          minHeight: 0,
          px: 2,
          py: 3,
          overflowY: "scroll",
          flexDirection: "column-reverse",
        }}
      >
        <Stack spacing={2} sx={{ justifyContent: "flex-end" }}>
          {messages.map((message, index: number) => {
            const isYou = message.sender === "You";
            return (
              <Stack
                key={index}
                direction="row"
                spacing={2}
                sx={{ flexDirection: isYou ? "row-reverse" : "row" }}
              >
                {message.sender !== "You" && <AvatarWithStatus />}
                <ChatBubble
                  variant={isYou ? "sent" : "received"}
                  {...message}
                />
              </Stack>
            );
          })}
        </Stack>
      </Box>
      <MessageInput
        textAreaValue={textAreaValue}
        setTextAreaValue={setTextAreaValue}
        onSubmit={async () => {
          if (!textAreaValue.trim()) return;

          addMessage(textAreaValue, "You");
          setTextAreaValue("");

          try {
            const response = await sendMessage({ message: textAreaValue });
            if (typeof response?.answer === "string") {
              addMessage(response.answer, "Assistant");
            } else {
              console.error("Unexpected response format:", response);
            }
          } catch (error) {
            console.error("Error sending message:", error);
            addMessage(
              "Sorry, there was an error processing your message.",
              "Assistant"
            );
          }
        }}
      />
    </Sheet>
  );
};
