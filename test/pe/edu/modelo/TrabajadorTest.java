package pe.edu.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrabajadorTest {

    private Trabajador trabajador;

    @Before
    public void setUp() {
        trabajador = new Trabajador(
            1,
            "Juanchito",
            "12345678",    // DNI válido (8 dígitos)
            "Perez",
            "912345678",   // Teléfono válido (9 dígitos y empieza con 9)
            "juan.perez@mail.com",
            2,
            "clave123"
        );
    }
    @Test
    public void testValidarCamposTrabajador() {
        System.out.println("Validando campos de Trabajador");

        // Validar DNI: 8 dígitos numéricos
        String dni = trabajador.getDni();
        assertEquals("El DNI debe tener 8 dígitos", 8, dni.length());
        assertTrue("El DNI debe contener solo números", dni.matches("\\d{8}"));
        // Validar teléfono: 9 dígitos y empieza con 9
        String telefono = trabajador.getTelefono();
        assertEquals("El teléfono debe tener 9 dígitos", 9, telefono.length());
        assertTrue("El teléfono debe empezar con 9", telefono.startsWith("9"));
        assertTrue("El teléfono debe contener solo números", telefono.matches("\\d{9}"));
        // Validar correo básico: contiene '@' y '.'
        String correo = trabajador.getCorreo();
        assertTrue("El correo debe contener '@'", correo.contains("@"));
        assertTrue("El correo debe contener '.'", correo.contains("."));
        // Validar otros campos simples
        assertEquals(1, trabajador.getIdTrabajador());
        assertEquals("Juanchito", trabajador.getNombre());
        assertEquals("Perez", trabajador.getApellido());
        assertEquals(2, trabajador.getIdRol());
        assertEquals("clave123", trabajador.getClave());
    }
}
