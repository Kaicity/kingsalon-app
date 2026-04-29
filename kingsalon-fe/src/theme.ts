"use client";
import { createTheme } from "@mui/material/styles";

const theme = createTheme({
  typography: {
    fontFamily: "var(--font-roboto)",
    h1: { fontWeight: 600 },
    h2: { fontWeight: 600 },
    button: {
      textTransform: "none",
      fontWeight: 500,
    },
  },

  palette: {
    mode: "light",
    primary: {
      main: "#D63384",
    },

    secondary: {
      main: "#F8E1E7",
    },
  },
});

export default theme;
