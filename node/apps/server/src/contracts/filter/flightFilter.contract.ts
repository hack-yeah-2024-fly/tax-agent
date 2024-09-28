import { extendZodWithOpenApi } from "@anatine/zod-openapi";
import { initContract } from "@ts-rest/core";
import { z } from "zod";
import { Filters } from "./filterSchema.contract";

extendZodWithOpenApi(z);

const contract = initContract();

const UserMessage = z
  .object({
    message: z.string(),
    conversationId: z.string().optional(),
  })
  .openapi({
    description:
      "LLM message from the user to generate proper tasks base on router.",
  });

const AssistantMessage = z.object({
  message: Filters,
});

export const flightFilterContract = contract.router({
  postFilter: {
    method: "POST",
    path: "/ai/filter/flight",
    responses: {
      200: AssistantMessage,
    },
    body: UserMessage,
  },
});

export const flightSchema = z.object({
  flightNumber: z.string(),
  arrivalAirportIATA: z.string(),
  arrivalAirportICAO: z.string(),
  arrivalDateTime: z.string(),
  departureAirportIATA: z.string(),
  departureAirportICAO: z.string(),
  departureDateTime: z.string(),
});
