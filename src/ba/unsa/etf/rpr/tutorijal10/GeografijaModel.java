package ba.unsa.etf.rpr.tutorijal10;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaModel {

    private ObservableList<Grad> gradovi = FXCollections.observableArrayList();
    private ObjectProperty<Grad> trenutniGrad = new SimpleObjectProperty<>();
    private ObservableList<Drzava> drzave = FXCollections.observableArrayList();
    private ObjectProperty<Drzava> trenutnaDrzava = null;

    private static GeografijaModel instance = null;
    private PreparedStatement glavniGradUpit, dajGradUpit, obrisiGradoveZaDrzavuUpit, obrisiDrzavuUpit, nadjiDrzavuUpit, dajGradoveUpit, dodajGradUpit, dodajDrzavuUpit, odrediIdGradaUpit, promijeniGradUpit, odrediIdDrzaveUpit;
    private PreparedStatement dajDrzavuUpit;
    private  Connection connection = null;


    public void napuni() {
        gradovi.removeAll();
        gradovi.add(new Grad(1, "Sarajevo", 120000, "BIH"));
        gradovi.add(new Grad(2, "Berlin", 150000, "Njemacka"));

        trenutniGrad.set(null);
    }
    public ObservableList<Grad> getGradovi() {
        return gradovi;
    }

    public void setGradovi(ObservableList<Grad> gradovi) {
        this.gradovi = gradovi;
    }

    public Grad getTrenutniGrad() {
        return trenutniGrad.get();
    }

    public ObjectProperty<Grad> trenutniGradProperty() {
        return trenutniGrad;
    }

    public void setTrenutniGrad(Grad trenutniGrad) {
        this.trenutniGrad.set(trenutniGrad);
    }

    public ObservableList<Drzava> getDrzave() {
        return drzave;
    }

    public void setDrzave(ObservableList<Drzava> drzave) {
        this.drzave = drzave;
    }

    public Drzava getTrenutnaDrzava() {
        return trenutnaDrzava.get();
    }

    public ObjectProperty<Drzava> trenutnaDrzavaProperty() {
        return trenutnaDrzava;
    }

    public void setTrenutnaDrzava(Drzava trenutnaDrzava) {
        this.trenutnaDrzava.set(trenutnaDrzava);
    }

    private  static  void initialize() {
        instance = new GeografijaModel();

    }
    private GeografijaModel() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:resources/baza.db");


        } catch (SQLException e) {
            e.printStackTrace();
        }/*
        try {
            glavniGradUpit = connection.prepareStatement("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM grad g, drzava d WHERE g.drzava = d.id AND d.naziv = ? AND d.glavni_grad = g.id");
        } catch (SQLException e) {
            regenerisiBazu();
            try {
                glavniGradUpit = connection.prepareStatement("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM grad g, drzava d WHERE g.drzava = d.id AND d.naziv = ? AND d.glavni_grad = g.id");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        try {
            dajDrzavuUpit = connection.prepareStatement("SELECT * FROM drzava WHERE id=?");
            dajGradUpit = connection.prepareStatement("SELECT * FROM grad WHERE id=?");
            obrisiGradoveZaDrzavuUpit = connection.prepareStatement("DELETE FROM grad WHERE drzava = ?");
            obrisiDrzavuUpit = connection.prepareStatement("DELETE FROM drzava WHERE id = ? ");
            nadjiDrzavuUpit = connection.prepareStatement("SELECT * FROM drzava WHERE naziv = ?");
            dajGradoveUpit = connection.prepareStatement("SELECT * FROM grad ORDER BY broj_stanovnika DESC");
            dodajGradUpit = connection.prepareStatement("INSERT INTO grad VALUES (?,?,?,?)");
            odrediIdGradaUpit = connection.prepareStatement("SELECT Max(id) + 1 FROM grad");
            dodajDrzavuUpit = connection.prepareStatement("INSERT INTO drzava VALUES (?,?,?)");
            odrediIdDrzaveUpit = connection.prepareStatement("SELECT Max(id) + 1 FROM drzava");
            promijeniGradUpit = connection.prepareStatement("UPDATE grad SET naziv = ?, broj_stanovnika = ?, drzava = ? WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public static GeografijaModel dajInstancu() {
        if (instance == null) {
            instance = new GeografijaModel();
        instance.napuni();
        }
        return instance;
    }

    public static void removeInstance() {
        if (instance != null) {
            try {
                instance.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        instance = null;
    }
/*
    public ArrayList<Grad> gradovi() {
        ArrayList<Grad> rez = new ArrayList<>();
        try {
            ResultSet resultSet = dajGradoveUpit.executeQuery();
            while (resultSet.next()) {
                Grad grad = dajGradIzResultSeta(resultSet);
                rez.add(grad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rez;
    }

    public Grad glavniGrad(String drzava) {
        try {
            glavniGradUpit.setString(1, drzava);
            ResultSet rs = glavniGradUpit.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return dajGradIzResultSeta(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
*/
    /*private Drzava dajDrzavu(int id, Grad grad) {
        try {
            dajDrzavuUpit.setInt(1, id);
            ResultSet rs = dajDrzavuUpit.executeQuery();
            if (!rs.next()) return null;
            return dajDrzavuIzResultSeta(rs, grad);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Grad dajGradIzResultSeta(ResultSet rs) throws SQLException {
        Grad grad = new Grad(rs.getInt(1), rs.getString(2), rs.getInt(3), null);
        grad.setDrzava(dajDrzavu(rs.getInt(4), grad));
        return grad;
    }

    public void obrisiDrzavu(String nazivDrzave) {
        try {
            nadjiDrzavuUpit.setString(1, nazivDrzave);
            ResultSet rs = nadjiDrzavuUpit.executeQuery();
            if (!rs.next()) return;
            Drzava drzava = dajDrzavuIzResultSeta(rs, null);
            obrisiGradoveZaDrzavuUpit.setInt(1, drzava.getId());
            obrisiGradoveZaDrzavuUpit.executeUpdate();
            obrisiDrzavuUpit.setInt(1, drzava.getId());
            obrisiDrzavuUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Drzava dajDrzavuIzResultSeta(ResultSet rs, Grad grad) throws SQLException {
        return new Drzava(rs.getInt(1), rs.getString(2), grad);
    }

    public void dodajGrad(Grad grad) {
        try {
            ResultSet rs = odrediIdGradaUpit.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            dodajGradUpit.setInt(1, id);
            dodajGradUpit.setString(2, grad.getNaziv());
            dodajGradUpit.setInt(3, grad.getBrojStanovnika());
            dodajGradUpit.setInt(4, grad.getDrzava().getId());
            dodajGradUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajDrzavu(Drzava drzava) {
        try {
            ResultSet rs = odrediIdDrzaveUpit.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            dodajDrzavuUpit.setInt(1, id);
            dodajDrzavuUpit.setString(2, drzava.getNaziv());
            dodajDrzavuUpit.setInt(3, drzava.getGlavniGrad().getId());
            dodajDrzavuUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Grad dajGrad(int id) {
        try {
            dajGradUpit.setInt(1, id);
            ResultSet rs = dajGradUpit.executeQuery();
            if (!rs.next()) return null;
            return dajGradIzResultSeta(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Drzava nadjiDrzavu(String nazivDrzave) {
        try {
            nadjiDrzavuUpit.setString(1, nazivDrzave);
            ResultSet rs = nadjiDrzavuUpit.executeQuery();
            if (!rs.next()) return null;
            return dajDrzavuIzResultSeta(rs, dajGrad(rs.getInt(3)));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void izmijeniGrad(Grad grad) {
        try {
            promijeniGradUpit.setString(1, grad.getNaziv());
            promijeniGradUpit.setInt(2, grad.getBrojStanovnika());
            promijeniGradUpit.setInt(3, grad.getDrzava().getId());
            promijeniGradUpit.setInt(4, grad.getId());
            promijeniGradUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new File("baza.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if (sqlUpit.charAt(sqlUpit.length() - 1) == ';') {
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/
}