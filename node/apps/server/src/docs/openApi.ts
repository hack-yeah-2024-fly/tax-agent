import { generateOpenApi } from "@ts-rest/open-api";
import { combineContracts } from "../contracts";

export const openApiDocument = generateOpenApi(combineContracts, {
  info: {
    title: "AI API",
    version: "1.0.0",
  },
});
