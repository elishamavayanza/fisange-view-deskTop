package com.fisange.controllers;

import com.fisange.models.Sale;
import com.fisange.services.SalesService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SalesController {

    @FXML
    private TableView<Sale> salesTable;

    @FXML
    private TableColumn<Sale, String> productColumn;

    @FXML
    private TableColumn<Sale, Integer> quantityColumn;

    @FXML
    private TableColumn<Sale, Double> priceColumn;

    private SalesService salesService;

    public SalesController() {
        this.salesService = new SalesService();
    }

    @FXML
    public void initialize() {
        // Initialize the columns
        productColumn.setCellValueFactory(cellData -> cellData.getValue().productProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        loadSalesData();
    }

    private void loadSalesData() {
        salesTable.setItems(salesService.getAllSales());
    }

    @FXML
    private void addSale() {
        // Implement logic to add a sale
    }

    @FXML
    private void cancelSale() {
        // Implement logic to cancel a sale
    }

    @FXML
    private void showSaleDetails() {
        Sale selectedSale = salesTable.getSelectionModel().getSelectedItem();
        if (selectedSale != null) {
            // Show detailed view of sale
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Aucune vente sélectionnée");
            alert.setContentText("Veuillez sélectionner une vente pour voir ses détails.");
            alert.showAndWait();
        }
    }
}
