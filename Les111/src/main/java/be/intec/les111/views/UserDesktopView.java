
package be.intec.les111.views;

import be.intec.les111.exceptions.UserException;
import be.intec.les111.models.entities.UserEntity;
import be.intec.les111.repositories.UserRepository;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

import be.intec.les111.models.binders.*;

public class UserDesktopView extends Application {

    private final TableView<UserBinder> table = new TableView<>();
    private final ObservableList<UserBinder> data = FXCollections.observableArrayList();
    private final UserRepository repository = new UserRepository();
    final HBox actBox = new HBox();
    final HBox navBox = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(new Group());
        stage.setTitle("User Desktop View");
        stage.setWidth(500);
        stage.setHeight(650);

        final Label header = new Label("User Records");
        header.setFont(new Font("Arial", 14));

        final Label message = new Label("Status: Form loaded.");
        header.setFont(new Font("Arial", 10));

        table.setEditable(true);

        TableColumn column0 = new TableColumn("id");
        column0.setCellValueFactory(
                new PropertyValueFactory<UserBinder, Integer>("id"));
        column0.setEditable(false);
        TableColumn column1 = new TableColumn("email");
        column1.setCellValueFactory(
                new PropertyValueFactory<UserBinder, String>("email"));
        column1.setEditable(true);
        column1.setOnEditCommit((EventHandler<CellEditEvent<UserBinder, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO


                    // UPDATE TABLE COLUMN

                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setEmail(t.getNewValue());
                }
        );
        TableColumn column2 = new TableColumn("passcode");
        column2.setCellValueFactory(
                new PropertyValueFactory<UserBinder, String>("passcode"));
        column2.setEditable(true);
        column2.setOnEditCommit((EventHandler<CellEditEvent<UserBinder, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO


                    // UPDATE TABLE COLUMN

                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setPasscode(t.getNewValue());
                }
        );

        table.setItems(data);
        table.getColumns().addAll(column0, column1, column2);

        final TextField column1EditField = new TextField();
        column1EditField.setPromptText("email");
        column1EditField.setMaxWidth(column1.getPrefWidth());
        final TextField column2EditField = new TextField();
        column2EditField.setPromptText("passcode");
        column2EditField.setMaxWidth(column2.getPrefWidth());

        final Button addButton = new Button("Add");
        addButton.setOnAction(onClick -> {

            // SAVE NEW RECORD TO DB
            try {
                int noOfNewRecords = repository.create(
                        new UserEntity()
                                .withParsedEmail(column1EditField.getText())
                                .withParsedPasscode(column2EditField.getText())
                );

                if (noOfNewRecords > 0) {

                    // CLEAR TABLE
                    table.getItems().clear();

                    // MAP DB RECORDS TO VIEW COMPONENTS
                    List<UserEntity> itemList = repository.read();

                    for (UserEntity item : itemList) {
                        data.add(new UserBinder(
                                item.getId(),
                                item.getEmail(),
                                item.getPasscode()
                        ));
                    }

                    table.setItems(data);

                    message.setText("New records from User are created and to the list.");
                    column1EditField.clear();
                    column2EditField.clear();

                    message.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                    message.setText("User created!");

                } else {
                    message.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                    message.setText("ERROR: User could NOT be created!");
                }
            } catch (UserException userException) {
                message.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                message.setText(userException.getMessage());
            }

        });

        final Button viewButton = new Button("View");
        viewButton.setOnAction(onClick -> {

            // CLEAR TABLE
            table.getItems().clear();

            // MAP DB RECORDS TO VIEW COMPONENTS
            List<UserEntity> itemList = repository.read();

            for (UserEntity item : itemList) {
                data.add(new UserBinder(
                        item.getId(),
                        item.getEmail(),
                        item.getPasscode()
                ));
            }

            table.setItems(data);

            message.setText("User records are refreshed");

        });

        final Button backButton = new Button("Back");
        backButton.setOnAction(onClick -> {
            scene.getWindow().hide();
        });

        navBox.getChildren().addAll(backButton);
        navBox.setSpacing(3);

        actBox.getChildren().addAll(column1EditField, column2EditField, addButton, viewButton);
        actBox.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(header, table, actBox, navBox, message);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);

        stage.show();
    }

}

