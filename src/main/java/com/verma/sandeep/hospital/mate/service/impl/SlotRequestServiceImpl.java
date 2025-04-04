package com.verma.sandeep.hospital.mate.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verma.sandeep.hospital.mate.dto.PatientDTO;
import com.verma.sandeep.hospital.mate.entity.SlotRequest;
import com.verma.sandeep.hospital.mate.exception.SlotRequestNotFoundException;
import com.verma.sandeep.hospital.mate.repository.SlotRequestRepository;

import jakarta.transaction.Transactional;

@Service
public class SlotRequestServiceImpl implements SlotRequestService {
	
	@Autowired
	private SlotRequestRepository slotRequestRepo;

	@Override
	public void saveSlotRequest(SlotRequest slot) {
		slotRequestRepo.save(slot);
		//TODO : Email

	}
	@Override
	public SlotRequest getOneSlotRequest(Long slotRequestId) {
		return slotRequestRepo.findById(slotRequestId)
				                                      .orElseThrow(()->new SlotRequestNotFoundException("Slot not found"));
	}

	//Admin can accept or reject requested slot
	@Override
	@Transactional
	public void updateSlotRequestStatus(Long id, String status) {
		slotRequestRepo.updateSlotRequestStatus(id, status);
		//TODO: Email sending

	}

	//Only patient can his slot requests
	@Override
	public List<SlotRequest> findSlotRequestsByPatientEmail(String email) {
		return slotRequestRepo.findSlotRequestsByPatientEmail(email);
	}

	//Admin can view all slots
	@Override
	public List<SlotRequest> getAllSlotRequest() {
		return slotRequestRepo.findAll();
	}

	//Logged-in doctor can view
	@Override
	public List<SlotRequest> findAllBookedSlotsByDoctor(String email,String status) {
		return slotRequestRepo.findAllBookedSlotsByDoctor(email,status);
	}
	@Override
	public Map<String, Object> createOrder(Long slotRequestId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<PatientDTO> fetchPaidPatients() {
		return slotRequestRepo.findPaidPatients();
	}

}
