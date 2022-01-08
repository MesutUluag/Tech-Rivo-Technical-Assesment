package com.techrivo.assesment.transformation.service;

import com.techrivo.assesment.transformation.model.dto.TransformationDto;
import com.techrivo.assesment.transformation.model.dto.TransformationResponseDto;
import org.apache.commons.text.CaseUtils;
import org.springframework.stereotype.Service;
import javax.validation.ValidationException;
import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TransformationService {

	public TransformationResponseDto transform(TransformationDto transformationDto) {
		validateItems(transformationDto);
		Set<String> items = transformationDto.getItems().stream().map(item ->
			CaseUtils.toCamelCase(item, false, '_')
		).collect(Collectors.toSet());
		TransformationResponseDto responseDto = new TransformationResponseDto(transformationDto.getName(),
			items, new Date().getTime() / 1000);
		return responseDto;
	}

	public void validateItems(TransformationDto transformationDto) {
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
		transformationDto.getItems().forEach(item -> {
			if (pattern.matcher(item).matches()) {
				throw new ValidationException("Items must be number");
			}
		});
	}
}
