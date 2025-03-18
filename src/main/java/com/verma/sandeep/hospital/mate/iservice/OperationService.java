package com.verma.sandeep.hospital.mate.iservice;

import java.util.List;

import com.verma.sandeep.hospital.mate.constant.OperationStatus;
import com.verma.sandeep.hospital.mate.dto.OperationDTO;

public interface OperationService {
	
	Long createOperation(OperationDTO operationDTO);
	List<OperationDTO> getAllOperations();
	OperationDTO getOperationById(Long id);
	void updateOperation(OperationDTO operationDTO);
	void deleteOperation(Long id);
	void updateOperationStatus(Long operationId,OperationStatus status);
	String getOperationStatus(Long operationId);

}
