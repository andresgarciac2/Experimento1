package co.com.uniandes.sube.dto;

public class OfferStepConfigurationDTO {

	long id;
	long offerId;
	String serializeSettings;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}	
	/**
	 * @return the offerId
	 */
	public long getOfferId() {
		return offerId;
	}
	/**
	 * @param offerId the offerId to set
	 */
	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}
	/**
	 * @return the serializeSettings
	 */
	public String getSerializeSettings() {
		return serializeSettings;
	}
	/**
	 * @param serializeSettings the serializeSettings to set
	 */
	public void setSerializeSettings(String serializeSettings) {
		this.serializeSettings = serializeSettings;
	}
}
