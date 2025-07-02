/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pe.edu.modelo;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.Arrays;
import java.util.List;

public class OrdenDeComprasTest {

    private OrdenDeCompras orden;

    private static final List<String> ESTADOS_VALIDOS = Arrays.asList("Aceptado", "Cancelado", "Pendiente");

    @Before
    public void setUp() {
        orden = new OrdenDeCompras(1, new Date(), 100.00, 2, 3, "Pendiente");
    }

    @Test
    public void testCamposGenerales() {
        assertEquals(1, orden.getIdOrdenCompra());
        assertNotNull("La fecha no debe ser nula", orden.getFechaOrden());
        assertEquals(100.00, orden.getTotal(), 0.01);
        assertEquals(2, orden.getIdTrabajador());
        assertEquals(3, orden.getIdProveedor());
        assertTrue("El estado debe ser válido", ESTADOS_VALIDOS.contains(orden.getEstado()));
    }
    @Test
    public void testFechaNoNula() {
        assertNotNull("La fecha de la orden no debe ser nula", orden.getFechaOrden());
    }
    @Test
    public void testTotalNoNegativo() {
        assertTrue("El total no debe ser negativo", orden.getTotal() >= 0);
    }
    @Test
    public void testEstadoValido() {
        assertTrue("El estado debe ser uno de Aceptado, Cancelado o Pendiente",
                ESTADOS_VALIDOS.contains(orden.getEstado()));
    }
}


