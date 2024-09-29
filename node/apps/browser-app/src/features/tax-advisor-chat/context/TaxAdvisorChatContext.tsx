import React, { createContext, useContext } from "react";
import useTaxAdvisorChat from "../hooks/useTaxAdvisorChat";

type TaxAdvisorChatContextType = ReturnType<typeof useTaxAdvisorChat>;

const TaxAdvisorChatContext = createContext<
  TaxAdvisorChatContextType | undefined
>(undefined);

export const TaxAdvisorChatProvider: React.FC<{
  children: React.ReactNode;
}> = ({ children }) => {
  const chatState = useTaxAdvisorChat();

  return (
    <TaxAdvisorChatContext.Provider value={chatState}>
      {children}
    </TaxAdvisorChatContext.Provider>
  );
};

// eslint-disable-next-line react-refresh/only-export-components
export const useTaxAdvisorChatContext = (): TaxAdvisorChatContextType => {
  const context = useContext(TaxAdvisorChatContext);
  if (!context) {
    throw new Error(
      "useTaxAdvisorChatContext must be used within a TaxAdvisorChatProvider"
    );
  }
  return context;
};
