package com.example.collections;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        //Nodes
        Text txtNote = new Text("Notification");
        txtNote.setFont(Font.font("Comic Sans MS",25));
        txtNote.setFill(Color.GREEN);

        TextField fldAdd = new TextField();
        fldAdd.setPromptText("Add Field");

        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(85);
        Button btnRemove = new Button("Remove");
        btnRemove.setMinWidth(85);

        //ListView and Observable List
        ListView<String> listList = new ListView<>();
        ObservableList<String> animals = FXCollections.observableArrayList();
        listList.setItems(animals);
        listList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listList.setMaxSize(300, 300);

        //ObservableList ChangeListener
        animals.addListener(new ListChangeListener() {

            @Override
            public void onChanged(ListChangeListener.Change change) {

                txtNote.setText("ListView Successfully changed");
            }
        });


        //Button Add
        btnAdd.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {

                if(!fldAdd.getText().isEmpty()){
                    if(fldAdd.getText().contains(",")){
                        txtNote.setText("Please use AddAll button to add 2+ items");
                    }else{

                        String text=fldAdd.getText();
                        Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z]{4,}$");
                        Matcher matcher = pattern.matcher(text);
                        StringBuilder matches = new StringBuilder();
                        if(matcher.find())
                        {
                            animals.add(fldAdd.getText());
                        } else {
                            txtNote.setText("Name must start with uppercase an at least 5 characters");
                        }

                    }
                }else{
                    txtNote.setText("Please add information to Add field");
                }
                listList.getSelectionModel().clearSelection();
            }
        });

        //Button Remove
        btnRemove.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {

                if(!listList.getSelectionModel().isEmpty()){
                    if(listList.getSelectionModel().getSelectedItems().size() > 1){
                        txtNote.setText("Please use RemoveAll button to remove 2+ items");
                    }else{
                        animals.remove(listList.getSelectionModel().getSelectedItem());
                    }
                }else{
                    txtNote.setText("Select at least 1 Element on the ListView");
                }
                listList.getSelectionModel().clearSelection();
            }
        });

        //Pane Left Right
        VBox right = new VBox(10);
        right.setPadding(new Insets(10));
        right.setAlignment(Pos.CENTER);
        right.getChildren().addAll(fldAdd, btnAdd, btnRemove);

        //Root Node
        BorderPane root = new BorderPane();

        root.setCenter(listList);
        root.setRight(right);
        root.setBottom(txtNote);


        Scene scene = new Scene(root, 800, 500);

        primaryStage.setTitle("JavaFX 8 - FXCollections");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}