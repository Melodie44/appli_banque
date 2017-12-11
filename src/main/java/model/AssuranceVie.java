package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ASSURANCE_VIE")
public class AssuranceVie extends Compte{

	@Column(name="DATE_FIN")
	private LocalDate dateFin;
	@Column(name="TAUX")
	private Double taux;
	
	public AssuranceVie() {
		super();
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}
	
}
