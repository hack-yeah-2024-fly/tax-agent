import React from "react";
import { TaxAdvisorChat } from "../../features/tax-advisor-chat";
import { TaxAdvisorChatProvider } from "../../features/tax-advisor-chat/context/TaxAdvisorChatContext";

export const MainPage: React.FC = () => {
  return (
    <TaxAdvisorChatProvider>
      <TaxAdvisorChat />
    </TaxAdvisorChatProvider>
  );
};
