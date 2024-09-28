import { extendZodWithOpenApi } from "@anatine/zod-openapi";
import { initContract } from "@ts-rest/core";
import { z } from "zod";

extendZodWithOpenApi(z);

const contract = initContract();

const UserMessage = z
  .object({
    message: z.string(),
  })
  .openapi({
    description: "Natural language message from user",
  });

const SystemMessage = z.object({
  systemMessage: z.string(),
});

export const messageContract = contract.router({
  postMessage: {
    method: "POST",
    path: "/ai/correct",
    responses: {
      200: SystemMessage,
    },
    body: UserMessage,
  },
});
