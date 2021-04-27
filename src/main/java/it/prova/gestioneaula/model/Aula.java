package it.prova.gestioneaula.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "aula")
public class Aula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer capienza;
	private String codice;
	private String materia;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aula")
	private Set<Studente> studenti = new HashSet<>();

	public Aula() {
		super();
	}

	public Aula(Integer capienza, String codice, String materia) {
		super();
		this.capienza = capienza;
		this.codice = codice;
		this.materia = materia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCapienza() {
		return capienza;
	}

	public void setCapienza(Integer capienza) {
		this.capienza = capienza;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Set<Studente> getStudenti() {
		return studenti;
	}

	public void setStudenti(Set<Studente> studenti) {
		this.studenti = studenti;
	}

	@Override
	public String toString() {
		return "Aula [id=" + id + ", capienza=" + capienza + ", codice=" + codice + ", materia=" + materia + "]";
	}

}
