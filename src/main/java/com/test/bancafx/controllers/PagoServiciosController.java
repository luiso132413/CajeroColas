package com.test.bancafx.controllers;

import com.test.bancafx.services.PagoServicio;
import com.test.bancafx.model.transaccionservicio;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class PagoServiciosController {
    @FXML private TextField txtNombreServicio;
    @FXML private TextField txtMonto;

    private final PagoServicio pagoServicio = new PagoServicio();
    private final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

    @FXML
    private void realizarPago() {
        try {

            if (!validarCampos()) {
                return;
            }

            double monto = obtenerMontoValidado();
            if (monto <= 0) {
                return;
            }

            transaccionservicio transaccion = crearTransaccion(monto);
            pagoServicio.realizarPagoServicio(transaccion);

            mostrarConfirmacion(transaccion);
            limpiarCampos();
            cerrarVentana();

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Formato de monto inválido", AlertType.ERROR);
        } catch (IOException e) {
            mostrarMensaje("Error", "Error al procesar el pago: " + e.getMessage(), AlertType.ERROR);
        } catch (Exception e) {
            mostrarMensaje("Error", "Error inesperado: " + e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private boolean validarCampos() {
        if (txtNombreServicio.getText().trim().isEmpty() || txtMonto.getText().trim().isEmpty()) {
            mostrarMensaje("Error", "Todos los campos son obligatorios", AlertType.ERROR);
            return false;
        }
        return true;
    }

    private double obtenerMontoValidado() throws ParseException {
        String montoTexto = txtMonto.getText().trim();
        double monto = numberFormat.parse(montoTexto).doubleValue();

        if (monto <= 0) {
            mostrarMensaje("Error", "El monto debe ser mayor a cero", AlertType.ERROR);
            return -1;
        }

        if (monto > 1000000) {
            mostrarMensaje("Error", "El monto máximo permitido es Q1,000,000", AlertType.ERROR);
            return -1;
        }

        if (montoTexto.contains(".")) {
            String parteDecimal = montoTexto.split("\\.")[1];
            if (parteDecimal.length() > 2) {
                mostrarMensaje("Error", "Máximo 2 decimales permitidos", AlertType.ERROR);
                return -1;
            }
        }

        return monto;
    }

    private transaccionservicio crearTransaccion(double monto) {
        return new transaccionservicio(
                (int) Math.round(monto * 100), // Convertir a centavos
                txtNombreServicio.getText().trim()
        );
    }

    private void mostrarConfirmacion(transaccionservicio transaccion) {
        double monto = transaccion.getPago() / 100.0;
        String mensaje = String.format(
                "Servicio: %s%nMonto: Q%,f%nTransacción exitosa",
                transaccion.getTipo_servicio(),
                monto
        );
        mostrarMensaje("Pago realizado", mensaje, AlertType.INFORMATION);
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    private void limpiarCampos() {
        txtNombreServicio.clear();
        txtMonto.clear();
    }

    private void cerrarVentana() {
        txtNombreServicio.getScene().getWindow().hide();
    }

    private void mostrarMensaje(String titulo, String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}