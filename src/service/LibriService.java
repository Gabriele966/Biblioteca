package service;

import model.Libri;
import java.util.*;
import repository.LibriRepository;

public class LibriService {
    LibriRepository oLibriRepository = new LibriRepository();

    public void create(String titolo, String autore, String idL){
        Libri oLibri = new Libri();
        oLibri.setTitolo(titolo);
        oLibri.setAutore(autore);
        oLibri.setIdL(idL);
        oLibriRepository.createLibri(oLibri);
    }

    public List<Libri> read(){return oLibriRepository.readLibri();}

    public void update(String titolo, String autore, String idL){
        Libri oLibri = new Libri();
        oLibri.setTitolo(titolo);
        oLibri.setAutore(autore);
        oLibri.setIdL(idL);
        oLibriRepository.updateLibri(oLibri);
    }

    public void delete(String idL){
        Libri oLibri = new Libri();
        oLibri.setIdL(idL);
        oLibriRepository.deleteLibri(oLibri);
    }


    public List<String> volteLibroPrestato(){return oLibriRepository.voltePresoPrestito();}
}
