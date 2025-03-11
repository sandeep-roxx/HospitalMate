package com.verma.sandeep.hospital.mate.bind;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
	
	private String dateTime;
	private String message;
	private int status;
	private String code;

}
