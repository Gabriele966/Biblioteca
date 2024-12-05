package model;

import java.time.LocalDate;

//Tabella di join tra utenti e libri
public class Prestito {
    private Utente utente;
    private Libri libri;
    private LocalDate inizio;
    private LocalDate fine;

    public Utente getUtente() {return utente;}
    public void setUtente(Utente utente) {this.utente = utente;}

    public Libri getLibri() {return libri;}
    public void setLibri(Libri libri) {this.libri = libri;}

    public LocalDate getInizio() {return inizio;}
    public void setInizio(LocalDate inizio) {this.inizio = inizio;}

    public LocalDate getFine() {return fine;}
    public void setFine(LocalDate fine) {this.fine = fine;}

}
