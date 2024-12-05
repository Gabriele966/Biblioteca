package repository;

import config.DbConnection;
import model.Libri;

import java.sql.*;
import java.util.*;

public class LibriRepository {

    public void createLibri(Libri oLibri) {
        try{
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            System.out.println("Connessisone Riuscita");
            ResultSet rs = stmt.executeQuery("INSERT INTO Libri VALUES('"+oLibri.getAutore() +"', '" + oLibri.getAutore() + "', '"+ oLibri.getIdL() + "')");
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public List<Libri> readLibri() {
        List<Libri> lLibri = new ArrayList<>();

        try{
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Libri");

            while(rs.next()){
                Libri oLibri = new Libri();
                oLibri.setTitolo(rs.getString("titolo"));
                oLibri.setAutore(rs.getString("Autore"));
                oLibri.setIdL(rs.getString("idL"));
                lLibri.add(oLibri);
            }
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return lLibri;
    }

    public void updateLibri(Libri oLibri) {
        try {
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("UPDATE libri SET Titolo='"+ oLibri.getTitolo()+"', Autore='"+ oLibri.getAutore()+"' WHERE id ='" +oLibri.getIdL()+ "'");
            System.out.println("model.dao.Libri aggiornato");
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void deleteLibri(Libri oLibri) {
        try{
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM Libri WHERE idL='" + oLibri.getIdL()+"'");
            System.out.println("model.dao.Libri eliminato");
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }

}
