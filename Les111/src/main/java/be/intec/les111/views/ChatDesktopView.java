
package be.intec.les111.views;

import be.intec.les111.exceptions.ChatException;
import be.intec.les111.models.entities.ChatEntity;
import be.intec.les111.repositories.ChatRepository;
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

public class ChatDesktopView extends Application {

    private final TableView<ChatBinder> table = new TableView<>();
    private final ObservableList<ChatBinder> data = FXCollections.observableArrayList();
    private final ChatRepository repository = new ChatRepository();
    final HBox actBox = new HBox();
    final HBox navBox = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(new Group());
        stage.setTitle("Chat Desktop View");
        stage.setWidth(500);
        stage.setHeight(650);

        final Label header = new Label("Chat Records");
        header.setFont(new Font("Arial", 14));

        final Label message = new Label("Status: Form loaded.");
        header.setFont(new Font("Arial", 10));

        table.setEditable(true);

        TableColumn column0 = new TableColumn("id");
        column0.setCellValueFactory(
                new PropertyValueFactory<ChatBinder, Integer>("id"));
        column0.setEditable(false);
        TableColumn column1 = new TableColumn("fromUserId");
        column1.setCellValueFactory(
                new PropertyValueFactory<ChatBinder, Integer>("fromUserId"));
        column1.setEditable(true);
        column1.setOnEditCommit((EventHandler<CellEditEvent<ChatBinder, Integer>>) t -> {

                    // UPDATE COLUMN IN THE REPO


                    // UPDATE TABLE COLUMN

                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setFromUserId(t.getNewValue());
                }
        );
        TableColumn column2 = new TableColumn("toUserId");
        column2.setCellValueFactory(
                new PropertyValueFactory<ChatBinder, Integer>("toUserId"));
        column2.setEditable(true);
        column2.setOnEditCommit((EventHandler<CellEditEvent<ChatBinder, Integer>>) t -> {

                    // UPDATE COLUMN IN THE REPO


                    // UPDATE TABLE COLUMN

                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setToUserId(t.getNewValue());
                }
        );
        TableColumn column3 = new TableColumn("subject");
        column3.setCellValueFactory(
                new PropertyValueFactory<ChatBinder, String>("subject"));
        column3.setEditable(true);
        column3.setOnEditCommit((EventHandler<CellEditEvent<ChatBinder, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO


                    // UPDATE TABLE COLUMN

                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setSubject(t.getNewValue());
                }
        );
        TableColumn column4 = new TableColumn("content");
        column4.setCellValueFactory(
                new PropertyValueFactory<ChatBinder, String>("content"));
        column4.setEditable(true);
        column4.setOnEditCommit((EventHandler<CellEditEvent<ChatBinder, String>>) t -> {

                    // UPDATE COLUMN IN THE REPO


                    // UPDATE TABLE COLUMN

                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setContent(t.getNewValue());
                }
        );

        table.setItems(data);
        table.getColumns().addAll(column0, column1, column2, column3, column4);

        final TextField column1EditField = new TextField();
        column1EditField.setPromptText("fromUserId");
        column1EditField.setMaxWidth(column1.getPrefWidth());
        final TextField column2EditField = new TextField();
        column2EditField.setPromptText("toUserId");
        column2EditField.setMaxWidth(column2.getPrefWidth());
        final TextField column3EditField = new TextField();
        column3EditField.setPromptText("subject");
        column3EditField.setMaxWidth(column3.getPrefWidth());
        final TextField column4EditField = new TextField();
        column4EditField.setPromptText("content");
        column4EditField.setMaxWidth(column4.getPrefWidth());

        final Button addButton = new Button("Add");
        addButton.setOnAction(onClick -> {

            // SAVE NEW RECORD TO DB
            try {
                int noOfNewRecords = repository.create(
                        new ChatEntity()
                                .withParsedFromUserId(column1EditField.getText())
                                .withParsedToUserId(column2EditField.getText())
                                .withParsedSubject(column3EditField.getText())
                                .withParsedContent(column4EditField.getText())
                );

                if (noOfNewRecords > 0) {

                    // CLEAR TABLE
                    table.getItems().clear();

                    // MAP DB RECORDS TO VIEW COMPONENTS
                    List<ChatEntity> itemList = repository.read();

                    for (ChatEntity item : itemList) {
                        data.add(new ChatBinder(
                                item.getId(),
                                item.getFromUserId(),
                                item.getToUserId(),
                                item.getSubject(),
                                item.getContent()
                        ));
                    }

                    table.setItems(data);

                    message.setText("New records from Chat are created and to the list.");
                    column1EditField.clear();
                    column2EditField.clear();
                    column3EditField.clear();
                    column4EditField.clear();

                    message.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                    message.setText("Chat created!");

                } else {
                    message.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                    message.setText("ERROR: Chat could NOT be created!");
                }
            } catch (ChatException chatException) {
                message.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                message.setText(chatException.getMessage());
            }

        });

        final Button viewButton = new Button("View");
        viewButton.setOnAction(onClick -> {

            // CLEAR TABLE
            table.getItems().clear();

            // MAP DB RECORDS TO VIEW COMPONENTS
            List<ChatEntity> itemList = repository.read();

            for (ChatEntity item : itemList) {
                data.add(new ChatBinder(
                        item.getId(),
                        item.getFromUserId(),
                        item.getToUserId(),
                        item.getSubject(),
                        item.getContent()
                ));
            }

            table.setItems(data);

            message.setText("Chat records are refreshed");

        });

        final Button backButton = new Button("Back");
        backButton.setOnAction(onClick -> {
            scene.getWindow().hide();
        });

        navBox.getChildren().addAll(backButton);
        navBox.setSpacing(3);

        actBox.getChildren().addAll(column1EditField, column2EditField, column3EditField, column4EditField, addButton, viewButton);
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

