package com.fisange.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.transform.Rotate;
import java.io.IOException;

public class DashboardHomeController {

    @FXML private Label selectedMonthLabel;
    private String currentSelectedMonth = "Aucun";

    @FXML private HBox cardsContainer;
    @FXML private HBox chartsContainer;
    @FXML private VBox legendContainer;
    @FXML private VBox calendarContainer;
    @FXML private VBox salesContainer;

    private static final double RADIUS = 200;  // Rayon de la couronne (distance du centre)

    @FXML
    public void initialize() {
        loadCards();
        loadCharts();
        loadLegend();
        loadCalendar();
        loadSales();
    }

    private void loadCards() {
        cardsContainer.getChildren().clear();
        for (int i = 1; i <= 3; i++) {
            try {
                VBox card = FXMLLoader.load(getClass().getResource("/com/fisange/views/components/CustomCard.fxml"));
                HBox.setHgrow(card, Priority.ALWAYS); // Permet à chaque carte de s'étendre
                card.setMaxWidth(Double.MAX_VALUE);   // Largeur maximale illimitée
                cardsContainer.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void loadCharts() {
        chartsContainer.getChildren().clear();

        // Premier PieChart : Répartition des ventes
        PieChart pieChart1 = new PieChart();
        pieChart1.getData().addAll(
                new PieChart.Data("Catégorie A", 25),
                new PieChart.Data("Catégorie B", 35),
                new PieChart.Data("Catégorie C", 20),
                new PieChart.Data("Catégorie D", 20)
        );
        pieChart1.setTitle("Répartition des ventes");
        pieChart1.setLabelsVisible(false);
        stylePieChart(pieChart1);

        // Deuxième PieChart : Statistiques produits
        PieChart pieChart2 = new PieChart();
        pieChart2.getData().addAll(
                new PieChart.Data("Produit X", 40),
                new PieChart.Data("Produit Y", 30),
                new PieChart.Data("Produit Z", 20),
                new PieChart.Data("Produit W", 10)
        );
        pieChart2.setTitle("Statistiques produits");
        pieChart2.setLabelsVisible(false);
        stylePieChart(pieChart2);

        // Conteneurs pour chaque graphique
        StackPane container1 = new StackPane(pieChart1);
        StackPane container2 = new StackPane(pieChart2);

        container1.setStyle("-fx-background-color: #3a3a3a; -fx-padding: 10;");
        container1.setEffect(new DropShadow(10, Color.BLACK));
        container1.setPrefSize(300, 300);

        container2.setStyle("-fx-background-color: #3a3a3a; -fx-padding: 10;");
        container2.setEffect(new DropShadow(10, Color.BLACK));
        container2.setPrefSize(300, 300);

        // Ne pas utiliser de translation ou rotation ici !
        chartsContainer.getChildren().addAll(container1, container2);
    }


    private void stylePieChart(PieChart chart) {
        chart.setLegendVisible(false);
        chart.setLabelsVisible(false);
        chart.setClockwise(true);
        chart.setStartAngle(90);

        // Effet "couronne"
        chart.setStyle(
                "-fx-pie-label-visible: false;" +
                        "-fx-background-color: transparent;" +
                        "-fx-padding: 20;"
        );

        // Couleurs personnalisées (peuvent être ajustées)
        String[] colors = {"#ff6b6b", "#feca57", "#48dbfb", "#1dd1a1"};
        int i = 0;
        for (PieChart.Data data : chart.getData()) {
            data.getNode().setStyle("-fx-pie-color: " + colors[i % colors.length] + ";");
            i++;
        }
    }



    private void loadLegend() {
        legendContainer.getChildren().clear();
        Label label = new Label("Légende :\n- A = Produit Alpha\n- B = Produit Beta");
        label.setStyle("-fx-text-fill: white;");
        legendContainer.getChildren().add(label);
    }

    private void loadCalendar() {
        calendarContainer.getChildren().clear();
        Label calendarLabel = new Label("Calendrier (Année 2025)");
        calendarLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        VBox monthsBox = new VBox();
        monthsBox.setSpacing(5);

        for (String month : new String[]{"Jan", "Fév", "Mar", "Avr", "Mai", "Juin",
                "Juil", "Août", "Sep", "Oct", "Nov", "Déc"}) {
            Label monthLabel = new Label(month);
            monthLabel.setStyle("-fx-text-fill: white; -fx-cursor: hand;");
            monthLabel.setOnMouseClicked(e -> {
                currentSelectedMonth = month;
                selectedMonthLabel.setText("Mois sélectionné : " + month);
                loadCharts1();
                // Changer les couleurs
            });
            monthsBox.getChildren().add(monthLabel);
        }

        calendarContainer.getChildren().addAll(calendarLabel, monthsBox);
    }

    private void loadCharts1() {
        chartsContainer.getChildren().clear();

        // Création du premier graphique dynamique
        PieChart pieChart1 = new PieChart();
        pieChart1.setTitle("Répartition des ventes");
        pieChart1.setLabelsVisible(false);
        populateChartData(pieChart1, "ventes");

        // Création du deuxième graphique dynamique
        PieChart pieChart2 = new PieChart();
        pieChart2.setTitle("Statistiques produits");
        pieChart2.setLabelsVisible(false);
        populateChartData(pieChart2, "produits");

        stylePieChart(pieChart1);
        stylePieChart(pieChart2);

        // Conteneurs
        StackPane container1 = new StackPane(pieChart1);
        StackPane container2 = new StackPane(pieChart2);

        for (StackPane container : new StackPane[]{container1, container2}) {
            container.setStyle("-fx-background-color: #3a3a3a; -fx-padding: 10;");
            container.setEffect(new DropShadow(10, Color.BLACK));
            container.setPrefSize(300, 300);
        }

        chartsContainer.getChildren().addAll(container1, container2);
    }

    private void populateChartData(PieChart chart, String type) {
        chart.getData().clear();

        switch (currentSelectedMonth) {
            case "Jan":
                if (type.equals("ventes")) {
                    chart.getData().addAll(
                            new PieChart.Data("Catégorie A", 40),
                            new PieChart.Data("Catégorie B", 30),
                            new PieChart.Data("Catégorie C", 20),
                            new PieChart.Data("Catégorie D", 10)
                    );
                } else {
                    chart.getData().addAll(
                            new PieChart.Data("Produit X", 50),
                            new PieChart.Data("Produit Y", 25),
                            new PieChart.Data("Produit Z", 15),
                            new PieChart.Data("Produit W", 10)
                    );
                }
                break;
            case "Fév":
                if (type.equals("ventes")) {
                    chart.getData().addAll(
                            new PieChart.Data("Catégorie A", 25),
                            new PieChart.Data("Catégorie B", 25),
                            new PieChart.Data("Catégorie C", 25),
                            new PieChart.Data("Catégorie D", 25)
                    );
                } else {
                    chart.getData().addAll(
                            new PieChart.Data("Produit X", 20),
                            new PieChart.Data("Produit Y", 30),
                            new PieChart.Data("Produit Z", 25),
                            new PieChart.Data("Produit W", 25)
                    );
                }
                break;
            case "Mar":
                if (type.equals("ventes")) {
                    chart.getData().addAll(
                            new PieChart.Data("Catégorie A", 10),
                            new PieChart.Data("Catégorie B", 20),
                            new PieChart.Data("Catégorie C", 30),
                            new PieChart.Data("Catégorie D", 40)
                    );
                } else {
                    chart.getData().addAll(
                            new PieChart.Data("Produit X", 15),
                            new PieChart.Data("Produit Y", 25),
                            new PieChart.Data("Produit Z", 35),
                            new PieChart.Data("Produit W", 25)
                    );
                }
                break;
            case "Avr":
                if (type.equals("ventes")) {
                    chart.getData().addAll(
                            new PieChart.Data("Catégorie A", 20),
                            new PieChart.Data("Catégorie B", 30),
                            new PieChart.Data("Catégorie C", 30),
                            new PieChart.Data("Catégorie D", 20)
                    );
                } else {
                    chart.getData().addAll(
                            new PieChart.Data("Produit X", 30),
                            new PieChart.Data("Produit Y", 30),
                            new PieChart.Data("Produit Z", 20),
                            new PieChart.Data("Produit W", 20)
                    );
                }
                break;
            case "Mai":
                if (type.equals("ventes")) {
                    chart.getData().addAll(
                            new PieChart.Data("Catégorie A", 15),
                            new PieChart.Data("Catégorie B", 20),
                            new PieChart.Data("Catégorie C", 30),
                            new PieChart.Data("Catégorie D", 35)
                    );
                } else {
                    chart.getData().addAll(
                            new PieChart.Data("Produit X", 25),
                            new PieChart.Data("Produit Y", 25),
                            new PieChart.Data("Produit Z", 25),
                            new PieChart.Data("Produit W", 25)
                    );
                }
                break;
            default:
                chart.getData().addAll(
                        new PieChart.Data("A", 25),
                        new PieChart.Data("B", 25),
                        new PieChart.Data("C", 25),
                        new PieChart.Data("D", 25)
                );
        }
    }


    private void loadSales() {
        salesContainer.getChildren().clear();
        Label salesTitle = new Label("Résumé des ventes");
        salesTitle.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        Label details = new Label("Total: $2000 | Nombre: 50");
        details.setStyle("-fx-text-fill: white;");
        salesContainer.getChildren().addAll(salesTitle, details);
    }
}
