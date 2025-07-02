/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pe.edu.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoriaProveedorTest {

    private CategoriaProveedor categoriaProveedor;

    @Before
    public void setUp() {
        categoriaProveedor = new CategoriaProveedor();
        categoriaProveedor.setIdCategoriaProveedor(100);
        categoriaProveedor.setNombreCategoria("Proveedor Mayorista");
        categoriaProveedor.setDescripcion("Categoría para proveedores que venden al por mayor");
    }

    @Test
    public void testCamposGenerales() {
        System.out.println("Validando campos de CategoriaProveedor");

        assertEquals(100, categoriaProveedor.getIdCategoriaProveedor());
        assertEquals("Proveedor Mayorista", categoriaProveedor.getNombreCategoria());
        assertEquals("Categoría para proveedores que venden al por mayor", categoriaProveedor.getDescripcion());
    }
}
