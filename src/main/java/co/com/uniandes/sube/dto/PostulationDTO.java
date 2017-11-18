package co.com.uniandes.sube.dto;

import java.util.Date;
import java.util.List;


public class PostulationDTO {
	
	private int id;
	private AcademicOfferDTO offer;
	private Date creationDate;
	private int state;
	private OfferStepDTO currentStep;
	private UserDTO user;
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
	 * @return the offer
	 */
	public AcademicOfferDTO getOffer() {
		return offer;
	}
	/**
	 * @param offer the offer to set
	 */
	public void setOffer(AcademicOfferDTO offer) {
		this.offer = offer;
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
	/**
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}
	/**
	 * @param currentStep the currentStep to set
	 */
	public void setCurrentStep(OfferStepDTO currentStep) {
		this.currentStep = currentStep;
	}
	/**
	 * @return the currentStep
	 */
	public OfferStepDTO getCurrentStep() {
		return currentStep;
	} 
}
