module edu.kit.csv_viewer {
    requires javafx.controls;
    requires javafx.fxml;
    opens edu.kit.csv_viewer to javafx.fxml;
    exports edu.kit.csv_viewer;
}
