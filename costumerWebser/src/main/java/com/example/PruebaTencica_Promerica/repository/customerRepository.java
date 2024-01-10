package com.example.PruebaTencica_Promerica.repository;

import com.example.PruebaTencica_Promerica.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface customerRepository extends JpaRepository<customer, Long> {



    @Procedure(name = "sp_ingresar_cliente")
    public void ingresarCliente(
            @Param("id_cedula") int idCedula,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("phone") String phone,
            @Param("birthdate") Date birthdate
    );
    @Procedure(name = "sp_eliminar_cliente")
    public void eliminarClientePorId(@Param("id_cedula") String id_cedula);
    @Procedure(name = "sp_actualizar_cliente")
    public void actualizarCliente(
            @Param("id") Long id,
            @Param("id_cedula") int idCedula,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("phone") String phone,
            @Param("birthdate") Date birthdate
    );
    @Procedure(name = "sp_obtener_cliente_Id")
    public List<customer> obtenerClientePorIdCedula(@Param("id_cedula") String idCedula);

    @Procedure(name = "sp_obtener_cliente_fechaNacimiento_desc")
    List<customer> obtenerClientesOrdenadosPorFechaNacimientoDesc();

    @Procedure(name = "sp_Obtener_cliente_ordenado_porId")
    List<customer> obtenerClientesOrdenadosPorId();
    @Procedure(name = "sp_Obtener_cliente_ordenado_primerNombre_Asc")
    List<customer> obtenerClientesOrdenadosPorPrimerNombreAsc();
}