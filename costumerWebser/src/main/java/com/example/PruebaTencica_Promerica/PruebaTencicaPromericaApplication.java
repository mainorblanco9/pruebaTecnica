package com.example.PruebaTencica_Promerica;

import com.example.PruebaTencica_Promerica.dto.customerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class PruebaTencicaPromericaApplication {

	public static void main(String[] args) {


		SpringApplication.run(PruebaTencicaPromericaApplication.class, args);



	}
//
//	@Component
//	class AppRunner implements CommandLineRunner {
//
//		private final Scanner scanner = new Scanner(System.in);
//		private final RestTemplate restTemplate = new RestTemplate();
//		private static final String BASE_URL = "http://localhost:9001/customer/";
//		public  HttpHeaders headers = new HttpHeaders();
//
//
//		@Override
//		public void run(String... args) throws ParseException {
//
//			while (true) {
//				mostrarMenu();
//				int opcion = scanner.nextInt();
//				scanner.nextLine(); // Limpiar buffer
//				switch (opcion) {
//					case 1:
//						System.out.println("Ingrese el ID de cédula del cliente: ");
//						String idCedula = scanner.nextLine();
//
//						System.out.println("Ingrese el nombre del cliente: ");
//						String firstName = scanner.nextLine();
//
//						System.out.println("Ingrese el apellido del cliente: ");
//						String lastName = scanner.nextLine();
//
//						System.out.println("Ingrese el teléfono del cliente: ");
//						String phone = scanner.nextLine();
//
//						System.out.println("Ingrese la fecha de nacimiento (formato: yyyy-MM-dd): ");
//						String birthdateStr = scanner.nextLine();
//
//						customerDto newCustomer = new customerDto(idCedula, firstName, lastName, phone, birthdateStr);
//
//
//						ResponseEntity<customerDto> responseCreate = restTemplate.postForEntity(BASE_URL + "create", newCustomer, customerDto.class);
//						System.out.println("Cliente creado con éxito: " + responseCreate.getBody());
//						break;
//					case 2:
//						System.out.println("Ingrese el ID de cédula del cliente a eliminar: ");
//						String idToDelete = scanner.nextLine();
//
//
//						restTemplate.delete(BASE_URL + "delete/" + idToDelete);
//
//						System.out.println("Cliente con ID de cédula " + idToDelete + " eliminado con éxito.");
//						break;
//					case 3:
//						System.out.println("Ingrese el ID de cédula del cliente a actualizar: ");
//						String idToUpdate = scanner.nextLine();
//
//						System.out.println("Ingrese el nuevo 'firstName': ");
//						String newFirstName = scanner.nextLine();
//
//						System.out.println("Ingrese el nuevo 'lastName': ");
//						String newLastName = scanner.nextLine();
//
//						System.out.println("Ingrese el nuevo 'phone': ");
//						String newPhone = scanner.nextLine();
//
//						System.out.println("Ingrese la nueva 'birthdate' (en formato yyyy-MM-dd): ");
//						String newBirthdate = scanner.nextLine();
//
//
//						Map<String, String> updateData = new HashMap<>();
//						updateData.put("id_cedula", idToUpdate);
//						updateData.put("firstName", newFirstName);
//						updateData.put("lastName", newLastName);
//						updateData.put("phone", newPhone);
//						updateData.put("birthdate", newBirthdate);
//
//
//
//						headers.setContentType(MediaType.APPLICATION_JSON);
//
//						HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(updateData, headers);
//
//
//						ResponseEntity<Void> response = restTemplate.exchange(
//								BASE_URL + "update",
//								HttpMethod.PUT,
//								requestEntity,
//								Void.class
//						);
//
//
//						if (response.getStatusCode().is2xxSuccessful()) {
//							System.out.println("Cliente con ID de cédula " + idToUpdate + " actualizado con éxito.");
//						} else {
//							System.out.println("Error al actualizar el cliente.");
//						}
//						break;
//					case 4:
//						System.out.println("Ingrese el ID del cliente: ");
//						String idToFetch = scanner.nextLine();
//
//						HttpHeaders headers = new HttpHeaders();
//						headers.setContentType(MediaType.APPLICATION_JSON);
//
//						try {
//							ResponseEntity<String> responseEntity = restTemplate.exchange(
//									BASE_URL + "getById/" + idToFetch,
//									HttpMethod.GET,
//									new HttpEntity<>(headers),
//									String.class
//							);
//
//							if (responseEntity.getStatusCode() == HttpStatus.OK) {
//								System.out.println("Detalles del cliente: " + responseEntity.getBody());
//							} else {
//								System.out.println("Cliente no encontrado.");
//							}
//						} catch (HttpClientErrorException e) {
//							System.out.println("Error al realizar la solicitud: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
//						} catch (Exception e) {
//							System.out.println("Error desconocido: " + e.getMessage());
//						}
//					break;
//					case 5:
//						ResponseEntity<List<customerDto>> responseBirthdateDesc = restTemplate.exchange(
//								BASE_URL + "getByBirthdateDesc",
//								HttpMethod.GET,
//								null,
//								new ParameterizedTypeReference<List<customerDto>>() {}
//						);
//						if (responseBirthdateDesc.getStatusCode() == HttpStatus.OK && responseBirthdateDesc.getBody() != null) {
//							System.out.println("Clientes ordenados por fecha de nacimiento descendente: ");
//							for (customerDto customer : responseBirthdateDesc.getBody()) {
//								System.out.println(customer.toString());
//							}
//						} else {
//							System.out.println("No se pudieron obtener los clientes ordenados por fecha de nacimiento descendente.");
//						}
//						break;
//					case 6:
//						ResponseEntity<List<customerDto>> responseIdAsc = restTemplate.exchange(
//								BASE_URL + "getByIdAsc",
//								HttpMethod.GET,
//								null,
//								new ParameterizedTypeReference<List<customerDto>>() {}
//						);
//						if (responseIdAsc.getStatusCode() == HttpStatus.OK && responseIdAsc.getBody() != null) {
//							System.out.println("Clientes ordenados por ID ascendente: ");
//							for (customerDto customer : responseIdAsc.getBody()) {
//								System.out.println(customer.toString());
//							}
//						} else {
//							System.out.println("No se pudieron obtener los clientes ordenados por ID ascendente.");
//						}
//						break;
//					case 7:
//						ResponseEntity<List<customerDto>> responseFirstNameAsc = restTemplate.exchange(
//								BASE_URL + "getByFirstNameAsc",
//								HttpMethod.GET,
//								null,
//								new ParameterizedTypeReference<List<customerDto>>() {}
//						);
//						if (responseFirstNameAsc.getStatusCode() == HttpStatus.OK && responseFirstNameAsc.getBody() != null) {
//							System.out.println("Clientes ordenados por nombre ascendente: ");
//							for (customerDto customer : responseFirstNameAsc.getBody()) {
//								System.out.println(customer.toString());
//							}
//						} else {
//							System.out.println("No se pudieron obtener los clientes ordenados por nombre ascendente.");
//						}
//						break;
//					case 8:
//
//						System.out.println("Saliendo...");
//						return;
//					default:
//						System.out.println("Opción no válida. Por favor, elige una opción válida.");
//				}
//			}
//		}
//
//		private void mostrarMenu() {
//			System.out.println("----- Menú -----");
//			System.out.println("1. Crear cliente");
//			System.out.println("2. Eliminar cliente");
//			System.out.println("3. Actualizar cliente");
//			System.out.println("4. Obtener cliente por ID");
//			System.out.println("5) Ordenados por fecha de nacimiento descendente ");
//			System.out.println("6) Ordenados por id ");
//			System.out.println("7) Ordenados por nombre de manera ascendente ");
//			System.out.println("8. Salir");
//			System.out.println("-----------------");
//			System.out.print("Elige una opción: ");
//		}
//	}
}
