import { initServer } from "@ts-rest/express";
import { messageContract } from "../../contracts/message";
import { fixGrammar } from "../../controller";

const server = initServer();

export const messageRouter = server.router<typeof messageContract>(
  messageContract,
  {
    postMessage: async (message) => {
      const response = await fixGrammar(message.body.message);
      return {
        status: 200,
        body: {
          systemMessage: response,
        },
      };
    },
  }
);
