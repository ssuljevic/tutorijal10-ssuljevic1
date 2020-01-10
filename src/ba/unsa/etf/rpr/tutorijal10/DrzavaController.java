package ba.unsa.etf.rpr.tutorijal10;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DrzavaController {

    public TextField fieldNaziv = new TextField("");

    @FXML
    public void initialize() {
        if( fieldNaziv.getText().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
        }
        fieldNaziv.textProperty().addListener((obs, old, novo) -> {
            if( novo.isEmpty()) {
                fieldNaziv.getStyleClass().removeAll("poljeIspravno");
                fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            }
            else {
                fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
                fieldNaziv.getStyleClass().add("poljeIspravno");
            }


        });
    }

    public void zatvori(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }





    public String getFieldNaziv() {
        return fieldNaziv.toString();
    }


    public void setFieldNaziv(TextField fieldNaziv) {
        this.fieldNaziv = fieldNaziv;
    }
}
