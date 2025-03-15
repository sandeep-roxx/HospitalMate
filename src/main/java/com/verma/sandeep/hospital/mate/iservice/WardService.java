package com.verma.sandeep.hospital.mate.iservice;

import java.util.List;

import com.verma.sandeep.hospital.mate.entity.Ward;

public interface WardService {
	
	Long allocateWard(Ward ward);
    List<Ward> getAllWards();
    Ward getWardById(Long id);
    void deleteWard(Long id);

}
