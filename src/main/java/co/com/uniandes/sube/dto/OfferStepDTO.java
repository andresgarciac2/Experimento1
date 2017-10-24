package co.com.uniandes.sube.dto;

public class OfferStepDTO {
	
	long id;
	long offerId;
	OfferStepConfigurationDTO offerStepConfiguration;
	OfferTransitionDTO offerTransition;
	String name;
	long type;
	int position;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the offerStepConfiguration
	 */
	public OfferStepConfigurationDTO getOfferStepConfiguration() {
		return offerStepConfiguration;
	}
	/**
	 * @param offerStepConfiguration the offerStepConfiguration to set
	 */
	public void setOfferStepConfiguration(
			OfferStepConfigurationDTO offerStepConfiguration) {
		this.offerStepConfiguration = offerStepConfiguration;
	}
	/**
	 * @return the offerTransition
	 */
	public OfferTransitionDTO getOfferTransition() {
		return offerTransition;
	}
	/**
	 * @param offerTransition the offerTransition to set
	 */
	public void setOfferTransition(OfferTransitionDTO offerTransition) {
		this.offerTransition = offerTransition;
	}
	/**
	 * @return the type
	 */
	public long getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(long type) {
		this.type = type;
	}
	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	
}
