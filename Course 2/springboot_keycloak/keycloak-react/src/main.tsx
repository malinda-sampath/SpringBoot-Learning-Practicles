import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import keycloak from "./keycloak";

const container = document.getElementById("root");

if (!container) {
  throw new Error("Root element not found");
}

const root = ReactDOM.createRoot(container);

// Always render UI first (important)
const renderApp = () => {
  root.render(
    <React.StrictMode>
      <App keycloak={keycloak} />
    </React.StrictMode>,
  );
};

keycloak
  .init({
    onLoad: "login-required",
    pkceMethod: "S256",
    checkLoginIframe: false,
  })
  .then(() => {
    renderApp();
  })
  .catch((err) => {
    console.error("Keycloak init failed:", err);
    renderApp(); // STILL SHOW UI EVEN IF AUTH FAILS
  });

// fallback: render immediately (important for debugging)
renderApp();
