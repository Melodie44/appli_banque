package ihm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Adresse;
import model.AssuranceVie;
import model.Banque;
import model.Client;
import model.Compte;
import model.LivretA;
import model.Virement;

public class RunApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(RunApp.class);

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_essai");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		// Création d'une banque
		Banque banq = new Banque();
		banq.setNom("Caisse d'épargne");
				
		em.persist(banq);
		et.commit();
		LOG.info("Banque créée");
		
		// Création d'une adresse
		et.begin();
		Adresse adr = new Adresse();
		adr.setNumero(12);
		adr.setRue("Impasse des Silènes");
		adr.setCodePostal(44100);
		adr.setVille("Nantes");
		
		// Création d'un client
		Client client = new Client();
		client.setNom("Dupont");
		client.setPrenom("Charles");
		client.setDateNaissance(LocalDate.parse("1991-02-03"));
		client.setAdresse(adr);
		client.setBanque(banq);
		
		em.persist(client);
		et.commit();
		LOG.info("Client créé avec son adresse");
		
		// Création de compte (livretA) pour le client
		et.begin();
		LivretA livretA = new LivretA();
		livretA.setNumero("0000000000101");
		livretA.setSolde(125.00);
		livretA.setTaux(0.75);
		
		em.persist(livretA);
		livretA.getClients().add(em.find(Client.class, 1));
		
		et.commit();
		LOG.info("LivretA créé");
		
		// Ajout d'un compte (assurance vie)
		et.begin();
		AssuranceVie ass = new AssuranceVie();
		ass.setNumero("0000000000202");
		ass.setSolde(1000.00);
		ass.setDateFin(LocalDate.parse("2020-10-10"));
		ass.setTaux(2.60);
		
		em.persist(ass);
		ass.getClients().add(em.find(Client.class, 1));
		et.commit();
		LOG.info("AssuranceVie créé");
		
		// Ajout d'une opération pour le livretA
		et.begin();
		Virement op = new Virement();
		op.setDate(LocalDateTime.now());
		op.setMontant(25.00);
		op.setMotif("Crédit");
		op.setBeneficiaire("Michelle");
		op.setCompte(em.find(Compte.class, 1));
		
		em.persist(op);
		et.commit();
		LOG.info("Virement pour livretA créé");
		
		// Ajout d'une opération pour l'assurance vie
		et.begin();
		Virement op2 = new Virement();
		op2.setDate(LocalDateTime.now());
		op2.setMontant(100.00);
		op2.setMotif("Crédit");
		op2.setBeneficiaire("Moi");
		op2.setCompte(em.find(Compte.class, 2));
		
		em.persist(op2);
		et.commit();
		LOG.info("Virement pour assurance vie créé");

		em.close();
		emf.close();
	}

}
