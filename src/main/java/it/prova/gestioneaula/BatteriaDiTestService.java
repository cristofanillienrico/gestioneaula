package it.prova.gestioneaula;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestioneaula.model.Aula;
import it.prova.gestioneaula.model.Studente;
import it.prova.gestioneaula.service.AulaService;
import it.prova.gestioneaula.service.StudenteService;

@Service
public class BatteriaDiTestService {

	@Autowired
	private AulaService aulaService;

	@Autowired
	private StudenteService studenteService;

	// casi di test (usare valorizzando la variabile casoDaTestare nel main)
	public static final String INSERISCI_NUOVO_AULA = "INSERISCI_NUOVO_AULA";
	public static final String INSERISCI_STUDENTI_DATA_UN_AULA = "INSERISCI_STUDENTI_DATA_UN_AULA";
	public static final String RIMUOVI_AULA_E_STUDENTI = "RIMUOVI_AULA_E_STUDENTI";
	public static final String ELENCA_TUTTE_LE_AULE = "ELENCA_TUTTE_LE_AULE";
	public static final String FIND_BY_EXAMPLE_BY_MATERIA = "FIND_BY_EXAMPLE_BY_MATERIA";
	public static final String UPDATE_STUDENTE_SET_DATA_NASCITA = "UPDATE_STUDENTE_SET_DATA_NASCITA";
	public static final String CARICA_AULA_EAGER = "CARICA_AULA_EAGER";
	public static final String FIND_BY_EXAMPLE_BY_COGNOME = "FIND_BY_EXAMPLE_BY_COGNOME";
	public static final String FIND_BY_EXAMPLE_NOME = "FIND_BY_EXAMPLE_NOME";

	public void eseguiBatteriaDiTest(String casoDaTestare) {
		try {
			switch (casoDaTestare) {
			case INSERISCI_NUOVO_AULA:
				// creo nuovo municipio
				Aula nuovaAula = new Aula(1, "2A", "italiano");
				// salvo
				aulaService.inserisciNuovo(nuovaAula);
				System.out.println("Municipio appena inserito: " + nuovaAula);
				break;

			case INSERISCI_STUDENTI_DATA_UN_AULA:
				// / creo nuovo studente
				Studente nuovoStudente1 = new Studente("andrea", "vecchiato", new Date(),
						aulaService.caricaSingolaAula(2L));
//				Studente nuovoStudente2= new Studente("dino","risi",new Date(),aulaService.caricaSingolaAula(1L));
				// salvo
				studenteService.inserisciNuovo(nuovoStudente1);
//				studenteService.inserisciNuovo(nuovoStudente2);
				break;
//
//			
			case RIMUOVI_AULA_E_STUDENTI:
				// per cancellare tutto il municipio
				Aula aulaDaEliminare = aulaService.caricaSingolaAula(2L);
				aulaService.rimuovi(aulaDaEliminare);
				break;
//
			case ELENCA_TUTTE_LE_AULE:
				// elencare i municipi
				System.out.println("Elenco le aule:");
				for (Aula aulaItem : aulaService.listAllAule()) {
					System.out.println(aulaItem);
				}
				break;
//
			case FIND_BY_EXAMPLE_BY_MATERIA:
				System.out.println("########### EXAMPLE ########################");

				Aula aulaExample = new Aula();
				aulaExample.setMateria("magia");
				for (Aula aulaItem : aulaService.findByExample(aulaExample)) {
					System.out.println(aulaItem);
				}
				break;

			case FIND_BY_EXAMPLE_BY_COGNOME:
				System.out.println("########### EXAMPLE ########################");

				Studente studenteExample = new Studente();
				studenteExample.setCognome("vecch");
				System.out.println(studenteService.findByExample(studenteExample).toString());
				break;

			case FIND_BY_EXAMPLE_NOME:
				System.out.println("########### EXAMPLE ########################");

				Studente studenteExample2 = new Studente();
				studenteExample2.setNome("andrea");
				System.out.println(studenteService.findByExample(studenteExample2).toString());
				break;

			case UPDATE_STUDENTE_SET_DATA_NASCITA:
				// carico un abitante e cambio eta
				Studente studenteEsistente = studenteService.caricaSingoloStudente(21L);
				if (studenteEsistente != null) {
					studenteEsistente.setDataNascita(new Date());
					studenteService.aggiorna(studenteEsistente);
				}
				break;
//
			case CARICA_AULA_EAGER:
				// quando carico un Municipio ho già i suoi abitanti
				Aula aulaACaso = aulaService.caricaSingolaAulaEagerStudenti(2L);
				if (aulaACaso != null) {
					for (Studente studenteItem : aulaACaso.getStudenti()) {
						System.out.println(studenteItem);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
