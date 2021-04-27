package it.prova.gestioneaula.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneaula.dao.AulaDAO;
import it.prova.gestioneaula.model.Aula;

@Service
public class AulaServiceImpl implements AulaService {

	@Autowired
	private AulaDAO aulaDAO;

	@Override
	public List<Aula> listAllAule() {
		return aulaDAO.list();
	}

	@Override
	public Aula caricaSingolaAula(Long id) {
		return aulaDAO.get(id);
	}

	@Override
	public Aula caricaSingolaAulaEagerStudenti(Long idAula) {
		return aulaDAO.findEagerFetch(idAula);
	}

	@Transactional
	public void aggiorna(Aula aulaInstance) {
		aulaDAO.update(aulaInstance);

	}

	@Transactional
	public void inserisciNuovo(Aula aulaInstance) {
		aulaDAO.insert(aulaInstance);

	}

	@Transactional
	public void rimuovi(Aula aulaInstance) {
		Aula daEliminare = aulaDAO.findEagerFetch(aulaInstance.getId());
		if (!daEliminare.getStudenti().isEmpty()) {
			throw new RuntimeException("Non posso eliminare aule con dentro studenti");

		}
		aulaDAO.delete(aulaInstance);

	}

	@Override
	public List<Aula> findByExample(Aula example) {
		return aulaDAO.findByExample(example);
	}

}
