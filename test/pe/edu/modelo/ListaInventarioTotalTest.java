/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pe.edu.modelo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Estudiante
 */

public class ListaInventarioTotalTest {

    private ListaInventarioTotal inventario;

    @Before
    public void setUp() {
        inventario = new ListaInventarioTotal();
        inventario.setIdLIT(1);
        inventario.setCantidad(0);
        inventario.setPrecio(0);
        inventario.setLote("L12345");
        inventario.setIdTrabajador(2);
        inventario.setIdProducto(5);
    }

    @Test
    public void testCamposGenerales() {
        assertEquals(1, inventario.getIdLIT());
        assertEquals(999, inventario.getCantidad());
        assertEquals(0, inventario.getPrecio(), 0.01);
        assertEquals("L12345", inventario.getLote());
        assertEquals(2, inventario.getIdTrabajador());
        assertEquals(5, inventario.getIdProducto());
    }

    @Test
    public void testCantidadNoCero() {
         System.out.println("Valida la cantidad no sea menor a 0");
        assertTrue("La cantidad debe ser mayor que 0", inventario.getCantidad() >= 0);
    }

    @Test
    public void testPrecioNoCero() {
        System.out.println("Valida que el precio no sea nulo");
        assertTrue("El precio debe ser mayor que 0", inventario.getPrecio() > 0.0);
    }
}
