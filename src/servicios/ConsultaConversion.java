package servicios;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaConversion {
    public ExchangeCurrency monedaConversion(String base_code, String target_code) {
        String url = "https://v6.exchangerate-api.com/v6/139e759f3b423493c4f34a11/pair/"+base_code+"/"+target_code;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), ExchangeCurrency.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
