package com.techrivo.assesment.transformation.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.techrivo.assesment.transformation.model.dto.TransformationDto;
import com.techrivo.assesment.transformation.service.TransformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.*;


@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class TransformTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	TransformationController transformationController;

	@MockBean
	TransformationService transformationService;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void transform() throws Exception {
		Set<String> items = new HashSet<>(Arrays.asList("foo_bar", "bar", "bar", "baz"));
		TransformationDto transformationDto = new TransformationDto("Mesut", items);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(transformationDto);

		mockMvc.perform(post("/transformation")
				.contentType(APPLICATION_JSON_UTF8)
				.content(json))
			.andExpect(status().isOk());
	}

}
