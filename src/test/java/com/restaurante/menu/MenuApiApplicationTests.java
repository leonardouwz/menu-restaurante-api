package com.restaurante.menu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MenuApiApplicationTests {

	@Autowired
	MockMvc mvc;

	@Test
	void postCreaPlatoYGetLoDevuelve() throws Exception {
		mvc.perform(post("/api/platos").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nombre\":\"Aji de gallina\",\"categoria\":\"Fondo\",\"precio\":25.0,\"disponible\":true}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(3));

		mvc.perform(get("/api/platos/3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nombre").value("Aji de gallina"));
	}

	@Test
	void postInvalidoDevuelve400() throws Exception {
		mvc.perform(post("/api/platos").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nombre\":\"\",\"precio\":-1}"))
				.andExpect(status().isBadRequest());
	}
}
