module number.translator {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens number.translator to javafx.fxml;
    exports number.translator;
}
