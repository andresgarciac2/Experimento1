package co.com.uniandes.sube.persistence;

public class OfferTransitionDTO {

	int id;
	int sourceStep;
	int targetStep;
	String conditions;
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
	 * @return the sourceStep
	 */
	public int getSourceStep() {
		return sourceStep;
	}
	/**
	 * @param sourceStep the sourceStep to set
	 */
	public void setSourceStep(int sourceStep) {
		this.sourceStep = sourceStep;
	}
	/**
	 * @return the targetStep
	 */
	public int getTargetStep() {
		return targetStep;
	}
	/**
	 * @param targetStep the targetStep to set
	 */
	public void setTargetStep(int targetStep) {
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
