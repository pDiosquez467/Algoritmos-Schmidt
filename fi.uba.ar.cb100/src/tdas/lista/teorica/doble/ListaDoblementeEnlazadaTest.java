package tdas.lista.teorica.doble;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListaDoblementeEnlazadaTest {

    @Test
    void testAddLast() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();

        Integer elemento1 = 1;
        Integer elemento2 = 2;
        Integer elemento3 = 3;

        lista.addLast(elemento1);

        Assertions.assertEquals(1, lista.size());
        Assertions.assertEquals(elemento1, lista.get(0));

        lista.addLast(elemento2);
        lista.addLast(elemento3);

        Assertions.assertEquals(3, lista.size());
        Assertions.assertEquals(elemento3, lista.get(2));
        Assertions.assertEquals(elemento1, lista.get(0));
        Assertions.assertEquals(elemento2, lista.get(1));
    }

    @Test
    void testAddLast_conNulosYRepetidos() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.addLast("A");
        lista.addLast(null);
        lista.addLast("A");

        Assertions.assertEquals(3, lista.size());
        Assertions.assertNull(lista.get(1));
        Assertions.assertEquals("A", lista.get(2));
    }

    @Test
    void testAddLast_emptyToSingleElement() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        String elementoUnico = "Single";

        Assertions.assertEquals(0, lista.size());

        lista.addLast(elementoUnico);

        Assertions.assertEquals(1, lista.size());
        Assertions.assertEquals(elementoUnico, lista.get(0));
    }

    @Test
    void testAddLast_sequenceAndIntegrity() {
        ListaDoblementeEnlazada<Character> lista = new ListaDoblementeEnlazada<>();

        lista.addLast('X');
        lista.addLast('Y');
        lista.addLast('Z');
        lista.addLast('W');
        lista.addLast('V');

        Assertions.assertEquals(5, lista.size());
        Assertions.assertEquals('X', lista.get(0));
        Assertions.assertEquals('Y', lista.get(1));
        Assertions.assertEquals('Z', lista.get(2));
        Assertions.assertEquals('W', lista.get(3));
        Assertions.assertEquals('V', lista.get(4));
    }

    @Test
    void testAddLast_highVolume() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        int volumen = 10000;

        for (int i = 0; i < volumen; i++) {
            lista.addLast(i);
        }

        Assertions.assertEquals(volumen, lista.size());
        Assertions.assertEquals(0, lista.get(0));
        Assertions.assertEquals(volumen - 1, lista.get(volumen - 1));
        Assertions.assertEquals(5000, lista.get(5000));
    }

    @Test
    void testAddLast_nullAtBoundaries() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();

        lista.addLast(null);

        Assertions.assertEquals(1, lista.size());
        Assertions.assertNull(lista.get(0));

        lista.addLast("Intermedio");

        Assertions.assertEquals(2, lista.size());
        Assertions.assertEquals("Intermedio", lista.get(1));

        lista.addLast(null);

        Assertions.assertEquals(3, lista.size());
        Assertions.assertNull(lista.get(2));

        Assertions.assertNull(lista.get(0));
        Assertions.assertEquals("Intermedio", lista.get(1));
    }
}