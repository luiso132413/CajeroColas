package com.test.bancafx.services;

import com.test.bancafx.model.Transaccioncaja;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class BancoService {
    private static final String API_BASE_URL = "https://fbanco.onrender.com/api/transaccion";
    private final HttpClient httpClient;

    public BancoService() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(15))
                .build();
    }

    public void realizarDeposito(Transaccioncaja transaccion) throws IOException {
        enviarTransaccion(transaccion, "/deposito");
    }

    public void realizarRetiro(Transaccioncaja transaccion) throws IOException {
        enviarTransaccion(transaccion, "/retirar");
    }


    private void enviarTransaccion(Transaccioncaja transaccion, String endpoint) throws IOException {
        try {
            JSONObject json = new JSONObject();
            json.put("numero_cuenta", transaccion.getNumeroCuenta());
            json.put("monto", transaccion.getMonto());
            json.put("descripcion", transaccion.getDescripcion());
            json.put("tipo", transaccion.getTipo());

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

            System.out.println("Transacción exitosa: " + response.body());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Operación interrumpida", e);
        } catch (Exception e) {
            throw new IOException("Error al comunicarse con el servidor: " + e.getMessage(), e);
        }
    }

}