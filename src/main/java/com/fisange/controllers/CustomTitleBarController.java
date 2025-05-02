package com.fisange.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CustomTitleBarController {

    @FXML private HBox customBar;
    @FXML private Button closeBtn;
    @FXML private Button minimizeBtn;
    @FXML private Button maximizeBtn;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void initialize() {
        // S'assurer que l'initialisation a lieu après la création complète des éléments
        Platform.runLater(() -> {
            if (closeBtn == null || minimizeBtn == null || maximizeBtn == null) {
                System.err.println("❌ Un ou plusieurs boutons sont null.");
                return;
            }

            // Assurer l'existence de la scène avant de manipuler la fenêtre
            if (closeBtn.getScene() != null) {
                Stage stage = (Stage) closeBtn.getScene().getWindow();

                // Déplacement de la fenêtre
                customBar.setOnMousePressed(event -> {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                });

                customBar.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                });

                // Gestion des boutons
                closeBtn.setOnAction(event -> handleClose());
                minimizeBtn.setOnAction(event -> handleMinimize());
                maximizeBtn.setOnAction(event -> handleMaximize());
            } else {
                System.err.println("❌ Scene est null.");
            }
        });
    }

    @FXML
    private void handleClose() {
        // Fermer la fenêtre
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleMinimize() {
        // Minimiser la fenêtre
        Stage stage = (Stage) minimizeBtn.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void handleMaximize() {
        // Maximiser ou restaurer la fenêtre
        Stage stage = (Stage) maximizeBtn.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }
}
