package com.example.boxchat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


public class ChatController {
    @FXML
    private ListView<String> messageList;
    @FXML
    private TextField inputField;
    @FXML
    private Button sendButton;

    @FXML
    public void initialize() {
        Platform.runLater(() -> inputField.requestFocus());

        sendButton.setOnAction(event -> {
            if (inputField.getText().isEmpty()) {
                return;
            }

            messageList.getItems().add(" " + inputField.getText() + " ");
            messageList.getItems().add(" Auto response: " + inputField.getText() + " ");

            inputField.clear();
        });

        inputField.setOnAction(event -> {
            if (inputField.getText().isEmpty()) {
                return;
            }

            messageList.getItems().add(" " + inputField.getText() + " ");
            messageList.getItems().add(" Auto response: " + inputField.getText() + " ");

            inputField.clear();
        });

        messageList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                Label label = null;
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    label = new Label(item);
                    label.setText(item);
                    label.setTextFill(Color.WHITE);
                    label.setPrefWidth(label.getText().length() * 6); // adjust this value as needed
                    if (item.startsWith(" Auto response: ")) {
                        label.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, new CornerRadii(20), null)));
                    } else {
                        label.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(20), null)));
                    }
                    setGraphic(label);
                    if (item.startsWith(" Auto response: ")) {
                        setAlignment(Pos.CENTER_RIGHT); // Align to the right
                        label.setStyle("-fx-alignment: TOP-RIGHT;");
                    } else {
                        setAlignment(Pos.CENTER_LEFT); // Align to the left
                        label.setStyle("-fx-alignment: TOP-LEFT;");
                    }
                }
            }
        });
    }
}