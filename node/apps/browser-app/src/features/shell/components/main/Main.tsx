import { Box } from "@mui/joy";
import { Outlet } from "react-router-dom";
import { Header } from "../header";
import { Sidebar } from "../sidebar";

export const Main = () => (
  <Box sx={{ display: "flex", minHeight: "100dvh" }}>
    <Sidebar />
    <Box sx={{ flex: 1, display: "flex", flexDirection: "column" }}>
      <Header />
      <Box component="main" className="MainContent" sx={{ flex: 1 }}>
        <Outlet />
      </Box>
    </Box>
  </Box>
);
