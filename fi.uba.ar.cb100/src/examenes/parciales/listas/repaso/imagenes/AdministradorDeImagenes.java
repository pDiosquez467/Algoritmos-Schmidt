package examenes.parciales.listas.repaso.imagenes;

import java.util.List;

import validaciones.Validaciones;

public class AdministradorDeImagenes {

    /**
     * post: Busca la imagen con más pixeles puros del listado de imágenes dado.
     * Un pixel es 'puro' si al menos dos de sus tres componentes RGB son iguales a cero.
     */
    public Imagen2D getImagenMasPura(List<Imagen2D> imagenes) {
        Validaciones.validarNotNull(imagenes, "imágenes");
        Imagen2D imagenMasPuraActual     = null;
        int cantidadDePixelesPurosMaxima = -1;
        for (Imagen2D imagen: imagenes) {
            if (imagen == null) continue;
            int cantidadDePixelesPurosActual = getCantidadDePixelesPuros(imagen);
            if ((imagenMasPuraActual == null) ||
                    (cantidadDePixelesPurosActual > cantidadDePixelesPurosMaxima)) {
                imagenMasPuraActual = imagen;
                cantidadDePixelesPurosMaxima = cantidadDePixelesPurosActual;
            }
        }
        return imagenMasPuraActual;
    }

    /**
     * post: Devuelve la cantidad de pixeles puros de la imagen dada.
     */
    public int getCantidadDePixelesPuros(Imagen2D imagen) {
        Validaciones.validarNotNull(imagen, "imagen");
        int totalPixelesPuros = 0;
        for (int i = 1; i <= imagen.getAncho(); i++) {
            for (int j = 1; j <= imagen.getAlto(); j++) {
                Pixel pixel = imagen.getPixel(i, j);
                if (esPixelPuro(pixel)) totalPixelesPuros++;
            }
        }
        return totalPixelesPuros;
    }

    /**
     * post: Indica si el pixel dado es puro.
     */
    public boolean esPixelPuro(Pixel pixel) {
        Validaciones.validarNotNull(pixel, "pixel");
        int totalComponentesPuras = 0;
        if (pixel.getRojo() == 0) totalComponentesPuras++;
        if (pixel.getVerde() == 0) totalComponentesPuras++;
        if (pixel.getAzul() == 0) totalComponentesPuras++;
        return totalComponentesPuras >= 2;
    }
}
