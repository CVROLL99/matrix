package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static Controller.MatrixController.a;
import static Controller.MatrixController.matrixData;

/**
 * Created by Misha on 06.06.2017.
 */
public class ChartController {

    @FXML
    public Button backBtn;
    @FXML
    public LineChart xyChart;


    /**
     * Метод возвращает нас назад на главное окно
     * @param mouseEvent
     * @throws IOException
     */
    @FXML
    public void onBack (MouseEvent mouseEvent) throws IOException {
        Parent chartPageParent = FXMLLoader.load(getClass().getResource("/jfx/matrix.fxml"));
        Scene chartPageScene = new Scene(chartPageParent);
        Stage appStage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(chartPageScene);
        appStage.setTitle("Работа моя");
        appStage.show();
    }

    @FXML
    public void initialize(){



    }
}
