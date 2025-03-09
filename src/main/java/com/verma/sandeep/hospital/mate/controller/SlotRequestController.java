package com.verma.sandeep.hospital.mate.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
		slotService.saveSlotRequest(slot);
		return null;
		
		
	}

}
