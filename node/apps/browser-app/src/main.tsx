import { CssBaseline } from "@mui/joy";
import { CssVarsProvider } from "@mui/joy/styles";
import { StrictMode } from "react";
import ReactDOM from "react-dom/client";

import App from "./app";

import "@fontsource/inter";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <CssVarsProvider>
      <CssBaseline />
      <App />
    </CssVarsProvider>
  </StrictMode>
);
