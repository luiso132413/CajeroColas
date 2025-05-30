package com.test.bancafx.controllers;

import com.test.bancafx.services.TicketService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TicketsController {
    @FXML private TextArea ticketArea;

    private TicketService ticketService = new TicketService();

    @FXML
    private void atenderProximoTicket() {
        String ticket = ticketService.obtenerProximoTicket();
        if (ticket != null) {
            ticketArea.setText("Ticket atendido:\n" + ticket + "\n\nProceso completado.");
            mostrarMensaje("Atención completada", "Ticket " + ticket + " atendido con éxito", AlertType.INFORMATION);
        } else {
            ticketArea.setText("No hay tickets pendientes para atender.");
        }
    }

    @FXML
    private void finalizarOperacion() {
        boolean resultado = ticketService.finalizarOperacion();
        if (resultado) {
            ticketArea.setText("Operación finalizada con éxito.");
            mostrarMensaje("Operación completada", "La operación ha sido finalizada", AlertType.INFORMATION);
        } else {
            ticketArea.setText("No hay operación para finalizar.");
        }
    }


    private void mostrarMensaje(String titulo, String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}