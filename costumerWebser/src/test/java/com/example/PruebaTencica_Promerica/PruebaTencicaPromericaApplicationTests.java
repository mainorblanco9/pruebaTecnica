package com.example.PruebaTencica_Promerica;

import com.example.PruebaTencica_Promerica.dto.customerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PruebaTencicaPromericaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testCreateCustomer() throws Exception {
		customerDto customer = new customerDto("451147414", "PruebaUnitaria", "ApellidoPrueba", "021101744", "1990-01-01");
		String customerJson = objectMapper.writeValueAsString(customer);

		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/customer/crearcliente")
						.contentType(MediaType.APPLICATION_JSON)
						.content(customerJson))
				.andExpect(status().isCreated());
	}
	@Test
	void testDeleteCustomer() throws Exception {
		mockMvc.perform(delete("/customer/eliminarcliente/{id_cedula}", "504110673"))
				.andExpect(status().isOk());
	}

	@Test
	void testUpdateCustomer() throws Exception {
		customerDto customer = new customerDto("451147414", "UpdatedFirstName", "UpdatedLastName", "UpdatedPhone", "1990-01-01");
		String customerJson = objectMapper.writeValueAsString(customer);

		mockMvc.perform(MockMvcRequestBuilders
						.request(HttpMethod.PUT, "/customer/actualizar")
						.contentType(MediaType.APPLICATION_JSON)
						.content(customerJson.getBytes()))
				.andExpect(status().isOk());
	}
	@Test
	void testGetCustomerById() throws Exception {
		mockMvc.perform(get("/customer/obtenerclienteporId/{idCedula}", "504110673"))
				.andExpect(status().isOk());
	}

	@Test
	void testGetCustomersByBirthdateDesc() throws Exception {
		mockMvc.perform(get("/customer/ordenadosPorFechadeNacimientoDescendente"))
				.andExpect(status().isOk());
	}

	@Test
	void testGetCustomersByIdAsc() throws Exception {
		mockMvc.perform(get("/customer/OrdenadosPorId"))
				.andExpect(status().isOk());
	}

	@Test
	void testGetCustomersByFirstNameAsc() throws Exception {
		mockMvc.perform(get("/customer/OrdenadosPorNombreDeManeraAscendente"))
				.andExpect(status().isOk());
	}
}
