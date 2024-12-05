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
            stmt.execute("INSERT INTO utenti VALUES('"+oUtente.getCognome()+"', '" + oUtente.getNome() + "')");
            stmt.close();
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
            stmt.close();
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
            stmt.execute("DELETE FROM utenti WHERE idu = '" + utente.getIdU() + "'");
            stmt.close();
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
            stmt.execute("UPDATE utenti SET nome='"+ oUtente.getNome()+"', cognome='"+ oUtente.getCognome()+"' WHERE idu ="  + oUtente.getIdU());
            stmt.close();
            System.out.println("model.dao.Utente aggiornato");
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public List<String> prestiotoUtente(Utente oUtente) {
        List<String> lprestatiUtente = new ArrayList<>();
        try{
            Connection c = DbConnection.openConnection();
            System.out.println("Connessione Riuscita");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ut.cognome, ut.nome, lb.Titolo, pr.inizio " +
                    "FROM Libri AS lb " +
                    "JOIN prestito pr ON pr.xidl = lb.idl " +
                    "JOIN utenti ut ON ut.idu = pr.xidu " +
                    "WHERE ut.cognome = '" + oUtente.getCognome() + "' " +
                    "ORDER BY pr.inizio ASC;");
            while(rs.next()) {
                String cognome = rs.getString("cognome");
                String nome = rs.getString("nome");
                String titolo = rs.getString("titolo");
                String inizio = rs.getString("inizio");
                lprestatiUtente.add(cognome+" "+nome+" "+titolo+" "+inizio);
            }
            stmt.close();


        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return lprestatiUtente;
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
            stmt.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return lprestatiUtenti;
    }

}
