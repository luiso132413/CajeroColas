package com.test.bancafx.controllers;

import com.test.bancafx.model.transaccionvoluminosa;
import com.test.bancafx.services.volumenServicio;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DepositosVoluminososController {

    @FXML private TextField txtNumeroCuenta;
    @FXML private TextField txtJupqDeposito;
    @FXML private TextField txtMonto;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtNoAutorizacion;

    private final volumenServicio servicio = new volumenServicio();

    @FXML
    private void initialize() {
        txtNoAutorizacion.setText(generarNumeroAutorizacion());
    }

    @FXML
    private void realizarDeposito() {
        if (txtNumeroCuenta.getText().isEmpty() || txtJupqDeposito.getText().isEmpty() ||
                txtMonto.getText().isEmpty() || txtDescripcion.getText().isEmpty()) {
            mostrarMensaje("Error",
                    "Todos los campos son obligatorios",
                    AlertType.ERROR);
            return;
        }

        try {
            transaccionvoluminosa transaccion = crearTransaccion();
            servicio.realizarPagoVolumen(transaccion);

            mostrarMensaje("Depósito Exitoso",
                    "Se ha registrado la transacción:\n" + transaccion.toString(),
                    AlertType.INFORMATION);

            limpiarCampos();
            cerrarVentana();

        } catch (Exception ex) {
            mostrarMensaje("Error",
                    "Error al realizar el depósito: " + ex.getMessage(),
                    AlertType.ERROR);
        }
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    private transaccionvoluminosa crearTransaccion() {
        int numeroCuenta = Integer.parseInt(txtNumeroCuenta.getText());
        String tipoDep = txtJupqDeposito.getText();
        double monto = Double.parseDouble(txtMonto.getText());
        String descripcion = txtDescripcion.getText();
        int nAutorizacion = Integer.parseInt(txtNoAutorizacion.getText());

        return new transaccionvoluminosa(
                numeroCuenta,
                nAutorizacion,
                descripcion,
                "Cajero 1",
                monto,
                tipoDep
        );
    }

    private void limpiarCampos() {
        txtNumeroCuenta.clear();
        txtJupqDeposito.clear();
        txtMonto.clear();
        txtDescripcion.clear();
        txtNoAutorizacion.setText(generarNumeroAutorizacion());
    }

    private String generarNumeroAutorizacion() {
        return String.valueOf((int)(Math.random() * 900000) + 100000);
    }

    private void cerrarVentana() {
        txtNumeroCuenta.getScene().getWindow().hide();
    }

    private void mostrarMensaje(String titulo, String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
