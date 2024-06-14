package conversionmonedas.api;

import com.google.gson.Gson;
import conversionmonedas.modelos.Conversion;
import conversionmonedas.modelos.Moneda;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionApi {

    public Moneda getMonedas() {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/c15f2fb7d148a56647b1c8db/latest/USD/");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrio un error");
        }
    }

    public Conversion getConversion(String origen, String destino) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/c15f2fb7d148a56647b1c8db/pair/"+origen+"/"+destino+"/");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), Conversion.class);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrio un error");
        }
    }
}
