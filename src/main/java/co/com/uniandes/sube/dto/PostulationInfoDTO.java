package co.com.uniandes.sube.dto;

import java.util.Date;


public class PostulationInfoDTO {

	private int id;
	private int postulationId;
	private int boolValue;
	private Date dateValue;
	private double decimalValue;
	private int intValue;	
	private String stringValue;
	private AttributeDTO attribute;
	
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
	 * @return the postulationId
	 */
	public int getPostulationId() {
		return postulationId;
	}
	/**
	 * @param postulationId the postulationId to set
	 */
	public void setPostulationId(int postulationId) {
		this.postulationId = postulationId;
	}
	/**
	 * @return the boolValue
	 */
	public int getBoolValue() {
		return boolValue;
	}
	/**
	 * @param boolValue the boolValue to set
	 */
	public void setBoolValue(int boolValue) {
		this.boolValue = boolValue;
	}
	/**
	 * @return the dateValue
	 */
	public Date getDateValue() {
		return dateValue;
	}
	/**
	 * @param dateValue the dateValue to set
	 */
	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}
	/**
	 * @return the decimalValue
	 */
	public double getDecimalValue() {
		return decimalValue;
	}
	/**
	 * @param decimalValue the decimalValue to set
	 */
	public void setDecimalValue(double decimalValue) {
		this.decimalValue = decimalValue;
	}
	/**
	 * @return the intValue
	 */
	public int getIntValue() {
		return intValue;
	}
	/**
	 * @param intValue the intValue to set
	 */
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	/**
	 * @return the stringValue
	 */
	public String getStringValue() {
		return stringValue;
	}
	/**
	 * @param stringValue the stringValue to set
	 */
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	/**
	 * @return the atribute
	 */
	public AttributeDTO getAttribute() {
		return attribute;
	}
	/**
	 * @param atribute the atribute to set
	 */
	public void setAttribute(AttributeDTO attribute) {
		this.attribute = attribute;
	}
	
	
	
	
}
