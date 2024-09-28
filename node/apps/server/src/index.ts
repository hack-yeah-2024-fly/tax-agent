import "dotenv/config";
import { createExpressEndpoints } from "@ts-rest/express";
import bodyParser from "body-parser";
import cors from "cors";
import express from "express";
import * as swaggerUi from "swagger-ui-express";
import { combineContracts } from "./contracts";
import { openApiDocument } from "./docs";
import { flightFilterRouter, messageRouter } from "./routers";

const app = express();

app.use(cors());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.get(
  "/api-docs/doc.json",
  (_req: express.Request, res: express.Response) => {
    res.json(openApiDocument);
  }
);

app.use("/api-docs", swaggerUi.serve, swaggerUi.setup(openApiDocument));

createExpressEndpoints(
  combineContracts,
  {
    message: messageRouter,
    flightFilter: flightFilterRouter,
  },
  app
);

const PORT = process.env.PORT ?? 4000;

app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});
