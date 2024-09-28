import { ChatOpenAI } from "@langchain/openai";

import { ChatPromptTemplate } from "@langchain/core/prompts";
import { flightFilterPrompt } from "../../ai/prompts/filters/flightFilter.prompt";
import { Filters } from "../../contracts/filter/filterSchema.contract";

const llm = new ChatOpenAI({
  temperature: 0.2,
  modelName: "gpt-4o",
});

const modelWithStructuredOutput = llm.withStructuredOutput(Filters);

export const flightFilterController = async (userInput: string) => {
  const flightFilterPromptTemplate = ChatPromptTemplate.fromMessages([
    ["system", flightFilterPrompt],
    ["human", "{userInput}"],
  ]);
  const formattedPrompt = await flightFilterPromptTemplate.formatPromptValue({
    userInput,
  });
  const structuredFilterResponse =
    await modelWithStructuredOutput.invoke(formattedPrompt);

  return structuredFilterResponse;
};
