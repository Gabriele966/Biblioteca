package view;
import java.lang.invoke.SwitchPoint;
import java.time.format.DateTimeFormatter;
import java.util.*;

import model.Libri;
import model.Prestito;
import model.Utente;
import service.*;
import java.time.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selezioanre la tabella che si desidera manipolare: ");
        System.out.println("1) Libri");
        System.out.println("2) Utente");
        System.out.println("3) Prestito");
        int entita = scanner.nextInt();

        System.out.println("***Menu***");
        System.out.println("1. Crea una nuova entita ");
        System.out.println("2. Aggiorna entita");
        System.out.println("3. Visualizza la lista delle entita");
        System.out.println("4. Elimina un entita");
        if(entita == 1) {
            System.out.println("5. quante volte è stato prestato un libro: ");

        }else if(entita == 2) {
            System.out.println("5. Elenco dei prestiti");
            System.out.println("6. quanti prestiti a fatto la singola persona: ");
        }else if(entita == 3) {
            System.out.println("5. Visualizza presititi durante uno specifico periodo");
            System.out.println("6. Visualizza i ritardatari");
        }
        System.out.println("9. Exit");
        System.out.print("inserisci la tua scelta: ");
        int sceta = scanner.nextInt();
        switch (sceta) {
            case 1:
                create(entita);
                break;
            case 2:
                update(entita);
                break;
            case 3:
                read(entita);
                break;
            case 4:
                delete(entita);
                break;
            case 5:
                if(entita == 1) {
                    voltePresoPrestito();
                }else if(entita == 2) {
                    prestiotoUtente();
                }else{
                    prestitoPeriodoSpecifico();
                }
                break;
            case 6:
                if(entita == 2) {
                    quantitaPrestititUtenti();
                }else{
                    ritardo();
                }
                break;
            case 9:
                break;
            default:
                System.out.print("Scelta non valida");
                break;
        }
    }

    private static void voltePresoPrestito() {
        LibriService oLibriService = new LibriService();
        List<String> list = oLibriService.volteLibroPrestato();
        for(String s : list){
            System.out.println(s);
        }
    }

    private static void quantitaPrestititUtenti() {
        UtenteService oUtenteService = new UtenteService();
        List<String> list = oUtenteService.quantitaPrestititUtenti();
        for(String s : list){
            System.out.println(s);
        }
    }

    private static void ritardo() {
        PrestitoService oPrestitoService = new PrestitoService();
        List<String> list = oPrestitoService.riratdo();
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static void prestitoPeriodoSpecifico() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("le date vanno inserite nel formato gg/mm/aaaa");
        System.out.println("inserire data inizio: ");
        String inizo = scanner.nextLine();
        System.out.println("inserire data fine: ");
        String fine = scanner.nextLine();
        PrestitoService oPrestitoService = new PrestitoService();
        List<String> list = oPrestitoService.prestitoPeriodoSpecifico(inizo, fine);
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }




    private static void prestiotoUtente() {
        UtenteService oUtenteService = new UtenteService();
        List<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci cognome utente: ");
        String cognome = scanner.next();
        System.out.println("inseirsci nome utente: ");
        String nome = scanner.next();
        list = oUtenteService.prestiotoUtente(cognome);
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    public static void create(int entita) {
        Scanner scanner = new Scanner(System.in);
        LibriService oLibriService = new LibriService();
        UtenteService oUtenteService = new UtenteService();
        PrestitoService oPrestitoService = new PrestitoService();
        switch (entita) {
            case 1:
                System.out.print("Inserire nome autore: ");
                String autore = scanner.next();
                System.out.print("Inserire titolo del Libro: ");
                String titolo = scanner.next();
                System.out.print("Inserire id Libro: ");
                String idL = scanner.next();
                oLibriService.create(autore, titolo, idL);
                break;
            case 2:
                System.out.print("Inserire cognome utente: ");
                String cognome= scanner.next();
                System.out.print("Inserire nome utente: ");
                String nome = scanner.next();
                oUtenteService.create(cognome, nome);
                break;
            case 3:
                System.out.print("Inserire id del Libro: ");
                String xidL = scanner.next();
                System.out.print("Inserire id del utente: ");
                int xidU = scanner.nextInt();
                System.out.print("Inserire la data di inizio prestito nel Formato gg/mm/aaaa: ");
                String dataInizio = scanner.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate inzio = LocalDate.parse(dataInizio, formatter);
                System.out.print("Inserire la data di fine prestito nel Formato gg/mm/aaaa: ");
                String dataFine = scanner.next();
                LocalDate fine = LocalDate.parse(dataFine, formatter);
                oPrestitoService.create(xidL, xidU, inzio, fine);
            default:
                System.out.print("dato non valido ");
                break;
        }

    }

    public static void update(int entita) {
        Scanner scanner = new Scanner(System.in);
        LibriService oLibriService = new LibriService();
        UtenteService oUtenteService = new UtenteService();
        PrestitoService oPrestitoService = new PrestitoService();
        switch (entita) {
            case 1:
                System.out.print("Inserire nome autore: ");
                String autore = scanner.next();
                System.out.print("Inserire titolo del Libro: ");
                String titolo = scanner.next();
                System.out.print("Inserire id Libro: ");
                String idL = scanner.next();
                oLibriService.update(autore, titolo, idL);
                break;
            case 2:
                System.out.print("Inserire cognome utente: ");
                String cognome= scanner.next();
                System.out.print("Inserire nome utente: ");
                String nome = scanner.next();
                System.out.print("Inserire id utente da modificare: ");
                int idU = scanner.nextInt();
                oUtenteService.update(cognome, nome, idU);
                break;
            case 3:
                System.out.print("Inserire id del Libro: ");
                String xidL = scanner.next();
                System.out.print("Inserire id del utente: ");
                int xidU = scanner.nextInt();
                System.out.print("Inserire la nuova data di inizio prestito nel Formato gg/mm/aaaa: ");
                String dataInizio = scanner.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate inzio = LocalDate.parse(dataInizio, formatter);
                System.out.print("Inserire la nuova data di fine prestito nel Formato gg/mm/aaaa: ");
                String dataFine = scanner.next();
                LocalDate fine = LocalDate.parse(dataFine, formatter);
                oPrestitoService.update(xidL, xidU, inzio, fine);
                break;
            default:
                System.out.print("dato non valido ");
                break;
        }


    }

    public static void read(int entita) {
        LibriService oLibriService = new LibriService();
        UtenteService oUtenteService = new UtenteService();
        PrestitoService oPrestitoService = new PrestitoService();
        switch(entita){
            case 1:
                List<Libri> readLibri = oLibriService.read();
                for(int i = 0; i < readLibri.size(); i++){
                    System.out.println(readLibri.get(i).getIdL() + " "+readLibri.get(i).getAutore()+ " "+ readLibri.get(i).getTitolo());
                }
                break;
            case 2:
                List<Utente> readUtente = oUtenteService.read();
                for(int i = 0; i < readUtente.size(); i++){
                    System.out.println(readUtente.get(i).getCognome() + " "+ readUtente.get(i).getNome());
                }
                break;
            case 3:
                List<Prestito> readPrestito = oPrestitoService.read();
                for(int i = 0; i < readPrestito.size(); i++){
                    System.out.println(readPrestito.get(i).getLibri().getIdL() + " "+ readPrestito.get(i).getUtente().getIdU() +
                            " " + readPrestito.get(i).getFine().toString() + " " + readPrestito.get(i).getInizio().toString());
                }
                break;
            default:
                System.out.print("dato non valido ");
                break;
        }


    }



    public static void delete(int entita) {
        Scanner scanner = new Scanner(System.in);
        UtenteService oUtenteService = new UtenteService();
        LibriService oLibriService = new LibriService();
        PrestitoService oPrestitoService = new PrestitoService();

        switch (entita) {
            case 1:
                System.out.print("Inserire id del Libro da eliminare: ");
                String idL = scanner.next();
                oLibriService.delete(idL);
                break;
            case 2:
                System.out.print("Inserire id del utente da eliminare: ");
                int idU = scanner.nextInt();
                oUtenteService.delete(idU);
                break;
            case 3:
                System.out.print("Inserire id Utente da eliminare: ");
                int idUP = scanner.nextInt();
                System.out.print("Inserire id Libro da eliminare: ");
                String idLP = scanner.next();
                oPrestitoService.delete(idLP, idUP);
                break;
            default:
                System.out.print("dato non valido ");
                break;
        }






    }
}