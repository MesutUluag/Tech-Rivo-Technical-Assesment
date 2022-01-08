package com.techrivo.assesment.transformation.rest;

import com.techrivo.assesment.transformation.model.dto.TransformationDto;
import com.techrivo.assesment.transformation.model.dto.TransformationResponseDto;
import com.techrivo.assesment.transformation.service.TransformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class TransformationController {
	TransformationService transformationService;

	TransformationController(TransformationService transformationService){
		this.transformationService = transformationService;
	}

	@PostMapping(value = "transformation")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TransformationResponseDto> saveComment(@Valid @RequestBody TransformationDto transformationDto) {
		TransformationResponseDto responseDto;
		try {
			responseDto = transformationService.transform(transformationDto);
		}catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(responseDto);
	}
}
