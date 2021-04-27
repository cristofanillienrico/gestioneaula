package it.prova.gestioneaula.dao;

import it.prova.gestioneaula.model.Aula;

public interface AulaDAO extends IBaseDAO<Aula> {
	public Aula findEagerFetch(long idAula);

}
