package semana03.ejercicios.nivel02.biblioteca;

import semana03.ejercicios.utils.Validaciones;

import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Biblioteca {
    private final int CAPACIDAD_INICIAL = 19;

    private String nombre;
    private LibroEnBiblioteca[] libros;
    private int indiceGuardado;

    public Biblioteca(String nombre) {
        this.setNombre(nombre);
        this.libros = new LibroEnBiblioteca[this.CAPACIDAD_INICIAL];
        this.indiceGuardado = 0;
    }

    // --- Métodos de comportamiento (públicos) ---

    public void agregarLibro(LibroEnBiblioteca libroEnBiblioteca) {
        Validaciones.validarNotNull(libroEnBiblioteca, "El libro a guardar en la biblioteca debe ser no nulo");
        Validaciones.validarFalso(this.contieneLibro(libroEnBiblioteca), "El libro ya pertenece a la biblioteca");

        if (this.debeAumentarCapacidad()) {
            this.ajustarCapacidad(this.libros.length * 2);
        }

        Libro copiaDelLibro = libroEnBiblioteca.getLibro();
        this.libros[this.indiceGuardado++] = new LibroEnBiblioteca(copiaDelLibro, libroEnBiblioteca.getCopiasDisponibles());
    }

    public void eliminarLibro(LibroEnBiblioteca libroEnBiblioteca) {
        Validaciones.validarNotNull(libroEnBiblioteca, "El libro a eliminar en la biblioteca debe ser no nulo");
        Validaciones.validarFalso(!this.contieneLibro(libroEnBiblioteca),
                "El libro no pertenece a la biblioteca");
        int indiceDeLibroEliminado = this.indiceDeGuardado(libroEnBiblioteca);
        this.compactarLibrosGuardadosDesde(indiceDeLibroEliminado);
        if (this.debeReducirCapacidad()) {
            this.ajustarCapacidad(this.libros.length / 2);
        }
    }

    public void prestarLibro(LibroEnBiblioteca libroEnBiblioteca) {
        Validaciones.validarNotNull(libroEnBiblioteca, "El libro a prestar debe ser no nulo");
        LibroEnBiblioteca almacenado = this.buscarLibro(libroGuardado -> libroGuardado.getLibro().equals(libroEnBiblioteca.getLibro()));
        Validaciones.validarNotNull(almacenado, "El libro no pertenece a la biblioteca");
        almacenado.prestar();
    }

    public void devolverLibro(LibroEnBiblioteca libroEnBiblioteca) {
        Validaciones.validarNotNull(libroEnBiblioteca, "El libro a devolver debe ser no nulo");
        LibroEnBiblioteca almacenado = this.buscarLibro(libroGuardado -> libroGuardado.getLibro().equals(libroEnBiblioteca.getLibro()));
        Validaciones.validarNotNull(almacenado, "El libro no pertenece a la biblioteca");
        almacenado.devolver();
    }

    public LibroEnBiblioteca[] obtenerTodosLosLibros() {
        LibroEnBiblioteca[] copias = new LibroEnBiblioteca[this.indiceGuardado];
        for (int i = 0; i < this.indiceGuardado; i++) {
            LibroEnBiblioteca original = this.libros[i];
            copias[i] = new LibroEnBiblioteca(original.getLibro(), original.getCopiasDisponibles());
        }
        return copias;
    }


    public LibroEnBiblioteca buscarLibro(LibroEnBiblioteca libroBuscado) {
        Validaciones.validarNotNull(libroBuscado, "El libro buscado debe ser no nulo");
        Validaciones.validarFalso(!this.contieneLibro(libroBuscado), "El libro no forma parte de la biblioteca");

        LibroEnBiblioteca libroEnBiblioteca = this.buscarLibro(libro -> libro.getLibro().equals(libroBuscado.getLibro()));
        assert libroEnBiblioteca != null;
        return new LibroEnBiblioteca(libroEnBiblioteca.getLibro(), libroEnBiblioteca.getCopiasDisponibles());
    }


    // --- Helpers privados ---

    private boolean contieneLibro(LibroEnBiblioteca libroEnBiblioteca) {
        return buscarLibro(l -> l.getLibro().equals(libroEnBiblioteca.getLibro())) != null;
    }

    private LibroEnBiblioteca buscarLibro(Predicate<LibroEnBiblioteca> criterio) {
        for (int i = 0; i < this.indiceGuardado; i++) {
            if (criterio.test(this.libros[i])) {
                return this.libros[i];
            }
        }
        return null;
    }

    private int indiceDeGuardado(LibroEnBiblioteca libroEnBiblioteca) {
        for (int i = 0; i < this.indiceGuardado; i++) {
            if (this.libros[i].getLibro().equals(libroEnBiblioteca.getLibro())) {
                return i;
            }
        }
        throw new NoSuchElementException("El libro dado no existe en la biblioteca");
    }

    private void compactarLibrosGuardadosDesde(int desde) {
        for (int i = desde; i < this.indiceGuardado - 1; i++) {
            this.libros[i] = this.libros[i+1];
        }
        this.libros[--this.indiceGuardado] = null;
    }

    private void ajustarCapacidad(int nuevaCapacidad) {
        LibroEnBiblioteca[] nuevosLibros = new LibroEnBiblioteca[nuevaCapacidad];
        System.arraycopy(this.libros, 0, nuevosLibros, 0, Math.min(this.indiceGuardado, nuevaCapacidad));
        this.libros = nuevosLibros;
    }

    private boolean debeAumentarCapacidad() {
        return this.indiceGuardado == this.libros.length;
    }

    private boolean debeReducirCapacidad() {
        return this.libros.length > this.CAPACIDAD_INICIAL && this.cantidadDeLibrosGuardados() < (this.libros.length / 4);
    }

    private int cantidadDeLibrosGuardados() {
        return this.indiceGuardado;
    }

    // --- Getters y Setters ---


    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        Validaciones.validarNotNull(nombre, "El nombre de una biblioteca debe ser no nulo");
        Validaciones.validarNotBlank(nombre, "El nombre de una biblioteca debe ser no vacío");
        this.nombre = nombre;
    }
}
