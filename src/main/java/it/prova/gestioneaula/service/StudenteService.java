package it.prova.gestioneaula.service;

import java.util.List;

import it.prova.gestioneaula.model.Studente;

public interface StudenteService {

	public List<Studente> listAllStudenti();

	public Studente caricaSingoloStudente(Long id);

	public void aggiorna(Studente studenteInstance);

	public void inserisciNuovo(Studente studenteInstance);

	public void rimuovi(Studente studenteInstance);

	public List<Studente> findByExample(Studente example);

}
