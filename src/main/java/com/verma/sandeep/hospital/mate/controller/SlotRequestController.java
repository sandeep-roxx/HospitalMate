package com.verma.sandeep.hospital.mate.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verma.sandeep.hospital.mate.constant.SlotStatus;
import com.verma.sandeep.hospital.mate.entity.Appointment;
import com.verma.sandeep.hospital.mate.entity.Patient;
import com.verma.sandeep.hospital.mate.entity.SlotRequest;
import com.verma.sandeep.hospital.mate.service.IAppointmentService;
import com.verma.sandeep.hospital.mate.service.IPatientService;
import com.verma.sandeep.hospital.mate.service.ISlotRequestService;

@RestController
@RequestMapping("/slot")
public class SlotRequestController {
	
	@Autowired
	private IAppointmentService apmtService;
	@Autowired
	private IPatientService patientService;
	@Autowired
	private ISlotRequestService slotService;
	
	//Patient can book a slot per appointment
	@GetMapping("/book/{apmtId}")
	public ResponseEntity<String> bookSlot(@PathVariable Long apmtId,
			                                                                       Principal principal
			                                                                        )
	{
		//Get logged in patient email(username)
		String email=principal.getName();
		Appointment aptm=apmtService.getOneAppointment(apmtId);
		Patient patient=patientService.getOnePatient(email);
		//Create slotRequest object
		SlotRequest slot=new SlotRequest();
		slot.setAppointment(aptm);
		slot.setPatient(patient);
		slot.setStatus(SlotStatus.PENDING.name());
		try {
			slotService.saveSlotRequest(slot);
		}catch (Exception e) {
			return ResponseEntity.ok("You have already booked this appointment");
		}
		return ResponseEntity.ok("You have  booked this appointment ");
				
	}
	
	// Admin can view all slots
	@GetMapping("/all")
	public ResponseEntity<List<SlotRequest>> viewAllSlotRequests(){
		List<SlotRequest> slotList=slotService.getAllSlotRequest();
		return ResponseEntity.ok(slotList);
		
	}
	
	//Admin can accept or reject the slot requests
	@PutMapping("/update-status/{slotRequestId}")
	public ResponseEntity<String> updateSlotStatus(
	        @PathVariable Long slotRequestId,
	        @RequestParam String status // Accepts only "ACCEPTED" or "REJECTED"
	) {
	    // Validate status input
	    if (!status.equalsIgnoreCase(SlotStatus.ACCEPTED.name()) && !status.equalsIgnoreCase(SlotStatus.REJECTED.name())) {
	        return ResponseEntity.badRequest().body("Invalid status. Allowed values: ACCEPTED or REJECTED.");
	    }

	    // Call service to update status
	    slotService.updateSlotRequestStatus(slotRequestId, status);
	    SlotRequest sr = slotService.getOneSlotRequest(slotRequestId);
	    //Update the slot count for a appointment
	    if(sr.getStatus().equals(SlotStatus.ACCEPTED.name())) {
	    	//Decrease the slot count
	    	apmtService.updateSlotCountForAppointment(
	    			                                                                                sr.getAppointment().getId(),
	    			                                                                                Integer.valueOf(-1)
	    			                                                                                  );
	    }//if
	    
	    return ResponseEntity.ok("Slot request status updated to " + status);
	}
	
	//Patient can cancel the slot request
	@GetMapping("/cancel/slot-request/{slotRequestId}")
	public ResponseEntity<String> cancelSlotRequest(
			 @PathVariable Long slotRequestId
			)
	{
		// Call service to update status
	    slotService.updateSlotRequestStatus(slotRequestId, SlotStatus.CANCELLED.name());
	    SlotRequest sr = slotService.getOneSlotRequest(slotRequestId);
	    //Update the slot count for a appointment
	    if(sr.getStatus().equals(SlotStatus.ACCEPTED.name())) {
	    	//Increase slot count
	    	apmtService.updateSlotCountForAppointment(
	    			                                                                                sr.getAppointment().getId(),
	    			                                                                                Integer.valueOf(1)
	    			                                                                                  );
	    }//if
		return ResponseEntity.ok("Slot request cancelled successfully.");
		
	}
	
	//Logged-in patient can view his slot request
	@GetMapping("/patient/view-request")
	public ResponseEntity<List<SlotRequest>> viewMyRequests(Principal principal){
		String email=principal.getName();
		List<SlotRequest>slotRequestList = slotService.findSlotRequestsByPatientEmail(email);
		return ResponseEntity.ok(slotRequestList);
		
	}
	
	//Logged-in doctor can view his booked slots
	@GetMapping("/doctor/view-request")
	public ResponseEntity<List<SlotRequest>> viewMyBookedSlots(Principal principal){
		String email=principal.getName();
		List<SlotRequest>slotRequestList = slotService.findAllBookedSlotsByDoctor(email,SlotStatus.ACCEPTED.name());
		return ResponseEntity.ok(slotRequestList);
		
	}

}
