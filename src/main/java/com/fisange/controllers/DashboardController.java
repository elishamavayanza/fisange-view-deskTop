package com.fisange.controllers;

import com.fisange.controllers.components.SidebarController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


import java.io.IOException;

public class DashboardController {

    @FXML
    private StackPane contentArea;

    @FXML
    private VBox sidebarContainer;

    @FXML
    public void initialize() {
        loadSidebar();
        showDashboard(); // Affichage par défaut
    }

    private void loadSidebar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fisange/views/components/Sidebar.fxml"));
            ScrollPane sidebar = loader.load();

            // Permet à la sidebar de grandir sur toute la hauteur
            VBox.setVgrow(sidebar, Priority.ALWAYS);

            SidebarController sidebarController = loader.getController();
            sidebarController.setParentController(this);

            sidebarContainer.getChildren().add(sidebar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthodes appelées depuis SidebarController
    public void showDashboard() {
        System.out.println("Tableau de bord cliqué");
        setContent("Bienvenue dans le Tableau de bord !");
    }

    public void showSales() {
        System.out.println("Point de vente cliqué");
        setContent("Point de vente");
    }

    public void showCategories() {
        System.out.println("Catégories de produits cliquées");
        setContent("Catégories de produits");
    }

    public void showProducts() {
        System.out.println("Tous nos produits cliqués");
        setContent("Tous nos produits");
    }

    public void showStock() {
        System.out.println("État de stock cliqué");
        setContent("État de stock");
    }

    public void showRates() {
        System.out.println("Taux de change cliqué");
        setContent("Taux de change");
    }

    public void showUsers() {
        System.out.println("Utilisateurs cliqués");
        setContent("Utilisateurs");
    }

    public void showSettings() {
        System.out.println("Configuration cliquée");
        setContent("Configuration");
    }

    public void addProduct() {
        System.out.println("Ajouter un produit cliqué");
        setContent("Ajouter un produit");
    }

    public void mergeCatalogs() {
        System.out.println("Fusion des catalogues cliquée");
        setContent("Fusion des catalogues");
    }

    public void revalidateStock() {
        System.out.println("Revalider Qnt disponible cliqué");
        setContent("Revalider Qnt disponible");
    }

    private void setContent(String text) {
        contentArea.getChildren().clear();
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        contentArea.getChildren().add(label);
    }
}
