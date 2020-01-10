package ba.unsa.etf.rpr.tutorijal10;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Grad {
    private SimpleIntegerProperty id;
    private SimpleStringProperty naziv;
    private SimpleIntegerProperty brojStanovnika;
    private SimpleStringProperty drzava;

    public Grad(int id, String naziv, int brojStanovnika, String drzava) {
        this.id = new SimpleIntegerProperty(id);
        this.naziv = new SimpleStringProperty(naziv);
        this.brojStanovnika = new SimpleIntegerProperty(brojStanovnika);
        this.drzava = new SimpleStringProperty(drzava);
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

    public int getBrojStanovnika() {
        return brojStanovnika.get();
    }

    public SimpleIntegerProperty brojStanovnikaProperty() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika.set(brojStanovnika);
    }

    public String getDrzava() {
        return drzava.get();
    }

    public SimpleStringProperty drzavaProperty() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava.set(drzava);
    }
}
