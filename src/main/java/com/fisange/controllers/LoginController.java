package com.fisange.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println("Tentative de connexion : " + username);

        if ("admin".equals(username) && "admin123".equals(password)) {
            System.out.println("✅ Connexion réussie, chargement du Dashboard...");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fisange/views/DashboardView.fxml"));
                Parent root = loader.load();

                Scene dashboardScene = new Scene(root);
                dashboardScene.getStylesheets().add(getClass().getResource("/styles/futuristic.css").toExternalForm());


                Stage stage = new Stage();
                stage.setTitle("FISANGE - Dashboard");
                stage.setScene(dashboardScene);
                stage.initStyle(StageStyle.UNDECORATED);

//              stage.setMaximized(true);
                stage.setHeight(700);
                stage.setWidth(1100);
                stage.show();

                // Fermer la fenêtre de connexion
                Stage currentStage = (Stage) usernameField.getScene().getWindow();
                currentStage.close();

            } catch (IOException e) {
                System.err.println("❌ Erreur FXML : " + e.getMessage());
                e.printStackTrace();
                errorLabel.setText("Erreur de chargement du tableau de bord.Veuillez vérifier les chemins des ressources.");
                errorLabel.setVisible(true);
            }

        } else {
            errorLabel.setText("Identifiants invalides");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    public void handleForgotPassword() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mot de passe oublié");
        alert.setHeaderText(null);
        alert.setContentText("La récupération de mot de passe sera bientôt disponible.");
        alert.showAndWait();
    }

    @FXML
    public void handleCreateAccount() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Créer un compte");
        alert.setHeaderText(null);
        alert.setContentText("La création de compte sera bientôt disponible.");
        alert.showAndWait();
    }

    @FXML
    public void handleCancel() {
        // Fermer simplement la fenêtre de connexion
        Platform.exit();
    }


}
