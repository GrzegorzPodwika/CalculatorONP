import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("layouts/main_layout.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Kalkulator ONP");
        primaryStage.setScene(new Scene(root));

        primaryStage.setMinHeight(400.0);
        primaryStage.setMinWidth(300.0);
        primaryStage.setMaxHeight(900);
        primaryStage.setMaxWidth(800);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
