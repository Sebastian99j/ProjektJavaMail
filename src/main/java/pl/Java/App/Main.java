package pl.Java.App;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.Java.Controllers.MainController;

import java.io.File;

public class Main extends Application {

    @FXML
    public static Stage stageMain;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stageMain = stage;
        FXMLLoader loader = new FXMLLoader(new File("/login.fxml").toURI().toURL());
        loader.setLocation(this.getClass().getResource("/login.fxml"));
        AnchorPane anchorPane = loader.load();

        MainController controller = loader.getController();

        Scene scene = new Scene(anchorPane);
        stage.setTitle("Logowanie");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}