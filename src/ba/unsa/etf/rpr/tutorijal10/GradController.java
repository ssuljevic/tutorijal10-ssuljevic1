package ba.unsa.etf.rpr.tutorijal10;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GradController {
    public TextField fieldNaziv;
    public TextField fieldBrojStanovnika;

    private boolean ispravnoPoljeBrojStanovnika(int a ){
       return  a > 0;
    }
    @FXML
    public void initialize() {
        fieldBrojStanovnika = new TextField("");
        if( fieldNaziv.getText().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
        }
        if( fieldBrojStanovnika.getText().toString().length() == 0) {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
        }

        fieldBrojStanovnika.textProperty().addListener((obs, old, novo) -> {
           if(!novo.isEmpty()) {
               fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
               fieldBrojStanovnika.getStyleClass().add("poljeIspravno");
           }
           else {
               fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
               fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
           }
           if( Integer.parseInt(novo) < 0) {
               fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
               fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
           }
           else {
               fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
               fieldBrojStanovnika.getStyleClass().add("poljeIspravno");
           }

        });

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

    public TextField getFieldNaziv() {
        return fieldNaziv;
    }

    public void setFieldNaziv(TextField fieldNaziv) {
        this.fieldNaziv = fieldNaziv;
    }
}
