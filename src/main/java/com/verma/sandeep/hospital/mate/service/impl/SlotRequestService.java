package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;
import java.util.Map;

import com.verma.sandeep.hospital.mate.dto.PatientDTO;
import com.verma.sandeep.hospital.mate.entity.SlotRequest;

public interface SlotRequestService {
	
	public void saveSlotRequest(SlotRequest slot);
	public SlotRequest getOneSlotRequest(Long slotRequestId);
	public List<SlotRequest> getAllSlotRequest();
	public void updateSlotRequestStatus(Long id, String status);
	 public List<SlotRequest> findSlotRequestsByPatientEmail(String email);
	 public List<SlotRequest> findAllBookedSlotsByDoctor(String email,String status);
	 public Map<String, Object> createOrder(Long slotRequestId);
	// Fetch all patients who have paid for an appointment and return as DTO
	public  List<PatientDTO> fetchPaidPatients();

}
