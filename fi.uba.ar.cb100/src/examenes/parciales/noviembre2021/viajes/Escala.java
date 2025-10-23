package examenes.parciales.noviembre2021.viajes;

public record Escala(String lugar, int duracion) {

    /**
     * post: Crea una escala en el lugar indicado, por los días de duración.
     *
     */
    public Escala {
    }

    /**
     * post: Devuelve el nombre del lugar.
     */
    @Override
    public String lugar() {
        return lugar;
    }

    /**
     * post: Devuelve la duración en días.
     */
    @Override
    public int duracion() {
        return duracion;
    }
}
