package tdas.pila.tests;

import org.junit.Test;
import tdas.pila.enlazada.Pila;
//import tdas.pila.dinamica.Pila;
import tdas.pila.excepciones.PilaException;

import static org.junit.Assert.*;

public class PilaTest {

    @Test
    public void testPilaVacia() {
        Pila<Integer> pila = new Pila<>();
        assertTrue(pila.estaVacia());
    }

    @Test
    public void testApilarYDesapilar() throws PilaException {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(1);
        assertFalse(pila.estaVacia());
        assertEquals(1, (int) pila.verTope());
        int dato = pila.desapilar();
        assertEquals(1, dato);
        assertTrue(pila.estaVacia());
    }

    @Test
    public void testApilarMultiplesElementos() throws PilaException {
        Pila<Integer> pila = new Pila<>();
        for (int i = 0; i < 100; i++) {
            pila.apilar(i);
        }
        assertEquals(99, (int) pila.verTope());
        for (int i = 99; i >= 0; i--) {
            assertEquals(i, (int) pila.desapilar());
        }
        assertTrue(pila.estaVacia());
    }

    @Test
    public void testRedimensionamiento() throws PilaException {
        Pila<Integer> pila = new Pila<>();

        for (int i = 0; i < 100; i++) {
            pila.apilar(i);
        }

        for (int i = 99; i >= 0; i--) {
            assertEquals(i, (int) pila.desapilar());
        }
        assertTrue(pila.estaVacia());
    }

    @Test
    public void testPilaConStrings() throws PilaException {
        Pila<String> pila = new Pila<>();
        pila.apilar("Hola");
        pila.apilar("Mundo");
        assertEquals("Mundo", pila.verTope());
        pila.desapilar();
        assertEquals("Hola", pila.verTope());
    }

    @Test(expected = PilaException.class)
    public void testDesapilarPilaVacia() throws PilaException {
        Pila<Integer> pila = new Pila<>();
        pila.desapilar();
    }

    @Test(expected = PilaException.class)
    public void testVerTopePilaVacia() throws PilaException {
        Pila<Integer> pila = new Pila<>();
        pila.verTope();
    }

    @Test
    public void testRedimensionamientoHaciaAbajo() throws PilaException {
        Pila<Integer> pila = new Pila<>();
        for (int i = 0; i < 100; i++) {
            pila.apilar(i);
        }
        for (int i = 0; i < 90; i++) {
            pila.desapilar();
        }
        assertEquals(9, (int) pila.verTope());
        for (int i = 9; i >= 0; i--) {
            assertEquals(i, (int) pila.desapilar());
        }
        assertTrue(pila.estaVacia());
    }
}