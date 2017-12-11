package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="COMPTE")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="NUMERO")
	private String numero;
	@Column(name="SOLDE")
	private Double solde;
	
	@ManyToMany
	@JoinTable(name="CLI_COMPTE",
			   joinColumns = @JoinColumn(name="ID_COMPTE", referencedColumnName="ID"),
	           inverseJoinColumns = @JoinColumn(name="ID_CLIENT", referencedColumnName="ID")
	)
	private List<Client> clients;
	
	@OneToMany(mappedBy="compte")
	private List<Operation> operations;

	public Compte() {
		clients = new ArrayList<Client>();
		operations = new ArrayList<Operation>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
}
