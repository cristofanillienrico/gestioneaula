package it.prova.gestioneaula.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneaula.dao.StudenteDAO;
import it.prova.gestioneaula.model.Aula;
import it.prova.gestioneaula.model.Studente;

@Service
public class StudenteServiceImpl implements StudenteService {

	@Autowired
	private StudenteDAO studenteDAO;

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Studente> listAllStudenti() {
		return studenteDAO.list();
	}

	@Override
	public Studente caricaSingoloStudente(Long id) {
		return studenteDAO.get(id);
	}

	@Transactional
	public void aggiorna(Studente studenteInstance) {
		studenteDAO.update(studenteInstance);

	}

	@Transactional
	public void inserisciNuovo(Studente studenteInstance) {

		Studente studenteDaInserire = entityManager.merge(studenteInstance);
		Aula aulaDelloStudente = studenteDaInserire.getAula();
		Integer numeroStudentiInAula = aulaDelloStudente.getStudenti().size();

		// mi dovrebbe salvare in caso ho già sforato il tetto massimo inseredo studenti
		// dal db
		if (numeroStudentiInAula.compareTo(aulaDelloStudente.getCapienza()) >= 0) {
			throw new RuntimeException("L'aula è piena ci sono " + numeroStudentiInAula + " studenti");
		}

//		studenteDAO.insert(studenteInstance);

	}

	@Transactional
	public void rimuovi(Studente studenteInstance) {
		studenteDAO.delete(studenteInstance);

	}

	@Override
	public List<Studente> findByExample(Studente example) {
		return studenteDAO.findByExample(example);
	}

}
