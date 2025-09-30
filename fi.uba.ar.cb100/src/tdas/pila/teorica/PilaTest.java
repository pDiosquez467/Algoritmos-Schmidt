package tdas.pila.teorica;

import org.junit.jupiter.api.Test;
import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import static org.junit.jupiter.api.Assertions.*;

class PilaTest {

    // Tests de comportamiento básico
    @Test
    void testPilaRecienCreadaEstaVacia() {
        Pila<Integer> pila = new Pila<>();
        assertTrue(pila.estaVacia());
    }

    @Test
    void testCantidadElementosPilaVacia() {
        Pila<String> pila = new Pila<>();
        assertEquals(0, pila.cantidadElementos());
    }

    @Test
    void testApilarUnElementoIncrementaTamanio() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(10);
        assertEquals(1, pila.cantidadElementos());
    }

    @Test
    void testApilarUnElementoNoEstaVacia() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(5);
        assertFalse(pila.estaVacia());
    }

    @Test
    void testDesapilarElementoCorrecto() {
        Pila<String> pila = new Pila<>();
        pila.apilar("test");
        assertEquals("test", pila.desapilar());
    }

    @Test
    void testDesapilarDisminuyeTamanio() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(1);
        pila.apilar(2);
        pila.desapilar();
        assertEquals(1, pila.cantidadElementos());
    }

    @Test
    void testObtenerTopeSinDesapilar() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(42);
        assertEquals(42, pila.obtener());
        assertEquals(1, pila.cantidadElementos());
    }

    // Tests de múltiples operaciones
    @Test
    void testApilarMultiplesElementos() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);
        assertEquals(3, pila.cantidadElementos());
    }

    @Test
    void testDesapilarEnOrdenLIFO() {
        Pila<Character> pila = new Pila<>();
        pila.apilar('a');
        pila.apilar('b');
        pila.apilar('c');

        assertEquals('c', pila.desapilar());
        assertEquals('b', pila.desapilar());
        assertEquals('a', pila.desapilar());
    }

    @Test
    void testObtenerSiempreDevuelveTope() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(100);
        assertEquals(100, pila.obtener());

        pila.apilar(200);
        assertEquals(200, pila.obtener());

        pila.desapilar();
        assertEquals(100, pila.obtener());
    }

    // Tests de casos límite
    @Test
    void testDesapilarHastaVacia() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(1);
        pila.apilar(2);
        pila.desapilar();
        pila.desapilar();
        assertTrue(pila.estaVacia());
        assertEquals(0, pila.cantidadElementos());
    }

    @Test
    void testDesapilarPilaVaciaLanzaExcepcion() {
        Pila<Integer> pila = new Pila<>();
        assertThrows(RuntimeException.class, pila::desapilar);
    }

    @Test
    void testObtenerPilaVaciaLanzaExcepcion() {
        Pila<String> pila = new Pila<>();
        assertThrows(RuntimeException.class, pila::obtener);
    }

    // Tests de integración con Lista
    @Test
    void testApilarListaVacia() {
        Pila<Integer> pila = new Pila<>();
        ListaSimplementeEnlazada<Integer> lista = new ListaSimplementeEnlazada<>();

        pila.apilar(lista);
        assertTrue(pila.estaVacia());
    }

    @Test
    void testApilarListaConElementos() {
        Pila<Integer> pila = new Pila<>();
        ListaSimplementeEnlazada<Integer> lista = new ListaSimplementeEnlazada<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);

        pila.apilar(lista);
        assertEquals(3, pila.cantidadElementos());
        assertEquals(3, pila.obtener());
    }

    @Test
    void testApilarListaIncrementaTamanioCorrectamente() {
        Pila<Integer> pila = new Pila<>();
        ListaSimplementeEnlazada<Integer> lista = new ListaSimplementeEnlazada<>();
        lista.add(10);
        lista.add(20);

        pila.apilar(lista);
        assertEquals(2, pila.cantidadElementos());
    }

    @Test
    void testOrdenElementosAlApilarLista() {
        Pila<Integer> pila = new Pila<>();
        ListaSimplementeEnlazada<Integer> lista = new ListaSimplementeEnlazada<>();
        lista.add(1);
        lista.add(2);

        pila.apilar(lista);
        assertEquals(2, pila.desapilar());
        assertEquals(1, pila.desapilar());
    }

    // Tests de tipos genéricos
    @Test
    void testPilaConStrings() {
        Pila<String> pila = new Pila<>();
        pila.apilar("hola");
        pila.apilar("mundo");
        assertEquals("mundo", pila.desapilar());
        assertEquals("hola", pila.obtener());
    }

    @Test
    void testPilaConDoubles() {
        Pila<Double> pila = new Pila<>();
        pila.apilar(3.14);
        pila.apilar(2.71);
        assertEquals(2.71, pila.desapilar(), 0.001);
        assertEquals(3.14, pila.obtener(), 0.001);
    }

    @Test
    void testPilaConBooleanos() {
        Pila<Boolean> pila = new Pila<>();
        pila.apilar(true);
        pila.apilar(false);
        assertFalse(pila.desapilar());
        assertTrue(pila.obtener());
    }

    // Tests de comportamiento tras vaciar
    @Test
    void testReapilarTrasVaciar() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(1);
        pila.desapilar();
        pila.apilar(2);

        assertEquals(1, pila.cantidadElementos());
        assertEquals(2, pila.obtener());
    }

    @Test
    void testComportamientoSecuencialComplejo() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(1);
        pila.apilar(2);
        pila.desapilar();
        pila.apilar(3);
        pila.apilar(4);
        pila.desapilar();

        assertEquals(2, pila.cantidadElementos());
        assertEquals(3, pila.obtener());
    }

    // Tests de integridad estructural
    @Test
    void testTamanioConsistenteTrasOperaciones() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(1);
        pila.apilar(2);
        assertEquals(2, pila.cantidadElementos());

        pila.desapilar();
        assertEquals(1, pila.cantidadElementos());

        pila.desapilar();
        assertEquals(0, pila.cantidadElementos());
    }

    @Test
    void testTopeCorrectoTrasMultiplesOperaciones() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(10);
        pila.apilar(20);
        pila.apilar(30);

        pila.desapilar();
        assertEquals(20, pila.obtener());

        pila.apilar(40);
        assertEquals(40, pila.obtener());
    }

    // Tests de volumen
    @Test
    void testApilarMultiplesElementosSecuencialmente() {
        Pila<Integer> pila = new Pila<>();
        for (int i = 0; i < 1000; i++) {
            pila.apilar(i);
        }
        assertEquals(1000, pila.cantidadElementos());
        assertEquals(999, pila.obtener());
    }

    @Test
    void testDesapilarMultiplesElementosSecuencialmente() {
        Pila<Integer> pila = new Pila<>();
        for (int i = 0; i < 1000; i++) {
            pila.apilar(i);
        }

        for (int i = 999; i >= 0; i--) {
            assertEquals(i, pila.desapilar());
        }
        assertTrue(pila.estaVacia());
    }

    @Test
    void testApilarListaGrande() {
        Pila<Integer> pila = new Pila<>();
        ListaSimplementeEnlazada<Integer> lista = new ListaSimplementeEnlazada<>();
        for (int i = 0; i < 100; i++) {
            lista.add(i);
        }

        pila.apilar(lista);
        assertEquals(100, pila.cantidadElementos());
        assertEquals(99, pila.desapilar());
    }
}