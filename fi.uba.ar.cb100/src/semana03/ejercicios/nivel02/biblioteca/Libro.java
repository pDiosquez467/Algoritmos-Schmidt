package semana03.ejercicios.nivel02.biblioteca;

public class Libro {
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private int copiasDisponibles;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public Libro(String titulo, String autor, int anioPublicacion, int copiasDisponibles) throws Exception {
        this(titulo, autor);
        this.anioPublicacion = anioPublicacion;
        this.setCopiasDisponibles(copiasDisponibles);
    }

    public Boolean estaDisponible() {
        return this.copiasDisponibles > 0;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public void setCopiasDisponibles(int copiasDisponibles) throws Exception {
        if (copiasDisponibles < 0) {
            throw new Exception("El número de copias disponibles debe ser mayor o igual a cero.");
        }
        this.copiasDisponibles = copiasDisponibles;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Libro libro)) return false;
        return this.getTitulo().equals(libro.getTitulo()) && this.getAutor().equals(libro.getAutor());
    }
}
