package pe.edu.modelo;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DetalleProductoTest {

    private DetalleProducto detalle;

    @Before
    public void setUp() {
        detalle = new DetalleProducto(
            1,
            new Date(),   // fecha actual válida
            -10,           // cantidad válida (puede cambiar a negativa para otro test)
            5,
            3
        );
    }

    @Test
    public void testValidarCamposDetalleProducto() {
        System.out.println("Validando campos de DetalleProducto");

        // Validar idDetalleProducto > 0
        assertTrue("idDetalleProducto debe ser mayor que 0", detalle.getIdDetalleProducto() > 0);

        // Validar fecha no nula y no futura
        assertNotNull("Fecha no debe ser nula", detalle.getFecha());
        assertTrue("Fecha no debe ser futura", !detalle.getFecha().after(new Date()));

        // Validar cantidad != 0
        assertTrue("Cantidad no debe ser 0", detalle.getCantidad() != 0);

        // Validar idProducto > 0
        assertTrue("idProducto debe ser mayor que 0", detalle.getIdProducto() > 0);

        // Validar idTrabajador > 0
        assertTrue("idTrabajador debe ser mayor que 0", detalle.getIdTrabajador() > 0);
    }
}
