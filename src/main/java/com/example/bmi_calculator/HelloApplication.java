package com.example.bmi_calculator;

import MODELS.User;
import MODELS.UserManager;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private UserManager manager ;
    private ObservableList<User> usersData;

    public HelloApplication() {
        manager = new UserManager();
        usersData = manager.getUsersList();
    }
    @Override
    public void start(Stage stage) {
        Label nameLabel = new Label("Name:");
        nameLabel.setStyle("-fx-font-weight: bold;");
        TextField nameField = new TextField();

        Label DOBlabel = new Label("Date Of Birth:");
        DOBlabel.setStyle("-fx-font-weight: bold;");
        DatePicker DOBPicker = new DatePicker();

        Label weightLabel = new Label("Weight (kg):");
        weightLabel.setStyle("-fx-font-weight: bold;");
        TextField weightField = new TextField();

        Label heightLabel = new Label("Height (cm):");
        heightLabel.setStyle("-fx-font-weight: bold;");
        TextField heightField = new TextField();

        Button clear = new Button("Clear");
        clear.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        clear.setOnAction(e -> {
            nameField.clear();
            DOBPicker.setValue(null);
            weightField.clear();
            heightField.clear();
        });

        Button bmi = new Button("Get BMI");
        bmi.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        bmi.setOnAction(e -> {
            String name = nameField.getText();
            String DOB = DOBPicker.getValue().toString();
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            User user = new User(name, DOB, weight, height,"UNKOWN");
            String response = manager.calculateBMI(user);
            manager.showResponse(response);
            nameField.clear();
            DOBPicker.setValue(null);
            weightField.clear();
            heightField.clear();
        });



        HBox buttonsLayout = new HBox(20);
        buttonsLayout.getChildren().addAll(bmi, clear);
        buttonsLayout.setAlignment(Pos.CENTER);

        GridPane formlayout = new GridPane();
        formlayout.setHgap(10);
        formlayout.setVgap(10);
        formlayout.setPadding(new Insets(20, 20, 20, 20));
        formlayout.add(nameLabel, 0, 0);
        formlayout.add(nameField, 1, 0);
        formlayout.add(DOBlabel, 0, 1);
        formlayout.add(DOBPicker, 1, 1);
        formlayout.add(weightLabel, 0, 2);
        formlayout.add(weightField, 1, 2);
        formlayout.add(heightLabel, 0, 3);
        formlayout.add(heightField, 1, 3);
        formlayout.add(buttonsLayout, 1, 4);

        TableView<User> usersTable = new TableView<>();
        usersTable.setItems(usersData);
        usersTable.setStyle("-fx-background-color: #f4f4f9;");

        TableColumn<User, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<User, String> dobColumn = new TableColumn<>("DOB");
        dobColumn.setCellValueFactory(cellData -> cellData.getValue().dobProperty());

        TableColumn<User, Double> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());

        TableColumn<User, Double> heightColumn = new TableColumn<>("Height");
        heightColumn.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());

        TableColumn<User, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());

        idColumn.setStyle("-fx-alignment: CENTER;");
        nameColumn.setStyle("-fx-alignment: CENTER;");
        dobColumn.setStyle("-fx-alignment: CENTER;");
        weightColumn.setStyle("-fx-alignment: CENTER;");
        heightColumn.setStyle("-fx-alignment: CENTER;");
        categoryColumn.setStyle("-fx-alignment: CENTER;");

        usersTable.getColumns().addAll(idColumn, nameColumn, dobColumn, weightColumn, heightColumn, categoryColumn);

        HBox mainLayout = new HBox(20);
        mainLayout.getChildren().addAll(formlayout, usersTable);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(50, 50, 50, 50));

        mainLayout.setStyle("-fx-background-color: #e3f2fd;");

        Scene scene = new Scene(mainLayout, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("BMI Calculator");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
