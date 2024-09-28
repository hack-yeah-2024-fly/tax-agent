import { initServer } from "@ts-rest/express";
import { flightFilterContract } from "../../contracts";
import { flightFilterController } from "../../controller";

type ServerType = ReturnType<typeof initServer>;

const server: ServerType = initServer();

type FlightFilterRouterType = ReturnType<
  typeof server.router<typeof flightFilterContract>
>;

export const flightFilterRouter: FlightFilterRouterType = server.router(
  flightFilterContract,
  {
    postFilter: async (message) => {
      const response = await flightFilterController(message.body.message);
      return {
        status: 200,
        body: {
          message: response,
        },
      };
    },
  }
);
