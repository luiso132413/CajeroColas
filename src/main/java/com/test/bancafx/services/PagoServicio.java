package com.test.bancafx.services;

import com.test.bancafx.model.transaccionservicio;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class PagoServicio {
    private static final String API_BASE_URL = "https://fbanco.onrender.com/api/servicio";
    private final HttpClient httpClient;

    public PagoServicio() {
        httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(15))
                .build();
    }

    public void realizarPagoServicio(transaccionservicio transaccion) throws IOException {
        enviarTransaccionServicio(transaccion, "/create");
    }

    private void enviarTransaccionServicio(transaccionservicio transaccion, String endpoint) throws IOException {
        try {
            JSONObject json = new JSONObject();
            json.put("tipo_servicio", transaccion.getTipo_servicio());
            json.put("pago", transaccion.getPago());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_BASE_URL + endpoint))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() >= 400) {
                throw new IOException("Error en la API: " + response.body());
            }

            System.out.println("Pago de servicio exitoso: " + response.body());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Operación interrumpida", e);
        } catch (Exception e) {
            throw new IOException("Error al comunicarse con el servidor: " + e.getMessage(), e);
        }
    }
}
