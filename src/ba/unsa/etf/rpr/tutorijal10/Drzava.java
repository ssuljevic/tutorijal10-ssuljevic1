package ba.unsa.etf.rpr.tutorijal10;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Drzava {

    private SimpleIntegerProperty id;
    private SimpleStringProperty naziv;
    private SimpleStringProperty glavniGrad;

    public Drzava(int id, String naziv, String glavniGrad) {
        this.id = new SimpleIntegerProperty(id);
        this.naziv = new SimpleStringProperty(naziv);
        this.glavniGrad = new SimpleStringProperty(glavniGrad);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNaziv() {
        return naziv.get();
    }

    public SimpleStringProperty nazivProperty() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv.set(naziv);
    }

    public String getGlavniGrad() {
        return glavniGrad.get();
    }

    public SimpleStringProperty glavniGradProperty() {
        return glavniGrad;
    }

    public void setGlavniGrad(String glavniGrad) {
        this.glavniGrad.set(glavniGrad);
    }
}
