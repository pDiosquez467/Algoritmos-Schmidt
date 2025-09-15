package tdas.lista;

public class Main {

    public static void main(String[] args) {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();

        lista.append(12);
        lista.append(23);
        lista.append(43);
        lista.append(98);

        System.out.println(lista);
        // lista.insert(-100, 100);
        // lista.remove(98);
        Integer dato = lista.pop(-1);
        System.out.println(dato);
        System.out.println(lista);
    }
}
