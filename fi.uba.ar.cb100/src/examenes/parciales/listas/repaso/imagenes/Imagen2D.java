package examenes.parciales.listas.repaso.imagenes;

import validaciones.Validaciones;

public class Imagen2D {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private Pixel[][] pixeles;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa la imagen 2D con el ancho y el alto dados.
     * pre: Las medidas deben ser mayores a cero.
     *
     * @param ancho: el ancho de la imagen.
     * @param alto:  el alto de la imagen.
     */
    public Imagen2D(int ancho, int alto) {
        Validaciones.validarNumeroMayorACero(ancho, "ancho");
        Validaciones.validarNumeroMayorACero(alto, "alto");
        this.inicializarImagen(ancho, alto);
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Devuelve el pixel de coordenadas 'x, y'.
     *
     * @param x: coordenada x.
     * @param y: coordenada y.
     * @return el pixel cuyas coordenadas son las dadas.
     */
    public Pixel getPixel(int x, int y) {
        Validaciones.validarNumeroEntre(x, 1, pixeles.length, "x");
        Validaciones.validarNumeroEntre(y, 1, pixeles.length, "y");
        return this.pixeles[x - 1][y - 1];
    }

    /**
     * post: Devuelve el pixel más rojo de la imagen.
     *
     * @return el pixel más rojo.
     */
    public Pixel getPixelMasRojo() {
        return this.getPixelMaximo(Color.ROJO);
    }

    /**
     * post: Devuelve el pixel más verde de la imagen.
     *
     * @return el pixel más verde.
     */
    public Pixel getPixelMasVerde() {
        return this.getPixelMaximo(Color.VERDE);
    }

    /**
     * post: Devuelve el pixel más azul de la imagen.
     *
     * @return el pixel más azul.
     */
    public Pixel getPixelMasAzul() {
        return this.getPixelMaximo(Color.AZUL);
    }

    /**
     * post: Aplica un filtro de escala de grises sobre la imagen.
     */
    public void aplicarFiltroDeEscalaDeGrises() {
        for (Pixel[] pixel : this.pixeles) {
            for (int j = 0; j < this.pixeles[0].length; j++) {
                pixel[j].filtroDeEscalaDeGrises();
            }
        }
    }

    /**
     * post: Aplica un filtro de brillo sobre la imagen.
     *
     * @param x: delta de color.
     */
    public void aplicarFiltroDeBrillo(int x) {
        for (Pixel[] pixel : this.pixeles) {
            for (int j = 0; j < this.pixeles[0].length; j++) {
                pixel[j].filtroDeBrillo(x);
            }
        }
    }

    /**
     * post: Invierte la imagen horizontalmente.
     */
    public void invertirHorizontalmente() {
        // TODO: Implementación.
    }

    /**
     * post: Devuelve el ancho de la imagen.
     *
     * @return el ancho de la imagen.
     */
    public int getAncho() {
        return this.pixeles.length;
    }

    /**
     * post: Devuelve el alto de la imagen.
     *
     * @return el alto de la imagen.
     */
    public int getAlto() {
        return this.pixeles[0].length;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    /**
     * post: Inicializa la matriz de pixeles, que envuelve la imagen.
     *
     * @param ancho: el ancho de la matriz.
     * @param alto:  el alto de la matriz.
     */
    private void inicializarImagen(int ancho, int alto) {
        this.pixeles = new Pixel[ancho][alto];
        for (int i = 0; i < this.pixeles.length; i++) {
            for (int j = 0; j < this.pixeles[0].length; j++) {
                this.pixeles[i][j] = this.getPixelBlanco();
            }
        }
    }

    /**
     * post: Devuelve un pixel de color blanco.
     *
     * @return un pixel blanco.
     */
    private Pixel getPixelBlanco() {
        return new Pixel(255, 255, 255);
    }

    /**
     * post: Devuelve el pixel cuya componente de color 'color' es máxima.
     *
     * @param color: el color indicado.
     * @return el pixel de mayor color 'color'.
     */
    private Pixel getPixelMaximo(Color color) {
        Pixel pixelMaximo = null;
        for (Pixel[] pixel : this.pixeles) {
            for (int j = 0; j < this.pixeles[0].length; j++) {
                if ((pixelMaximo == null)
                        || (pixel[j].getComponente(color) > pixelMaximo.getComponente(color))) {
                    pixelMaximo = pixel[j];
                }
            }
        }
        return pixelMaximo;
    }
}