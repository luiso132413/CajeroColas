package com.test.bancafx;

import com.test.bancafx.controllers.TicketsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {
    private static Stage primaryStage;
    private static Stage ticketsStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        // Cargar ventana de tickets primero
        cargarVentanaTickets();

        // Cargar menú principal
        cargarMenuPrincipal();
    }

    private void cargarMenuPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/test/bancafx/views/menu-principal.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Sistema Bancario - Menú Principal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cargarVentanaTickets() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/test/bancafx/views/tickets.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        ticketsStage = new Stage();
        ticketsStage.setTitle("Panel de Tickets");
        ticketsStage.setScene(scene);
        ticketsStage.setX(100);
        ticketsStage.setY(100);
        ticketsStage.show();


    }

    public static void mostrarCajero() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/test/bancafx/views/cajero.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Operaciones de Cajero");
        stage.initOwner(primaryStage);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static void mostrarPagoServicios() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/test/bancafx/views/pago-servicios.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Pago de Servicios");
        stage.initOwner(primaryStage);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static void mostrarDepositosVoluminosos() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/test/bancafx/views/depositos-voluminosos.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Depósitos Voluminosos");
        stage.initOwner(primaryStage);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Stage getTicketsStage() {
        return ticketsStage;
    }
}