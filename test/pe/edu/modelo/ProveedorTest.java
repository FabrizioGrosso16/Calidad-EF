/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pe.edu.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProveedorTest {

    private Proveedor proveedor;

    @Before
    public void setUp() {
        // Datos válidos para iniciar el objeto
        proveedor = new Proveedor(
            1,
            "Empresa XYZ",
            "20123456787",  // RUC válido (empieza con 20 y tiene 11 dígitos)
            "Av. Principal 123",
            "contacto@empresa.com",
            5
        );
    }

    @Test
    public void testValidarCamposProveedor() {
        System.out.println("Validando campos de Proveedor");

        // Validar RUC: comienza con 10 o 20 y tiene 11 dígitos
        String ruc = proveedor.getRuc();
        assertTrue("El RUC debe comenzar con 10 o 20", ruc.startsWith("10") || ruc.startsWith("20"));
        assertEquals("El RUC debe tener 11 dígitos", 11, ruc.length());
        assertTrue("El RUC debe contener solo números", ruc.matches("\\d{11}"));

        // Validar correo: debe contener '@' y '.'
        String correo = proveedor.getCorreo();
        assertTrue("El correo debe contener '@'", correo.contains("@"));
        assertTrue("El correo debe contener '.'", correo.contains("."));

        // Validar otros campos simples
        assertEquals(1, proveedor.getIdProveedor());
        assertEquals("Empresa XYZ", proveedor.getRazonSocial());
        assertEquals("Av. Principal 123", proveedor.getDireccion());
        assertEquals(5, proveedor.getIdCategoriaProveedor());
    }
}
