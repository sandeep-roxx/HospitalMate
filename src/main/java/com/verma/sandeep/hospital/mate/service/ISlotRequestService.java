package com.verma.sandeep.hospital.mate.service;

import java.util.List;

import com.verma.sandeep.hospital.mate.entity.SlotRequest;

public interface ISlotRequestService {
	
	public void saveSlotRequest(SlotRequest slot);
	public List<SlotRequest> getAllSlotRequest();
	public void updateSlotRequestStatus(Long id, String status);
	 public List<SlotRequest> findSlotRequestsByPatientEmail(String email);

}
