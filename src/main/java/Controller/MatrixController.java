package Controller;

import POJO.Massive;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Misha on 05.06.2017.
 */
public class MatrixController {

    public static double a,b,k,p,h;
    public static ObservableList matrixData = FXCollections.observableArrayList();

    @FXML
    public Button btnOk = new Button();
    @FXML
    public Button btnClr = new Button();
    @FXML
    public TextField aTextfield;
    @FXML
    public TextField bTextField;
    @FXML
    public TextField kTextField;
    @FXML
    public TextField pTextfield;
    @FXML
    public TextField hTextField;
    @FXML
    public TableView<Massive> solutionTable;
    @FXML
    public TableColumn<Massive,Double> xTableColumn;
    @FXML
    public TableColumn<Massive,Double> yTableColumn;
    @FXML
    public Button chartBtn;
    @FXML
    public Button sredBtn;
    @FXML
    public Label sredLabel;
    @FXML
    public Button fileBtn;


    /**
     * Кнопка ОК
     */
    @FXML
    public void okBtnAction() {
        prov();
        clearData();
        massiveGet();

        xTableColumn.setCellValueFactory(new PropertyValueFactory<Massive, Double>("xval"));
        yTableColumn.setCellValueFactory(new PropertyValueFactory<Massive, Double>("yval"));
        solutionTable.setItems(matrixData);

       sred();

    }

    /**
     * Данный метод сохраняет все в файл
     */
    public void onFile(){
        prov();
        a = Double.parseDouble(aTextfield.getText());
        b = Double.parseDouble(bTextField.getText());
        k = Double.parseDouble(kTextField.getText());
        p = Double.parseDouble(pTextfield.getText());
        h = Double.parseDouble(hTextField.getText());
        FileChooser fileChooser = new FileChooser();

        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем диалог сохранения файла
        File file = fileChooser.showSaveDialog(null);


        PrintWriter out = null;
        try {
            out = new PrintWriter(file.getAbsoluteFile());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
                out.print("Граница отрезка а = "+aTextfield.getText()+'\r'+'\n');
                out.print("Граница отрезка b = "+bTextField.getText()+'\r'+'\n');
                out.print("k = "+kTextField.getText()+'\r'+'\n');
                out.print("p = "+pTextfield.getText()+'\r'+'\n');
                out.print("Шаг h = "+hTextField.getText()+'\r'+'\n');
                out.print(sredLabel.getText()+'\r'+'\n');
                out.print("**Таблица значений функции**"+'\r'+'\n');
                while(a<=b){
                    double xval = a;
                    xval =  Math.rint(xval * 100) / 100;
                    a = a+h;
                    out.print(xval+" ");

                }
                out.print(""+'\r'+'\n');
                a = Double.parseDouble(aTextfield.getText());
                while(a<=b){
                    double xval = a;
                    xval =  Math.rint(xval * 100) / 100;
                    double yval = (a-(k*a*a*a))/p;
                    a = a+h;
                    yval =  Math.rint(yval * 100) / 100;

                    out.print(yval+" ");

                }

                JOptionPane.showMessageDialog(null,"Записано успешно!");
            } finally {
                out.close();
            }
        }



    /**
     * Проверка на ввод значений
     * @param keyEvent
     */
    public void onKeyLetter(KeyEvent keyEvent) {
        String c = keyEvent.getCharacter();
        if ("1234567890.,-+".contains(c)) {

        } else {
            keyEvent.consume();
            JOptionPane.showMessageDialog(null, "Разрешается вводить только цифры!");

        }
    }

    /**
     * Проверка параметров не введенного текста
     */
    public void prov(){
        if(aTextfield.getText().length() <= 0){
            JOptionPane.showMessageDialog(null, "Вы не ввели А");
            aTextfield.requestFocus();
        }
        if(bTextField.getText().length() <= 0){
            JOptionPane.showMessageDialog(null, "Вы не ввели b");
            bTextField.requestFocus();
        }
        if(kTextField.getText().length() <= 0){
            JOptionPane.showMessageDialog(null, "Вы не ввели k");
            kTextField.requestFocus();
        }
        if(pTextfield.getText().length() <= 0){
            JOptionPane.showMessageDialog(null, "Вы не ввели p");
            pTextfield.requestFocus();
        }
        if(hTextField.getText().length() <= 0){
            JOptionPane.showMessageDialog(null, "Вы не ввели k");
            hTextField.requestFocus();
        }
    }


    /**
     * Данный метод вычисляет среднее значение
     */
    public void sred(){
        a = Double.parseDouble(aTextfield.getText());
        b = Double.parseDouble(bTextField.getText());
        k = Double.parseDouble(kTextField.getText());
        p = Double.parseDouble(pTextfield.getText());
        h = Double.parseDouble(hTextField.getText());


        double rez = 0;
        int count=0;
        while (a <= b) {
            count++;
            a = a + h;
            double yval = (a-(k*a*a*a))/p;
            yval =  Math.rint(yval * 100) / 100;
            rez =(rez + yval);

        }
        double sred = rez/count;
        sred = Math.rint(sred* 100) / 100;
        sredLabel.setText("Среднее значение: "+ sred);
    }



    /**
     * Данный метод составляет значения х на отрезке a,b
     */
    private void massiveGet() {
        a = Double.parseDouble(aTextfield.getText());
        b = Double.parseDouble(bTextField.getText());
        k = Double.parseDouble(kTextField.getText());
        p = Double.parseDouble(pTextfield.getText());
        h = Double.parseDouble(hTextField.getText());

        if (p == 0) {
            JOptionPane.showMessageDialog(null, "P не может быть 0!");
            System.exit(0);
        }

        if(h > Math.abs(b)){
            JOptionPane.showMessageDialog(null, "Шаг не может быть больше диапазона!");
            System.exit(0);
        }

        if(h <=0){
            JOptionPane.showMessageDialog(null, "H должно быть больше нуля");
            System.exit(0);
        }
        while(a <= b){
            double xval = a;

            xval =  Math.rint(xval * 100) / 100;
            double yval = (a-(k*a*a*a))/p;
            a = a+h;
            yval =  Math.rint(yval * 100) / 100;
            matrixData.add(new Massive(xval,yval));

        }
    }


    /**
     * Метод очищает коллекцию data
     */
    public void clearData(){
        matrixData.clear();
    }


    /**
     * Метод кнопки очистка
     * @param actionEvent
     */
    public void onClrAction(ActionEvent actionEvent) {
        clearData();
        sredLabel.setText("Среднее значение: ");
        aTextfield.clear();
        bTextField.clear();
        kTextField.clear();
        pTextfield.clear();
        hTextField.clear();
    }
}

