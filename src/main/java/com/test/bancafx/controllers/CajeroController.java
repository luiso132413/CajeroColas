package com.test.bancafx.controllers;

import com.test.bancafx.model.Transaccioncaja;
import com.test.bancafx.services.BancoService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CajeroController {
    @FXML private TextField txtNoCuenta;
    @FXML private TextField txtMonto;
    @FXML private TextField txtDescripcion;

    private final BancoService bancoService = new BancoService();

    @FXML
    private void realizarDeposito() {
        try {
            Transaccioncaja transaccion = crearTransaccion("DEPOSITO");
            bancoService.realizarDeposito(transaccion);
            mostrarMensaje("Éxito", "Depósito realizado: " + transaccion, AlertType.INFORMATION);
            limpiarCampos();
        } catch (Exception e) {
            manejarError(e);
        }
    }

    @FXML
    private void realizarRetiro() {
        try {
            Transaccioncaja transaccion = crearTransaccion("RETIRO");
            bancoService.realizarRetiro(transaccion);
            mostrarMensaje("Éxito", "Retiro realizado: " + transaccion, AlertType.INFORMATION);
            limpiarCampos();
        } catch (Exception e) {
            manejarError(e);
        }
    }

    @FXML
    private void cancelar() {
        txtNoCuenta.getScene().getWindow().hide();
    }

    private Transaccioncaja crearTransaccion(String tipo) throws NumberFormatException {
        int cuenta = Integer.parseInt(txtNoCuenta.getText());
        double monto = Double.parseDouble(txtMonto.getText());
        String descripcion = txtDescripcion.getText();
        return new Transaccioncaja(cuenta, tipo, monto, descripcion);
    }

    private void limpiarCampos() {
        txtNoCuenta.clear();
        txtMonto.clear();
        txtDescripcion.clear();
    }

    private void manejarError(Exception e) {
        String mensaje = e instanceof NumberFormatException
                ? "Error en datos: Número de cuenta debe ser entero y monto debe ser numérico"
                : "Error: " + e.getMessage();

        mostrarMensaje("Error", mensaje, AlertType.ERROR);
    }

    private void mostrarMensaje(String titulo, String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}