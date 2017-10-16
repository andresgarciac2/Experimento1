package co.com.uniandes.sube.dto;

import java.util.Date;

public class CandidateDTO extends UserDTO{
	
	int id;
	Date birthdate;
	int city;
	int departament;
	int levelSchooling;
	int stratum;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}
	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	/**
	 * @return the city
	 */
	public int getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(int city) {
		this.city = city;
	}
	/**
	 * @return the departament
	 */
	public int getDepartament() {
		return departament;
	}
	/**
	 * @param departament the departament to set
	 */
	public void setDepartament(int departament) {
		this.departament = departament;
	}
	/**
	 * @return the levelSchooling
	 */
	public int getLevelSchooling() {
		return levelSchooling;
	}
	/**
	 * @param levelSchooling the levelSchooling to set
	 */
	public void setLevelSchooling(int levelSchooling) {
		this.levelSchooling = levelSchooling;
	}
	/**
	 * @return the stratum
	 */
	public int getStratum() {
		return stratum;
	}
	/**
	 * @param stratum the stratum to set
	 */
	public void setStratum(int stratum) {
		this.stratum = stratum;
	}
}
