package examenes.parciales.listas.repaso.viajes;

public class Escala {

    private String lugar;
    private int duracion;

    /**
     * post: Crea una escala en el lugar indicado, por los días de duración
     * dados en 'duracion'.
     */
    public Escala(String lugar, int duracion) {
        this.lugar = lugar;
        this.duracion = duracion;
    }

    /**
     * post: Devuelve el nombre del lugar.
     * @return el nombre del lugar.
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * post: Devuelve la duración en días.
     * @return la duración en días.
     */
    public int getDuracion() {
        return duracion;
    }
}
