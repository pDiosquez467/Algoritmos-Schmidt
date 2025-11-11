package examenes.parciales.listas.repaso.editor;

import validaciones.Validaciones;

public class Comentario {

    private final String contenido;
    private int calificacion;

    /**
     * post: Inicializa el comentario con el contenido dado y calificación 0.
     * @param contenido: el contenido del comentario.
     */
    public Comentario(String contenido) {
        Validaciones.validarNotNull(contenido, "contenido");
        this.contenido = contenido;
        this.calificacion = 0;
    }

    /**
     * post: Indica si el comentario está calificado.
     * @return verdadero si el comentario está calificado.
     */
    public boolean estaCalificado() {
        return this.calificacion > 0;
    }

    /**
     * post: Devuelve el contenido del comentario.
     * @return el contenido del comentario.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * post: Devuelve la calificación del comentario.
     * @return la calificación del comentario.
     */
    public int getCalificacion() {
        return calificacion;
    }

    /**
     * post: Modifica la calificación del comentario.
     * pre: 'calificacion' debe estar en el intervalo [1, 10].
     * @param calificacion: la calificación del comentario.
     */
    public void calificar(int calificacion) {
        Validaciones.validarNumeroEntre(calificacion, 1, 10, "calificacion");
        this.calificacion = calificacion;
    }
}
