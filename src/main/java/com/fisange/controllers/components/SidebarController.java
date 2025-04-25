package com.fisange.controllers.components;

import com.fisange.controllers.DashboardController;
import javafx.fxml.FXML;

public class SidebarController {

    private DashboardController parentController;

    public void setParentController(DashboardController controller) {
        this.parentController = controller;
    }

    @FXML
    private void showDashboard() {
        parentController.showDashboard();
    }

    @FXML
    private void showSales() {
        parentController.showSales();
    }

    @FXML
    private void showCategories() {
        parentController.showCategories();
    }

    @FXML
    private void showProducts() {
        parentController.showProducts();
    }

    @FXML
    private void showStock() {
        parentController.showStock();
    }

    @FXML
    private void showRates() {
        parentController.showRates();
    }

    @FXML
    private void showUsers() {
        parentController.showUsers();
    }

    @FXML
    private void showSettings() {
        parentController.showSettings();
    }

    @FXML
    private void addProduct() {
        parentController.addProduct();
    }

    @FXML
    private void mergeCatalogs() {
        parentController.mergeCatalogs();
    }

    @FXML
    private void revalidateStock() {
        parentController.revalidateStock();
    }
}
