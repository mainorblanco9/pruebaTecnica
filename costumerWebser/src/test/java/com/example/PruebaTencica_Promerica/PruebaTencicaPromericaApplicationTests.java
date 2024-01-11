package com.example.PruebaTencica_Promerica;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PruebaTencicaPromericaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testCreateCustomer() throws Exception {

		String customerJson = "{ \"id_cedula\": \"504110673\", \"firstName\": \"Minor\", \"lastName\": \"Blanco\", \"phone\": \"83010\", \"birthdate\": \"1990-01-01\" }";

		// Realiza la solicitud POST y espera un status 201 (CREATED)
		mockMvc.perform(post("/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(customerJson))
				.andExpect(status().isCreated());
	}

	@Test
	void testDeleteCustomer() throws Exception {
		mockMvc.perform(delete("/customer/delete/{id}", "123456"))
				.andExpect(status().isOk());
	}

	@Test
	void testUpdateCustomer() throws Exception {

	}

	@Test
	void testGetCustomerById() throws Exception {
		mockMvc.perform(get("/customer/getById/{id_cedula}", "123456"))
				.andExpect(status().isOk());
	}

	@Test
	void testGetCustomersByBirthdateDesc() throws Exception {
		mockMvc.perform(get("/customer/getByBirthdateDesc"))
				.andExpect(status().isOk());
	}

	@Test
	void testGetCustomersByIdAsc() throws Exception {
		mockMvc.perform(get("/customer/getByIdAsc"))
				.andExpect(status().isOk());
	}

	@Test
	void testGetCustomersByFirstNameAsc() throws Exception {
		mockMvc.perform(get("/customer/getByFirstNameAsc"))
				.andExpect(status().isOk());
	}
}
