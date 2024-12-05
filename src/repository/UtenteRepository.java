package repository;

import config.DbConnection;
import model.Utente;
import java.util.*;
import java.sql.*;

public class UtenteRepository {

    public void createUtente(Utente oUtente) {
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("INSERT INTO utenti VALUES('"+oUtente.getCognome()+"', '" + oUtente.getNome() + "')");
        }catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public List<Utente> readUtenti() {
        List<Utente> lUtente = new ArrayList<>();
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM utenti");
            while (rs.next()) {
                Utente utente = new Utente();
                utente.setCognome(rs.getString("cognome"));
                utente.setNome(rs.getString("nome"));
                utente.setIdU(rs.getInt("idU"));
                lUtente.add(utente);
            }
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return lUtente;
    }

    public void deleteUtente(Utente utente) {
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM utenti WHERE idu = '" + utente.getIdU() + "'");
            System.out.println("model.dao.Utente eliminato");

        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void updateUtente(Utente oUtente) {
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("UPDATE utenti SET nome='"+ oUtente.getNome()+"', cognome='"+ oUtente.getCognome()+"' WHERE idu ="  + oUtente.getIdU());
            System.out.println("model.dao.Utente aggiornato");
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}
