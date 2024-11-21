module org.example.higherorlowergame {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.higherorlowergame to javafx.fxml;
    exports org.example.higherorlowergame;
}