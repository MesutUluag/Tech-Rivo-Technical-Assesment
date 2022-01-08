package com.techrivo.assesment.transformation.model.dto;

import lombok.NonNull;
import lombok.Value;
import java.util.Set;


@Value
public class TransformationDto {
	@NonNull
	private String name;

	@NonNull
	private Set<String> items;

}
