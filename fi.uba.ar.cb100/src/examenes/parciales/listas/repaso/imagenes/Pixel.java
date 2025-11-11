package examenes.parciales.listas.repaso.imagenes;

import validaciones.Validaciones;

import java.util.Objects;

public class Pixel {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private int rojo;
    private int verde;
    private int azul;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa un pixel con las componentes dadas.
     * pre: Las componentes deben estar en el rango [0, 255].
     * @param rojo: componente de color rojo.
     * @param verde: componente de color verde.
     * @param azul: componente de color azul.
     */
    public Pixel(int rojo, int verde, int azul) {
        this.setRojo(rojo);
        this.setAzul(azul);
        this.setVerde(verde);
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pixel pixel)) return false;
        return rojo == pixel.rojo && verde == pixel.verde && azul == pixel.azul;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rojo, verde, azul);
    }

    @Override
    public String toString() {
        return "Pixel{" +
                "rojo=" + rojo +
                ", verde=" + verde +
                ", azul=" + azul +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Aplica un filtro de escala de grises al pixel.
     */
    public void filtroDeEscalaDeGrises() {
        int filtro = (int)(0.299 * this.rojo + 0.587 * this.verde + 0.114 * azul);
        this.setRojo(filtro);
        this.setAzul(filtro);
        this.setVerde(filtro);
    }

    /**
     * post: Aplica un filtro de brillo al pixel. Modifica cada componente en
     * 'x' unidades.
     * @param x: delta de color.
     */
    public void filtroDeBrillo(int x) {
        this.setRojo(Math.min(255, Math.max(0, this.rojo + x)));
        this.setVerde(Math.min(255, Math.max(0, this.verde + x)));
        this.setAzul(Math.min(255, Math.max(0, this.azul + x)));
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve la componente de color rojo.
     * @return el color rojo.
     */
    public int getRojo() {
        return rojo;
    }

    /**
     * post: Devuelve la componente de color verde.
     * @return el color verde.
     */
    public int getVerde() {
        return verde;
    }

    /**
     * post: Devuelve la componente de color azul.
     * @return el color azul.
     */
    public int getAzul() {
        return azul;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Modifica el color rojo del pixel.
     * pre: el valor dado debe estar en el intervalo [0, 255].
     * @param rojo: la componente de color rojo.
     */
    public void setRojo(int rojo) {
        Validaciones.validarNumeroEntre(rojo, 0, 255, "rojo");
        this.rojo = rojo;
    }

    /**
     * post: Modifica el color verde del pixel.
     * pre: el valor dado debe estar en el intervalo [0, 255].
     * @param verde: la componente de color verde.
     */
    public void setVerde(int verde) {
        Validaciones.validarNumeroEntre(verde, 0, 255, "verde");
        this.verde = verde;
    }

    /**
     * post: Modifica el color azul del pixel.
     * pre: el valor dado debe estar en el intervalo [0, 255].
     * @param azul: la componente de color azul.
     */
    public void setAzul(int azul) {
        Validaciones.validarNumeroEntre(azul, 0, 255, "azul");
        this.azul = azul;
    }

    /**
     * post: Devuelve el valor de la componente dado el color.
     * @param color: el color indicado.
     * @return la componente del color indicado.
     */
    public int getComponente(Color color) {
        if (color.equals(Color.ROJO)) return this.rojo;
        if (color.equals(Color.VERDE)) return this.verde;
        return this.azul;
    }
}
