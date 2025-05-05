package com.fisange.controllers.components;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class HeaderController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label userLabel;

    @FXML
    private Label timeLabel;

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Timer timer;

    @FXML
    public void initialize() {
        startClock();
    }

    public void setUserName(String name) {
        userLabel.setText("About me: " + name);
    }

    private void startClock() {
        timer = new Timer(true); // daemon
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    String currentTime = LocalTime.now().format(timeFormatter);
                    timeLabel.setText(currentTime);
                });
            }
        }, 0, 1000);
    }

    public void stopClock() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
