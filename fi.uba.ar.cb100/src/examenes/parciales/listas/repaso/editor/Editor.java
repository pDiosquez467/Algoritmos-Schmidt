package examenes.parciales.listas.repaso.editor;

import validaciones.Validaciones;

import java.util.List;

public class Editor {

    /**
     * post: Selecciona de 'imagenesDisponibles' aquella que tenga por lo menos tantos comentarios
     * como los indicados y el promedio de calificación sea máximo. Ignora los comentarios sin calificación.
     */
    public Imagen seleccionarImagen(List<Imagen> imagenesDisponibles, int cantidadDeComentarios) {
        Validaciones.validarNotNull(imagenesDisponibles, "imágenesDisponibles");
        Validaciones.validarNumeroMayorACero(cantidadDeComentarios, "cantidadDeComentarios");

        Imagen imagenConPromedioMaximo = null;
        double promedioMaximoAlMomento = -1;
        for (Imagen imagen: imagenesDisponibles) {
            if (imagen == null) continue;
            if (imagen.getComentarios().size() >= cantidadDeComentarios) {
                double promedioActual = getPromedioDeImagen(imagen);
                if ((imagenConPromedioMaximo == null) ||
                        (promedioActual > promedioMaximoAlMomento)) {
                    imagenConPromedioMaximo = imagen;
                    promedioMaximoAlMomento = promedioActual;
                }
            }
        }
        return imagenConPromedioMaximo;
    }

    /**
     * post: Devuelve el promedio de los comentarios calificados de la imagen
     * dada. Si no tiene comentarios calificados, devuelve 0.
     */
    public double getPromedioDeImagen(Imagen imagen) {
        Validaciones.validarNotNull(imagen, "imagen");
        double valor = 0;
        int total    = 0;
        for (Comentario comentario: imagen.getComentarios()) {
            if (comentario.estaCalificado()) {
                valor += comentario.getCalificacion();
                total++;
            }
        }
        return (total > 0) ? valor / total : 0;
    }
}
