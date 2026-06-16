import { useState } from "react";
import Keycloak from "keycloak-js";

type Props = {
  keycloak: Keycloak;
};

function App({ keycloak }: Props) {
  const [response, setResponse] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);

  const token = keycloak.token;

  const callApi = async (url: string) => {
    if (!token) return;

    try {
      setLoading(true);

      const res = await fetch(url, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const data = await res.text();
      setResponse(data);
    } catch (err) {
      setResponse("❌ API call failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={styles.container}>
      <h1>🔐 Keycloak Dashboard</h1>

      <div style={styles.card}>
        <p>
          <b>Status:</b> {token ? "🟢 Logged In" : "🔴 Not Logged In"}
        </p>

        <p>
          <b>User:</b> {keycloak.tokenParsed?.preferred_username || "Guest"}
        </p>
      </div>

      <div style={styles.buttonRow}>
        <button
          style={{ ...styles.button, background: "green" }}
          onClick={() => callApi("http://localhost:8080/api/v1/public")}
          disabled={loading}
        >
          Call PUBLIC API
        </button>
        <button
          style={{ ...styles.button, background: "blue" }}
          onClick={() => callApi("http://localhost:8080/api/v1/user")}
          disabled={!token || loading}
        >
          Call USER API
        </button>

        <button
          style={{ ...styles.button, background: "darkred" }}
          onClick={() => callApi("http://localhost:8080/api/v1/admin")}
          disabled={!token || loading}
        >
          Call ADMIN API
        </button>

        <button
          style={{ ...styles.button, background: "gray" }}
          onClick={() => keycloak.logout()}
        >
          Logout
        </button>
      </div>

      <div style={styles.outputBox}>
        <h3>API Response</h3>
        <textarea
          value={loading ? "Loading..." : response}
          readOnly
          style={styles.textarea}
        />
      </div>
    </div>
  );
}

const styles: Record<string, React.CSSProperties> = {
  container: {
    fontFamily: "Arial",
    padding: 20,
    maxWidth: 700,
    margin: "auto",
  },
  card: {
    padding: 10,
    border: "1px solid #ddd",
    borderRadius: 8,
    marginBottom: 20,
  },
  buttonRow: {
    display: "flex",
    gap: 10,
    marginBottom: 20,
    flexWrap: "wrap",
  },
  button: {
    padding: "10px 15px",
    border: "none",
    borderRadius: 6,
    background: "#007bff",
    color: "white",
    cursor: "pointer",
  },
  outputBox: {
    marginTop: 20,
  },
  textarea: {
    width: "100%",
    height: 150,
    padding: 10,
    fontSize: 14,
  },
};

export default App;
