package com.example.PruebaTencica_Promerica.repository;

import com.example.PruebaTencica_Promerica.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface customerRepository extends JpaRepository<customer, Long> {

    @Procedure(procedureName = "sp_ingresar_cliente")
    public void ingresarCliente(
            @Param("id_cedula") String id_cedula,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("phone") String phone,
            @Param("birthdate") String birthdate
    );
    @Procedure(procedureName = "sp_eliminar_cliente")
    public void eliminarClientePorId(@Param("id_cedula") String id_cedula);

    @Procedure(procedureName = "sp_actualizar_cliente")
    public void actualizarCliente(
            @Param("id") Long id,
            @Param("id_cedula") String id_cedula,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("phone") String phone,
            @Param("birthdate") String birthdate
    );
    @Procedure(procedureName = "sp_obtener_cliente_Id")
    public List<customer> obtenerClientePorIdCedula(@Param("id_cedula") String idCedula );

    @Procedure(procedureName = "sp_obtener_cliente_fechaNacimiento_desc")
    List<customer> obtenerClientesOrdenadosPorFechaNacimientoDesc();

    @Procedure(procedureName = "sp_Obtener_cliente_ordenado_porId")
    List<customer> obtenerClientesOrdenadosPorId();

    @Procedure(procedureName = "sp_Obtener_cliente_ordenado_primerNombre_Asc")
    List<customer> obtenerClientesOrdenadosPorPrimerNombreAsc();
}
