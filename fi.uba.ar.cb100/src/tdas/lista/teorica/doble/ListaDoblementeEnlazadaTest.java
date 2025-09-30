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
    void testAddEnMedio_Integridad() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();

        lista.addLast("A");
        lista.addLast("B"); // Índice 1
        lista.addLast("C");

        // La lista es: [A, B, C]

        // 1. Inserción: Se inserta 'X' en el índice 2 (después de 'B', antes de 'C')
        lista.add(2, "X");

        // La lista esperada es: [A, B, X, C]

        // 2. Verificación de tamaño
        Assertions.assertEquals(4, lista.size());

        // 3. Verificación de los elementos nuevos y reubicados
        Assertions.assertEquals("X", lista.get(2), "El nuevo elemento 'X' debe estar en el índice 2.");
        Assertions.assertEquals("B", lista.get(1), "El elemento 'B' debe mantenerse en el índice 1.");
        Assertions.assertEquals("C", lista.get(3), "El elemento 'C' se debe haber movido al índice 3.");
    }

    // ---

    @Test
    void testAddAlPrincipio_Integridad() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();

        lista.addLast(10);
        lista.addLast(20);

        // La lista es: [10, 20]

        // 1. Inserción: Se inserta 5 en el índice 0
        lista.add(0, 5);

        // La lista esperada es: [5, 10, 20]

        // 2. Verificación de tamaño
        Assertions.assertEquals(3, lista.size());

        // 3. Verificación de los elementos
        Assertions.assertEquals(5, lista.get(0), "El nuevo elemento '5' debe ser el primero.");
        Assertions.assertEquals(10, lista.get(1), "El antiguo primero '10' debe moverse al índice 1.");
        Assertions.assertEquals(20, lista.get(2), "El último elemento '20' no debe cambiar.");
    }

    // ---

    @Test
    void testAddAlFinal_Integridad() {
        ListaDoblementeEnlazada<Double> lista = new ListaDoblementeEnlazada<>();

        lista.addLast(1.1);
        lista.addLast(2.2);

        // La lista es: [1.1, 2.2]. El tamaño es 2.

        // 1. Inserción: Se inserta 9.9 en el índice 2 (el final)
        lista.add(2, 9.9);

        // La lista esperada es: [1.1, 2.2, 9.9]

        // 2. Verificación de tamaño
        Assertions.assertEquals(3, lista.size());

        // 3. Verificación de los elementos
        Assertions.assertEquals(9.9, lista.get(2), "El nuevo elemento debe estar en el índice del tamaño anterior (2).");
        Assertions.assertEquals(2.2, lista.get(1), "El penúltimo elemento debe mantenerse.");
        Assertions.assertEquals(1.1, lista.get(0), "El primer elemento debe mantenerse.");
    }

    // ---

    @Test
    void testAddEnListaVacia() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        String dato = "Solo";

        // 1. Inserción: Se inserta en el único índice válido (0) de una lista vacía.
        lista.add(0, dato);

        // 2. Verificación de tamaño
        Assertions.assertEquals(1, lista.size());

        // 3. Verificación de contenido
        Assertions.assertEquals(dato, lista.get(0));
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