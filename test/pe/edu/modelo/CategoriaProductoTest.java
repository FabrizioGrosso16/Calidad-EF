/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

package pe.edu.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoriaProductoTest {

    private CategoriaProducto categoria;

    @Before
    public void setUp() {
        categoria = new CategoriaProducto();
        categoria.setIdCategoriaProducto(10);
        categoria.setNombre("Lechita Glorita");
        categoria.setDescripcion("es leche");
    }

    @Test
    public void testCamposGenerales() {
        System.out.println("Validando campos de CategoriaProducto");

        assertEquals(10, categoria.getIdCategoriaProducto());
        assertEquals("Lechita Glorita", categoria.getNombre());
        assertEquals("es leche", categoria.getDescripcion());
    }
}
