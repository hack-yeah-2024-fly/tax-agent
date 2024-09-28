import { combineContracts } from "@repo/server/src/contracts";
import { initQueryClient } from "@ts-rest/react-query";

export const client = initQueryClient(combineContracts, {
  baseUrl: "http://localhost:4000",
});
