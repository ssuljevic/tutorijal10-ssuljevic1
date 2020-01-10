package ba.unsa.etf.rpr.tutorijal10;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;

public class GlavnaController {
    DrzavaController drzavaController;
    GradController gradController;
    GradController izmijeniGradController;
    public TableView<Grad> tableviewGrad;
    public TableColumn<Grad, String> colGradId;
    public TableColumn<Grad, String> colGradNaziv;
    public TableColumn<Grad, String> colGradStanovnika;
    public TableColumn<Grad, String> colGradDrzava;

    public Button btnObrisiGrad;
    private  GeografijaModel dao = GeografijaModel.dajInstancu();
    public void otvoriDrzavu(ActionEvent actionEvent) {
        Parent root = null;
        try {
            Stage myStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/drzava.fxml"));
            loader.load();
            drzavaController = loader.getController();

            myStage.setTitle("Država");
            myStage.setScene(new Scene(loader.getRoot(), 350, 105));
            myStage.setResizable(false);
            myStage.show();
            Grad g = new Grad(0, "", 0, null);

            myStage.setOnHiding(event -> btnObrisiGrad.setText(drzavaController.fieldNaziv.getText()));

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void otvoriGrad(ActionEvent actionEvent) {
        Parent root = null;

        try {
            Stage myStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"));
            loader.load();
            gradController = loader.getController();

            myStage.setTitle("Grad");
            myStage.setScene(new Scene(loader.getRoot(), 450, 210));
            myStage.setResizable(false);
            myStage.show();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void izmijeniGrad(ActionEvent actionEvent) {
        try {
            Stage myStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"));
            loader.load();
           izmijeniGradController = loader.getController();

            myStage.setTitle("Grad");
            myStage.setScene(new Scene(loader.getRoot(), 450, 210));
            myStage.setResizable(false);
            myStage.show();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void obrisiGrad(ActionEvent actionEvent) {
        int selektovani = tableviewGrad.getSelectionModel().getSelectedIndex();
        tableviewGrad.getItems().remove(tableviewGrad.getSelectionModel().getFocusedIndex());
        tableviewGrad.getSelectionModel().select(selektovani);
    }
    public void resetujBazu() {
        GeografijaModel.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        dao = GeografijaModel.dajInstancu();
    }

    @FXML
    public void initialize() {
        colGradId.setCellValueFactory(new PropertyValueFactory<Grad, String>("id"));
        colGradNaziv.setCellValueFactory(new PropertyValueFactory<Grad, String>("naziv"));
        colGradDrzava.setCellValueFactory(new PropertyValueFactory<Grad, String>("drzava"));
        colGradStanovnika.setCellValueFactory(new PropertyValueFactory<Grad, String>("brojStanovnika"));
        tableviewGrad.setItems(dao.getGradovi());
        tableviewGrad.getSelectionModel().selectedItemProperty().addListener((obs, oldGrad, newGrad) -> {
            dao.setTrenutniGrad(newGrad);
            tableviewGrad.refresh();
        });





    }



}

