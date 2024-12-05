package service;
import model.Libri;
import model.Prestito;
import model.Utente;
import repository.PrestitoRepository;

import java.util.*;
import java.time.LocalDate;

public class PrestitoService {
    PrestitoRepository oPrestitoRepository = new PrestitoRepository();

    public void create(String xidL, int xidU, LocalDate inizo, LocalDate fine) {
        Prestito prestito = new Prestito();
        Libri libri = new Libri();
        Utente utente = new Utente();
        libri.setIdL(xidL);
        utente.setIdU(xidU);
        prestito.setLibri(libri);
        prestito.setUtente(utente);
        prestito.setInizio(inizo);
        prestito.setFine(fine);
        oPrestitoRepository.createPrestito(prestito);
    }

public List<Prestito> read() {return oPrestitoRepository.readPrestito();}

public void update(String xidL, int xidU, LocalDate inizo, LocalDate fine) {
    Prestito prestito = new Prestito();
    Libri libri = new Libri();
    Utente utente = new Utente();
    libri.setIdL(xidL);
    utente.setIdU(xidU);
    prestito.setLibri(libri);
    prestito.setUtente(utente);
    prestito.setInizio(inizo);
    prestito.setFine(fine);
    oPrestitoRepository.updatePrestito(prestito);
}

public void delete(String xidL, int xidU) {
        Prestito prestito = new Prestito();
        Libri libri = new Libri();
        Utente utente = new Utente();
        libri.setIdL(xidL);
        utente.setIdU(xidU);
        prestito.setLibri(libri);
        prestito.setUtente(utente);
        oPrestitoRepository.deletePrestito(prestito);

}







}
