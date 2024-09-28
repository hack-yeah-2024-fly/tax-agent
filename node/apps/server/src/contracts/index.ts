import { initContract } from "@ts-rest/core";
import { flightFilterContract } from "./filter";
import { messageContract } from "./message";

export * from "./filter";
export * from "./message";

const contract = initContract();

export const combineContracts = contract.router({
  message: messageContract,
  flightFilter: flightFilterContract,
});
