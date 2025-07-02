/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pe.edu.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RolTest {

    private Rol rol;

    @Before
    public void setUp() {
        rol = new Rol();
        rol.setIdRol(1);
        rol.setNombre("Administrador");
        rol.setDescripcion("Rol con todos los permisos");
    }

    @Test
    public void testCamposGenerales() {
        System.out.println("Validando campos de Rol");

        assertEquals(1, rol.getIdRol());
        assertEquals("Administrador", rol.getNombre());
        assertEquals("Rol con todos los permisos", rol.getDescripcion());
    }
}
