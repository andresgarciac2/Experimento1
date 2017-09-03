package co.com.uniandes.sube.persistence;

public class OfferTransitionDTO {

	long id;
	long offerId;
	long sourceStep;
	long targetStep;
	String conditions;
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
	 * @return the sourceStep
	 */
	public long getSourceStep() {
		return sourceStep;
	}
	/**
	 * @param sourceStep the sourceStep to set
	 */
	public void setSourceStep(long sourceStep) {
		this.sourceStep = sourceStep;
	}
	/**
	 * @return the targetStep
	 */
	public long getTargetStep() {
		return targetStep;
	}
	/**
	 * @param targetStep the targetStep to set
	 */
	public void setTargetStep(long targetStep) {
		this.targetStep = targetStep;
	}
	/**
	 * @return the conditions
	 */
	public String getConditions() {
		return conditions;
	}
	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
}
