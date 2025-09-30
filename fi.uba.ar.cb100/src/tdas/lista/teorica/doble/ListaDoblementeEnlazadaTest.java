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


    @Test
    void testContains_PrimerElemento() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("Inicio");
        lista.add("Medio");
        lista.add("Fin");

        Assertions.assertTrue(lista.contains("Inicio"),
                "Debe retornar true al buscar el primer elemento de la lista.");
    }

    @Test
    void testContains_UltimoElemento() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("Inicio");
        lista.add("Medio");
        lista.add("Fin");

        // Prueba 2: El último elemento de la lista
        Assertions.assertTrue(lista.contains("Fin"),
                "Debe retornar true al buscar el último elemento de la lista.");
    }

    @Test
    void testContains_ElementoNulo() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("A");
        lista.add(null);
        lista.add("B");

        // Prueba 3: El elemento nulo
        Assertions.assertTrue(lista.contains(null),
                "Debe retornar true si el elemento nulo está presente.");
    }

    @Test
    void testContains_Repetido() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("Dato");
        lista.add("Repetido");
        lista.add("Dato");

        // Prueba 4: Un elemento repetido
        Assertions.assertTrue(lista.contains("Dato"),
                "Debe retornar true si un elemento repetido está presente.");
    }

    @Test
    void testContains_NoEncontrado() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("A");
        lista.add("B");
        lista.add("C");

        // Prueba 5: Un dato que no existe
        Assertions.assertFalse(lista.contains("X"),
                "Debe retornar false si el elemento no está en la lista.");
    }

    @Test
    void testContains_EnListaVacia() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();

        // Prueba 6: Búsqueda en una lista vacía
        Assertions.assertFalse(lista.contains("Dato"),
                "Debe retornar false al buscar cualquier cosa en una lista vacía.");
    }

    @Test
    void testContains_BuscarNuloNoExistente() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("A");
        lista.add("B");

        // Prueba 7: Buscar null en una lista que no lo contiene
        Assertions.assertFalse(lista.contains(null),
                "Debe retornar false al buscar null si este no está en la lista.");
    }

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

    @Test
    void testRemove_EnMedio_Integridad() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();

        lista.addLast("A"); // Índice 0
        lista.addLast("B"); // Índice 1 (El vecino anterior al removido)
        lista.addLast("C"); // Índice 2 (El nodo a remover)
        lista.addLast("D"); // Índice 3 (El vecino siguiente al removido)

        // 1. Verificación inicial
        Assertions.assertEquals(4, lista.size());

        // 2. Ejecutar la remoción
        String removido = lista.remove(2); // Se remueve "C"

        // 3. Verificación de resultado
        Assertions.assertEquals(3, lista.size(), "El tamaño debe ser 3.");
        Assertions.assertEquals("C", removido, "Se debe retornar el valor 'C'.");

        // 4. Verificación de re-conexión de vecinos: "B" debe apuntar a "D"
        Assertions.assertEquals("B", lista.get(1), "El vecino anterior ('B') debe mantenerse en el índice 1.");
        Assertions.assertEquals("D", lista.get(2), "El vecino siguiente ('D') debe moverse al índice 2.");
    }

    @Test
    void testRemove_AlFinal_Borde() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();

        lista.addLast(10);
        lista.addLast(20);
        lista.addLast(30); // Índice 2 (último)

        // 1. Verificación inicial
        Assertions.assertEquals(3, lista.size());

        // 2. Ejecutar la remoción
        Integer removido = lista.remove(2);

        // 3. Verificación de resultado
        Assertions.assertEquals(2, lista.size(), "El tamaño debe ser 2.");
        Assertions.assertEquals(30, removido, "Se debe retornar el valor 30.");

        // 4. Verificación de que el nuevo último elemento es el anterior (20)
        Assertions.assertEquals(20, lista.get(1), "El elemento en el nuevo último índice (1) debe ser 20.");

        // Asumimos que la implementación actualiza un puntero 'ultimo' de la lista
        // (No se puede probar sin acceso a 'ultimo', pero el get(1) lo confirma).
    }

    @Test
    void testRemove_AlPrincipio_Borde() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();

        lista.addLast(10); // Índice 0 (a remover)
        lista.addLast(20); // Índice 1 (nuevo primero)
        lista.addLast(30);

        // 1. Verificación inicial
        Assertions.assertEquals(3, lista.size());

        // 2. Ejecutar la remoción
        Integer removido = lista.remove(0);

        // 3. Verificación de resultado
        Assertions.assertEquals(2, lista.size(), "El tamaño debe ser 2.");
        Assertions.assertEquals(10, removido, "Se debe retornar el valor 10.");

        // 4. Verificación de que el nuevo primero es el 20
        Assertions.assertEquals(20, lista.get(0), "El elemento en el nuevo índice 0 debe ser 20.");

        // Asumimos que la implementación actualiza el puntero 'primero' a 20.
    }

    @Test
    void testRemove_ListaUnitaria() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("Uno Solo");

        // 1. Verificación inicial
        Assertions.assertEquals(1, lista.size());

        // 2. Ejecutar la remoción
        String removido = lista.remove(0);

        Assertions.assertEquals(0, lista.size(), "El tamaño debe ser 0.");
        Assertions.assertTrue(lista.isEmpty(), "La lista debe estar vacía.");
        Assertions.assertEquals("Uno Solo", removido);

    }

    @Test
    void testRemove_IndicesInvalidos_Excepciones() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.addLast(10);
        lista.addLast(20);

        // 1. Índice negativo
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.remove(-1);
        }, "Debe lanzar IndexOutOfBoundsException para índices negativos.");

        // 2. Índice igual o mayor al tamaño (fuera de rango)
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.remove(2); // El tamaño es 2, el índice válido máximo es 1
        }, "Debe lanzar IndexOutOfBoundsException para índice igual o mayor al tamaño.");
    }

    @Test
    void testIndexOf_ElementoEnMedio() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();

        lista.add(12);
        lista.add(24);
        lista.add(54);
        lista.add(56);

        // Búsqueda del elemento 54 (índice 2)
        Assertions.assertEquals(2, lista.indexOf(54),
                "Debe retornar 2 para el valor 54.");
    }

    @Test
    void testIndexOf_PrimerElemento() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("Inicio");
        lista.add("Medio");
        lista.add("Fin");

        // Búsqueda del primer elemento (índice 0)
        Assertions.assertEquals(0, lista.indexOf("Inicio"),
                "Debe retornar 0 para el primer elemento.");
    }

    @Test
    void testIndexOf_UltimoElemento() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("A");
        lista.add("B");
        lista.add("C");

        // Búsqueda del último elemento (índice 2)
        Assertions.assertEquals(2, lista.indexOf("C"),
                "Debe retornar el índice correcto para el último elemento.");
    }

    @Test
    void testIndexOf_ElementoNulo() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("A");
        lista.add(null);
        lista.add("B");

        // Búsqueda de un elemento nulo (índice 1)
        Assertions.assertEquals(1, lista.indexOf(null),
                "Debe retornar el índice del primer elemento nulo encontrado.");
    }

    @Test
    void testIndexOf_PrimeraOcurrenciaDeRepetido() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.add(10);
        lista.add(20);
        lista.add(10);
        lista.add(30);

        // Búsqueda de un elemento repetido, debe devolver la primera ocurrencia (índice 0)
        Assertions.assertEquals(0, lista.indexOf(10),
                "Debe retornar el índice de la primera ocurrencia (0).");
    }

    @Test
    void testIndexOf_ElementoInexistente() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("A");
        lista.add("B");

        // Búsqueda de un dato que no está presente
        Assertions.assertEquals(-1, lista.indexOf("Z"),
                "Debe retornar -1 si el elemento no se encuentra.");
    }

    @Test
    void testIndexOf_ListaVacia() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();

        // Búsqueda en una lista vacía
        Assertions.assertEquals(-1, lista.indexOf(99),
                "Debe retornar -1 al buscar en una lista vacía.");
    }

    @Test
    void testIndexOf_BuscarNuloNoExistente() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("A");
        lista.add("B");

        // Búsqueda de null en una lista sin nulls
        Assertions.assertEquals(-1, lista.indexOf(null),
                "Debe retornar -1 al buscar un valor nulo que no está presente.");
    }

    @Test
    void testSet_ReemplazoCompletoYRetorno() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.add(10);  // Índice 0
        lista.add(20);  // Índice 1
        lista.add(30);  // Índice 2

        // 1. Reemplazar el elemento del medio (índice 1)
        Integer valorAnterior = lista.set(1, 200);

        // Verificación del valor de retorno
        Assertions.assertEquals(20, valorAnterior,
                "set debe retornar el valor anterior (20).");

        // 2. Verificación del valor nuevo
        Assertions.assertEquals(200, lista.get(1),
                "El valor en el índice 1 debe ser 200.");

        // 3. Verificación de que el tamaño y los vecinos no cambiaron
        Assertions.assertEquals(3, lista.size(), "El tamaño no debe cambiar.");
        Assertions.assertEquals(10, lista.get(0), "El vecino 0 no debe cambiar.");
        Assertions.assertEquals(30, lista.get(2), "El vecino 2 no debe cambiar.");
    }

    // ---

    @Test
    void testSet_CasosLimite() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("Primero");
        lista.add("Medio");
        lista.add("Ultimo");

        // 1. Reemplazar el primer elemento (índice 0)
        String antiguoPrimero = lista.set(0, "NuevoInicio");
        Assertions.assertEquals("Primero", antiguoPrimero,
                "El valor retornado para el índice 0 debe ser 'Primero'.");
        Assertions.assertEquals("NuevoInicio", lista.get(0),
                "El elemento en el índice 0 debe ser 'NuevoInicio'.");

        // 2. Reemplazar el último elemento (índice size - 1)
        String antiguoUltimo = lista.set(2, "NuevoFin");
        Assertions.assertEquals("Ultimo", antiguoUltimo,
                "El valor retornado para el último índice (2) debe ser 'Ultimo'.");
        Assertions.assertEquals("NuevoFin", lista.get(2),
                "El elemento en el índice 2 debe ser 'NuevoFin'.");

        // 3. Verificar que el elemento del medio no se modificó
        Assertions.assertEquals("Medio", lista.get(1),
                "El elemento intermedio no debe haberse alterado.");
    }

    // ---

    @Test
    void testSet_ManejoDeNulos() {
        ListaDoblementeEnlazada<String> lista = new ListaDoblementeEnlazada<>();
        lista.add("NoNulo");
        lista.add(null);

        // Caso A: Reemplazar un valor nulo por un valor no nulo
        String retornoA = lista.set(1, "NuevoValor");
        Assertions.assertNull(retornoA,
                "Al reemplazar null, debe retornar null.");
        Assertions.assertEquals("NuevoValor", lista.get(1),
                "El valor debe ser reemplazado correctamente por uno no nulo.");

        // Caso B: Reemplazar un valor no nulo por un valor nulo
        String retornoB = lista.set(0, null);
        Assertions.assertEquals("NoNulo", retornoB,
                "Al reemplazar 'NoNulo', debe retornar 'NoNulo'.");
        Assertions.assertNull(lista.get(0),
                "El valor debe ser reemplazado correctamente por null.");
    }

    // ---

    @Test
    void testSet_IndicesInvalidos() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.add(10);

        // 1. Índice negativo
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.set(-1, 99);
        }, "Debe lanzar IndexOutOfBoundsException para índices negativos.");

        // 2. Índice igual al tamaño (fuera de rango)
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.set(1, 99); // El tamaño es 1, el índice válido es solo 0
        }, "Debe lanzar IndexOutOfBoundsException para índice igual al tamaño.");
    }
}