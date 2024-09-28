import { ChatOpenAI } from "@langchain/openai";

import { ChatPromptTemplate } from "@langchain/core/prompts";
import { fixGrammarPrompt } from "../../ai";

const llm = new ChatOpenAI({
  temperature: 0.9,
  modelName: "gpt-4o",
});

export const fixGrammar = async (userText: string) => {
  const prompt = ChatPromptTemplate.fromMessages([
    ["system", fixGrammarPrompt],
    ["human", userText],
  ]);
  const msg = await prompt.formatMessages({});
  const response = await llm.invoke(msg);

  return response.content as string;
};
