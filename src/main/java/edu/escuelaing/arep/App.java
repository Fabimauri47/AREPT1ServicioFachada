package edu.eci.arep;

import org.json.JSONObject;
import spark.Request;
import spark.Response;

import static spark.Spark.*;


public class App {
    public static void App(String[] args) {
        port(getPort());
        get("/", (req, res) -> inputDataPage(req, res));
        get("/calculador de operaciones", (req, res) -> {
            String tool = req.queryParams("tool");
            String numero = req.queryParams("numero");
            return new JSONObject(Cliente.get(numero, tool));
        });

        get("/results", (req, res) -> {
            String tool = req.queryParams("tool");
            String numero = req.queryParams("numero");
            JSONObject jsonObject = new JSONObject(Cliente.get(numero, tool));
            return outputDataPage(jsonObject);

        });

    }

    
     */
    private static String outputDataPage(JSONObject json) {
        return "<!DOCTYPE html>"
        + "<html>"
        + "<body>"
        + "<h2>Calculadora</h2>"
        + "<h4>Operacion</h4>"
        + json.get("operacion")
        + "<h4>Resultado:</h4>"
        + json.get("resultado")
        + "</body>"
        + "</html>";
    }

    
    
    private static String inputDataPage(Request req, Response res) {
        return "<!DOCTYPE html>"
        + "<html>"
        + "<head>"
        + "<title>AREP Primer Parcial calculadora</title>"
        + " <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
        + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">"
        + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\" integrity=\"sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN\" crossorigin=\"anonymous\">"
        + "</head>"
        + "<body>"
        + "<h2>Cal trigo</h2>"
        + "<form action=\"/results\">"
        + "  Numero a ingresar:<br>"
        + "  <input type=\"text\" name=\"number\">"
        + "  <br><br>"
        + "  Operacion a ingresar(sin, cos, tan):<br>"
        + "  <input type=\"text\" name=\"operacion\">"
        + "  <input type=\"submit\" value=\"Calcular\">"
        + "</form>"
        + "</body>"
        + "</html>";
    }


    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
