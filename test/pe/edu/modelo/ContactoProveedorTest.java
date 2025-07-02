package pe.edu.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ContactoProveedorTest {

    private ContactoProveedor contacto;

    @Before
    public void setUp() {
        contacto = new ContactoProveedor(
            1,
            "Carlos Lopez",
            "912345678",       // Teléfono válido (9 dígitos y empieza con 9)
            "carlos@mail.com", // Email válido (contiene @ y .)
            1
        );
    }

    @Test
    public void testValidarCamposContactoProveedor() {
        System.out.println("Validando campos de ContactoProveedor");

        // Validar teléfono: 9 dígitos y empieza con 9
        String telefono = contacto.getTelefono();
        assertEquals("El teléfono debe tener 9 dígitos", 9, telefono.length());
        assertTrue("El teléfono debe empezar con 9", telefono.startsWith("9"));
        assertTrue("El teléfono debe contener solo números", telefono.matches("\\d{9}"));

        // Validar email básico: contiene '@' y '.'
        String email = contacto.getEmail();
        assertTrue("El email debe contener '@'", email.contains("@"));
        assertTrue("El email debe contener '.'", email.contains("."));

        // Validar otros campos simples
        assertEquals(1, contacto.getIdContacto());
        assertEquals("Carlos Lopez", contacto.getNombre());
        assertEquals(1, contacto.getIdProveedor());
    }
}
