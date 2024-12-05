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
            stmt.execute("INSERT INTO Libri VALUES('"+oLibri.getAutore() +"', '" + oLibri.getAutore() + "', '"+ oLibri.getIdL() + "')");
            stmt.close();
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
            stmt.close();
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
            stmt.execute("UPDATE libri SET Titolo='"+ oLibri.getTitolo()+"', Autore='"+ oLibri.getAutore()+"' WHERE id ='" +oLibri.getIdL()+ "'");
            System.out.println("model.dao.Libri aggiornato");
            stmt.close();
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void deleteLibri(Libri oLibri) {
        try{
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            stmt.execute("DELETE FROM Libri WHERE idL='" + oLibri.getIdL()+"'");
            System.out.println("model.dao.Libri eliminato");
            stmt.close();
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public List<String> voltePresoPrestito(){
        List<String> list = new ArrayList<>();
        try{
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT lb.titolo, COUNT(pr.xidl)\n" +
                    "FROM prestito AS pr\n" +
                    "JOIN libri lb ON lb.idl = pr.xidl\n" +
                    "GROUP BY(lb.titolo)\n" +
                    "ORDER BY (COUNT (pr.xidl)) DESC;");
            while(rs.next()){
                list.add(rs.getString("titolo") +" "+ rs.getString("count"));
            }
            stmt.close();

        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);

        }
        return list;
    }

    public List<String>  quantitaPrestititUtenti(){
        List<String> lprestatiUtenti = new ArrayList<>();
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ut.nome, COUNT(pr.xidl)\n" +
                    "FROM prestito AS pr\n" +
                    "JOIN utenti ut on ut.idu = pr.xidu \n" +
                    "GROUP BY ( ut.nome)\n" +
                    "ORDER BY (COUNT((pr.xidl))) DESC;");
            while(rs.next()) {
                lprestatiUtenti.add(rs.getString("nome") +" "+ rs.getInt("count"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return lprestatiUtenti;
    }

}
