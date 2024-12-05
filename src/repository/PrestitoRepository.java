package repository;
import config.DbConnection;
import model.Prestito;
import model.Libri;
import model.Utente;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class PrestitoRepository {

    public void createPrestito(Prestito oPrestito) {
        try{
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            stmt.execute("INSERT INTO prestito VALUES('"+oPrestito.getLibri().getIdL() +"', " +
                    "'" + oPrestito.getUtente().getIdU() + "'," +
                    "" + " '"+ oPrestito.getInizio() + "'," +
                    " '"+ oPrestito.getFine() + "')");
            stmt.close();
        }catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }

    public List<Prestito> readPrestito() {
        List<Prestito> lPrestito = new ArrayList<>();
        try{
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM prestito");
            while (rs.next()) {
                Prestito p = new Prestito();
                Libri l = new Libri();
                Utente u = new Utente();
                l.setIdL(rs.getString("xidL"));
                u.setIdU(rs.getInt("xidU"));
                p.setUtente(u);
                p.setLibri(l);
                p.setInizio(LocalDate.parse(rs.getString("inizio")));
                p.setFine(LocalDate.parse(rs.getString("fine")));
                lPrestito.add(p);
            }
            stmt.close();
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return lPrestito;
    }

    public void deletePrestito(Prestito oPrestito) {
        try{
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            stmt.execute("DELETE FROM prestito WHERE xidL = '" + oPrestito.getLibri().getIdL() + "' AND xidU = '" + oPrestito.getUtente().getIdU() + "'");
            System.out.println("model.dao.Prestito eliminato");
            stmt.close();
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void updatePrestito(Prestito oPrestito) {
        try{
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            stmt.execute("UPDATE prestito SET xidL = '" + oPrestito.getLibri().getIdL() + "', xidU = '" + oPrestito.getUtente().getIdU() + "', " +
                    "inizio = '" + oPrestito.getInizio() + "', fine = '" + oPrestito.getFine() + "' " +
                    "WHERE xidL = '" + oPrestito.getLibri().getIdL() + "' AND xidU = '" + oPrestito.getUtente().getIdU() + "'");
            System.out.println("model.dao.Prestito aggiornato");
            stmt.close();
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public List<String> riratdoPrestito() {
        List<String> lPrestito = new ArrayList<>();
        try{
            Connection c = DbConnection.openConnection();
            Statement stmt =c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ut.cognome, ut.nome, lb.Titolo\n" +
                    "FROM libri as lb\n" +
                    "JOIN Prestito pr on pr.xidl = lb.idl\n" +
                    "JOIN Utenti ut on ut.idU = Pr.xidu\n" +
                    "WHERE pr.inizio + 15 < pr.fine;");
            while (rs.next()) {
                String cognome = rs.getString("cognome");
                String nome = rs.getString("nome");
                String titolo = rs.getString("titolo");
                lPrestito.add(cognome + " " + nome + " " + titolo);
            }
            stmt.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return lPrestito;
    }


    public List<String> prestitoPeriodoSpecifico(String ini, String fin){
        List<String> lPrestito = new ArrayList<>();
        try{
            Connection c = DbConnection.openConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ut.cognome, ut.nome, lb.Titolo, pr.inizio, pr.fine " +
                    "FROM libri AS lb " +
                    "JOIN prestito pr ON pr.xidl = lb.idl " +
                    "JOIN utenti ut ON ut.idu = pr.xidu " +
                    "WHERE pr.inizio > '" + ini + "' AND pr.fine < '" + fin + "';");
            while (rs.next()) {
                String cognome = rs.getString("cognome");
                String nome = rs.getString("nome");
                String titolo = rs.getString("titolo");
                lPrestito.add(cognome +" " + nome + " " + titolo);
            }
            stmt.close();
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return lPrestito;
    }







}
