package com.verma.sandeep.hospital.mate.iservice;

import java.util.List;

import com.verma.sandeep.hospital.mate.dto.WardDTO;
import com.verma.sandeep.hospital.mate.entity.Ward;

public interface WardService {
	
	Long allocateWard(WardDTO wardDTO);
	void addPatientsToWard(Long wardId, List<Long> patientIds);
    List<Ward> getAllWards();
    WardDTO getWardById(Long id);
    void updateOneWard(WardDTO wardDTO);
    void deleteWard(Long id);

}
