import {
  useQuery,
  useMutation,
  UseQueryResult,
  UseMutationResult,
} from "@tanstack/react-query";
import { apiClient } from "./api-client";

// Get Session ID
export const useGetSessionId = (): UseQueryResult<string, Error> => {
  return useQuery({
    queryKey: ["sessionId"],
    queryFn: async () => {
      const response = await apiClient.get<{ sessionId: string }>("/session");
      return response.data.sessionId;
    },
  });
};

// Get Conversation Stream
export const useGetConversationStream = (): UseQueryResult<any, Error> => {
  return useQuery({
    queryKey: ["conversationStream"],
    queryFn: async () => {
      const response = await apiClient.get("/conversation");
      return response.data;
    },
  });
};

// Send Message
interface SendMessageParams {
  message: string;
  // Add any other required parameters
}

export const useSendMessage = (): UseMutationResult<
  { answer: string },
  Error,
  SendMessageParams
> => {
  return useMutation({
    mutationFn: async (params: SendMessageParams) => {
      const response = await apiClient.post<{ answer: string }>(
        "/message",
        params
      );
      return response.data;
    },
  });
};
