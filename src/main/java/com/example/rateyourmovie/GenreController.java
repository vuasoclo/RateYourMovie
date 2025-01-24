package com.example.rateyourmovie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class GenreController {
    @FXML
    private Label genreLabel;

    private String getColorBasedOnLength(int length) {
        int index = (length+5) % colors.length;
        return colors[index];
    }

    private final String[] colors = {"#FEBA54", "#B9E5FF", "#BDB2FE", "#FB9AA8", "#FE686A", "#92DC75"};

    public void setData(String genre) {
        if (genre == null) {
            return;
        }
        genreLabel.setText(genre);
        int length = genre.length();
        genreLabel.setStyle("-fx-background-color: " + getColorBasedOnLength(length));
    }
}
