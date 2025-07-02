/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pe.edu.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductoTest {

    private Producto producto;

    @Before
    public void setUp() {
        producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombre("Laptop");
        producto.setDescripcion("Laptop de alta gama");
        producto.setPrecio(1500.99);
        producto.setEstado("Activo");
        producto.setIdCategoriaProducto(3);
        producto.setIdMarcaProducto(2);
    }

    @Test
    public void testCamposGenerales() {
        System.out.println("Validando campos de Producto");

        assertEquals(1, producto.getIdProducto());
        assertEquals("Laptop", producto.getNombre());
        assertEquals("Laptop de alta gama", producto.getDescripcion());
        assertEquals(1500.99, producto.getPrecio(), 0.01);
        assertEquals("Activo", producto.getEstado());
        assertEquals(3, producto.getIdCategoriaProducto());
        assertEquals(2, producto.getIdMarcaProducto());
    }
}
