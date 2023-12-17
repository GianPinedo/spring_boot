package com.blogapp.apis.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFindException extends RuntimeException {

	String entityName;
	String fieldName;
	Integer fieldValue;
	public ResourceNotFindException(String entityName, String fieldName, Integer fieldValue) {
		super(String.format("%s Not find with %s = %s",entityName, fieldName , fieldValue));
		this.entityName = entityName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
