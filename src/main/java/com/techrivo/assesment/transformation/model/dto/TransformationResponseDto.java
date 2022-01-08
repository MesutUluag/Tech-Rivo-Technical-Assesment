package com.techrivo.assesment.transformation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Set;

@AllArgsConstructor
@Value
public class TransformationResponseDto {
	String name;
	Set<String> items;
	long timeStamp;
}
