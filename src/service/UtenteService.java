package service;

import model.Utente;
import repository.UtenteRepository;
import java.util.*;


public class UtenteService {
    UtenteRepository oUtenteRepository = new UtenteRepository();

    public void create(String cognome, String nome){
        Utente oUtlente = new Utente();
        oUtlente.setCognome(cognome);
        oUtlente.setNome(nome);
        oUtenteRepository.createUtente(oUtlente);
    }

    public List<Utente> read(){return oUtenteRepository.readUtenti();}

    public void update(String cognome, String nome, int idU){
        Utente oUtente = new Utente();
        oUtente.setCognome(cognome);
        oUtente.setNome(nome);
        oUtente.setIdU(idU);
        oUtenteRepository.updateUtente(oUtente);
    }

    public void delete(int idU){
        Utente oUtente = new Utente();
        oUtente.setIdU(idU);
        oUtenteRepository.deleteUtente(oUtente);
    }

    public List<String> prestiotoUtente(String cognome){
        List<String> list =new ArrayList<>();
        Utente oUtente = new Utente();
        oUtente.setCognome(cognome);
        list = oUtenteRepository.prestiotoUtente(oUtente);
        return list;
    }

    public List<String> quantitaPrestititUtenti(){return oUtenteRepository.quantitaPrestititUtenti();}


}
