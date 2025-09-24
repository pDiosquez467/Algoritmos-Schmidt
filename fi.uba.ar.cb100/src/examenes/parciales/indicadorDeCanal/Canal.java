package examenes.parciales.indicadorDeCanal;

public class Canal {

    private final int numero;
    private EstadoDeCanal estadoDeCanal;

    public Canal(int numero) {
        this.numero = numero;
        this.estadoDeCanal = EstadoDeCanal.ACTIVO;
    }

    public int getNumero() {
        return numero;
    }

    public EstadoDeCanal getEstadoDeCanal() {
        return estadoDeCanal;
    }

    public void setEstadoDeCanal(EstadoDeCanal estadoDeCanal) {
        this.estadoDeCanal = estadoDeCanal;
    }

    public boolean estaActivo() {
        return estadoDeCanal == EstadoDeCanal.ACTIVO;
    }
}
