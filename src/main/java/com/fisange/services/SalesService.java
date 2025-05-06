package com.fisange.services;

import com.fisange.models.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SalesService {

    private ObservableList<Sale> sales;

    public SalesService() {
        // Initialisation des ventes
        sales = FXCollections.observableArrayList();
    }

    public ObservableList<Sale> getAllSales() {
        // Ici, tu peux récupérer les données de vente depuis la base de données ou une autre source.
        return sales;
    }

    public void addSale(Sale sale) {
        sales.add(sale);
        // Ajoute ici la logique pour ajouter la vente à la base de données
    }

    public void cancelSale(Sale sale) {
        sales.remove(sale);
        // Ajoute ici la logique pour annuler la vente dans la base de données
    }
}
