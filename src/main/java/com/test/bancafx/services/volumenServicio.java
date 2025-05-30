package com.test.bancafx.services;

import com.test.bancafx.model.transaccionvoluminosa;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class volumenServicio {
    private static final String API_BASE_URL = "https://fbanco.onrender.com/api/voluminoso";
    private final HttpClient httpClient;

    public volumenServicio() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(15))
                .build();
    }

    public void realizarPagoVolumen(transaccionvoluminosa transaccion) throws IOException {
        enviarTransaccionVoluminosa(transaccion, "/create");
    }

    private void enviarTransaccionVoluminosa(transaccionvoluminosa transaccion, String endpoint) throws IOException {
        try {
            JSONObject json = crearCuerpoSolicitud(transaccion);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_BASE_URL + endpoint))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            validarRespuesta(response);
            logRespuestaExitosa(response);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Operación interrumpida", e);
        } catch (IOException e) {
            throw new IOException("Error al comunicarse con el servidor: " + e.getMessage(), e);
        }
    }

    private JSONObject crearCuerpoSolicitud(transaccionvoluminosa transaccion) {
        JSONObject json = new JSONObject();
        json.put("numero_cuenta", transaccion.getNumero_cuenta());
        json.put("tipo_dep", transaccion.getTipo_dep());
        json.put("monto", transaccion.getMonto());
        json.put("cajero", transaccion.getCajero());
        json.put("descripcion", transaccion.getDescripcion());
        json.put("n_autorizacion", transaccion.getN_autorizacion());
        return json;
    }

    private void validarRespuesta(HttpResponse<String> response) throws IOException {
        if (response.statusCode() >= 400) {
            throw new IOException("Error en la API - Código: " +
                    response.statusCode() + ", Mensaje: " + response.body());
        }
    }

    private void logRespuestaExitosa(HttpResponse<String> response) {
        System.out.println("Transacción voluminosa procesada exitosamente. Respuesta: " +
                response.body());
    }
}