package ba.unsa.etf.rpr.tutorijal10;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class GlavnaController {
    DrzavaController drzavaController;
    public TableView<Grad> tableViewGrad;
    public Button btnObrisiGrad;
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
}

