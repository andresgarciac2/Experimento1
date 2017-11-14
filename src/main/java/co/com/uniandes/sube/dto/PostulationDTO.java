package co.com.uniandes.sube.dto;

import java.util.Date;
import java.util.List;


public class PostulationDTO {
	
	private int id;
	private int userId;
	private int offerId;
	private Date creationDate;
	private int currentStep;
	private int state;
	List<PostulationInfoDTO> postulationInfoList;
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
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the currentStep
	 */
	public int getCurrentStep() {
		return currentStep;
	}
	/**
	 * @param currentStep the currentStep to set
	 */
	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}
	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * @return the postulationInfoList
	 */
	public List<PostulationInfoDTO> getPostulationInfoList() {
		return postulationInfoList;
	}
	/**
	 * @param postulationInfoList the postulationInfoList to set
	 */
	public void setPostulationInfoList(List<PostulationInfoDTO> postulationInfoList) {
		this.postulationInfoList = postulationInfoList;
	} 
	
	
	

}
