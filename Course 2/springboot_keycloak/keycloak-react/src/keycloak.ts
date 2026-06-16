import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  url: "http://localhost:9090",
  realm: "realm-demo",
  clientId: "demo",
});

export default keycloak;
