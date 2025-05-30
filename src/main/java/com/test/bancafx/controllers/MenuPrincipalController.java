package com.test.bancafx.controllers;

import com.test.bancafx.App;
import javafx.fxml.FXML;

public class MenuPrincipalController {
    @FXML
    private void abrirCajero() throws Exception {
        App.mostrarCajero();
    }

    @FXML
    private void abrirPagoServicios() throws Exception {
        App.mostrarPagoServicios();
    }

    @FXML
    private void abrirDepositosVoluminosos() throws Exception {
        App.mostrarDepositosVoluminosos();
    }
}