package edu.kit.csv_viewer;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.util.List;

public class Controller {

    @FXML
    private TableView<List<String>> tableView;

    @FXML
    protected void onButtonClick(MouseEvent event) {
        final var button = (Button) event.getSource();
        var fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        final var file = fileChooser.showOpenDialog(button.getScene().getWindow());
        if (file != null) {
            final var data = CSV.read(file.toString());
            if (!data.isEmpty()) {
                tableView.getColumns().clear();
                List<String> headers = data.getFirst();
                for (int i = 0; i < headers.size(); ++i) {
                    TableColumn<List<String>, String> col = new TableColumn<>(headers.get(i));
                    final int colIndex = i;
                    col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(colIndex)));
                    tableView.getColumns().add(col);
                }
                ObservableList<List<String>> items = FXCollections.observableArrayList();
                items.addAll(data.subList(1, data.size()));
                tableView.setItems(items);
            }
        }
    }
}
