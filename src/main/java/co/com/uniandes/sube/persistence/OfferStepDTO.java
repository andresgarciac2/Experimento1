package co.com.uniandes.sube.persistence;

public class OfferStepDTO {
	
	int id;
	int offerId;
	OfferStepConfigurationDTO offerStepConfiguration;
	OfferTransitionDTO offerTransition;
	String name;	
	
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
	 * @return the offerId
	 */
	public int getOfferId() {
		return offerId;
	}
	/**
	 * @param offerId the offerId to set
	 */
	public void setOfferId(int offerId) {
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
}
