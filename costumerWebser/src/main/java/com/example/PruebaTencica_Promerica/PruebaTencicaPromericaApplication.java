package com.example.PruebaTencica_Promerica;

import com.example.PruebaTencica_Promerica.dto.customerDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class PruebaTencicaPromericaApplication {

	public static void main(String[] args) {


		SpringApplication.run(PruebaTencicaPromericaApplication.class, args);



	}

//	@Component
//	class AppRunner implements CommandLineRunner {
//
//		private final Scanner scanner = new Scanner(System.in);
//		private final RestTemplate restTemplate = new RestTemplate();
//		private static final String BASE_URL = "http://localhost:9001/customer/";
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
//						int idCedula = scanner.nextInt();
//						scanner.nextLine();
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
//						Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdateStr);
//
//						customerDto newCustomer = new customerDto(idCedula, firstName, lastName, phone, birthdate);
//
//						// Ahora, utiliza RestTemplate para enviar esta información al endpoint de creación
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
//						String jsonUpdate = "{" +
//								"\"id_cedula\": \"" + idToUpdate + "\", " +
//								"\"firstName\": \"" + newFirstName + "\", " +
//								"\"lastName\": \"" + newLastName + "\", " +
//								"\"phone\": \"" + newPhone + "\", " +
//								"\"birthdate\": \"" + newBirthdate + "\"" +
//								"}";
//
//						// Llamada al endpoint PUT para actualizar el cliente
//						restTemplate.put(BASE_URL + "update", jsonUpdate);
//
//						System.out.println("Cliente con ID de cédula " + idToUpdate + " actualizado con éxito.");
//						break;
//					case 4:
//						System.out.println("Ingrese el ID del cliente: ");
//						String idToFetch = scanner.nextLine();
//
//						// Realizar una solicitud GET para obtener detalles del cliente por su ID
//						ResponseEntity<customerDto> responseGetById = restTemplate.getForEntity(BASE_URL + "getById/" + idToFetch, customerDto.class);
//
//						// Verificar si se encontró el cliente
//						if (responseGetById.getStatusCode() == HttpStatus.OK) {
//							System.out.println("Detalles del cliente: " + responseGetById.getBody());
//						} else {
//							System.out.println("Cliente no encontrado.");
//						}
//						break;
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
//							System.out.println("Clientes ordenados por ID: ");
//							for (customerDto customer : responseIdAsc.getBody()) {
//								System.out.println(customer.toString());
//							}
//						} else {
//							System.out.println("No se pudieron obtener los clientes ordenados por ID.");
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
//			System.out.println("5) Ordenados por fecha de nacimiento descendente 5pts.");
//			System.out.println("6) Ordenados por id 5pts.");
//			System.out.println("7) Ordenados por nombre de manera ascendente 5pts.");
//			System.out.println("8. Salir");
//			System.out.println("-----------------");
//			System.out.print("Elige una opción: ");
//		}
//	}
}
