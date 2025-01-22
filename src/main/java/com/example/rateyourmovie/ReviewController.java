package com.example.rateyourmovie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ReviewController {
    @FXML
    private Label OtherName;

    @FXML
    private Label OtherRating;

    @FXML
    private Label OtherReview;

    @FXML
    private Label e11;

    @FXML
    private AnchorPane reviewModel;

    public void setData(String name, String rating, String review){
        OtherName.setText(name);
        OtherRating.setText(rating);
        OtherReview.setText(review);

        OtherReview.setStyle("-fx-text-fill: black");
        OtherRating.setStyle("-fx-text-fill: black");
        OtherName.setStyle("-fx-text-fill: black");
        e11.setStyle("-fx-text-fill: black");
    }
}
