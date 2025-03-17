package com.verma.sandeep.hospital.mate.iservice;

import java.util.List;

import com.verma.sandeep.hospital.mate.dto.OperationDTO;

public interface OperationService {
	
	Long createOperation(OperationDTO operationDTO);
	List<OperationDTO> getAllOperations();
	OperationDTO getOperationById(Long id);
	void updateOperation(OperationDTO operationDTO);
	void deleteOperation(Long id);
	void updateOperationStatus(Long operationId,String status);
	String getOperationStatus(Long operationId);

}
