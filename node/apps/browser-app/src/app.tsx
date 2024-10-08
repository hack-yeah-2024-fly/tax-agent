import CssBaseline from "@mui/joy/CssBaseline";
import { CssVarsProvider } from "@mui/joy/styles";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

import { Main } from "./features/shell/components/main";
import LoginPage from "./pages/login/login.page";
import Orders from "./features/orders/orders";
import MyProfile from "./features/form/components/MyProfile";
import { MainPage } from "./pages/main";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Main />,
    children: [
      {
        path: "/",
        element: <MainPage />,
      },
      {
        path: "/orders",
        element: <Orders />,
      },
      {
        path: "/form",
        element: <MyProfile />,
      },
    ],
  },
  {
    path: "/login",
    element: <LoginPage />,
  },
]);

const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <CssVarsProvider defaultMode="dark" disableTransitionOnChange>
        <CssBaseline />
        <RouterProvider router={router} />
      </CssVarsProvider>
    </QueryClientProvider>
  );
}

export default App;
