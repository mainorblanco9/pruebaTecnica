package com.example.PruebaTencica_Promerica.repository;

import com.example.PruebaTencica_Promerica.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
            @Param("id_cedula") String id_cedula,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("phone") String phone,
            @Param("birthdate") String birthdate
    );
    @Query(value = "CALL sp_obtener_cliente_id(:p_id_cedula, :out_firstname, :out_lastname, :out_phone, :out_birthdate)", nativeQuery = true)
    List<Map<String, Object>> obtenerClientePorIdCedula(
    @Param("p_id_cedula") String idCedula,
    @Param("out_firstname") String firstName,
    @Param("out_lastname") String lastName,
    @Param("out_phone") String phone,
    @Param("out_birthdate") String birthdate);


    @Query(value = "CALL sp_obtener_cliente_fechaNacimiento_desc(:out_id_cedulas, :out_firstNames, :out_lastNames, :out_phones, :out_birthdates)", nativeQuery = true)
    List<Map<String, Object>> obtenerClientesOrdenadosPorFechaNacimientoDesc(@Param("out_id_cedulas") String[] out_id_cedulas,
                                                                             @Param("out_firstNames") String[] out_firstNames,
                                                                             @Param("out_lastNames") String[] out_lastNames,
                                                                             @Param("out_phones") String[] out_phones,
                                                                             @Param("out_birthdates") String[] out_birthdates);


    @Query(value = "CALL sp_obtener_clientes_ordenado_porid(:out_id_cedulas, :out_firstNames, :out_lastNames, :out_phones, :out_birthdates)", nativeQuery = true)
    List<Map<String, Object>> obtenerClientesOrdenadosPorId( @Param("out_id_cedulas") String[] out_id_cedulas,
                                                             @Param("out_firstNames") String[] out_firstNames,
                                                             @Param("out_lastNames") String[] out_lastNames,
                                                             @Param("out_phones") String[] out_phones,
                                                             @Param("out_birthdates") String[] out_birthdates);
    @Procedure(procedureName = "sp_Obtener_cliente_ordenado_primerNombre_Asc(NULL, NULL, NULL, NULL, NULL)")
    List<customer> obtenerClientesOrdenadosPorPrimerNombreAsc(@Param("p_id_cedula") String idCedula,
                                                              @Param("out_firstname") String firstName,
                                                              @Param("out_lastname") String lastName,
                                                              @Param("out_phone") String phone,
                                                              @Param("out_birthdate") String birthdate);
}
