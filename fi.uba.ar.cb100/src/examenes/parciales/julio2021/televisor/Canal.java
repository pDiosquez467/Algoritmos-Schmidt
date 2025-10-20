package examenes.parciales.julio2021.televisor;

import validaciones.Validaciones;

import java.util.Objects;

public class Canal {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private final int numeroDelCanal;
    private int ultimoVolumenRegistrado;
    private int maximoVolumenRegistrado;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa el canal con el número dado, y con el volumen en cero.
     * pre: El número del canal debe ser mayor a cero.
     * @param numeroDelCanal: número del canal.
     */
    public Canal(int numeroDelCanal) {
        Validaciones.validarNumeroMayorACero(numeroDelCanal, "numero");
        this.numeroDelCanal = numeroDelCanal;
        this.ultimoVolumenRegistrado = 0;
        this.maximoVolumenRegistrado = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Canal canal)) return false;
        return numeroDelCanal == canal.numeroDelCanal;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroDelCanal);
    }

    @Override
    public String toString() {
        return "Canal{" +
                "numero=" + numeroDelCanal +
                ", ultimoVolumenRegistrado=" + ultimoVolumenRegistrado +
                ", maximoVolumenRegistrado=" + maximoVolumenRegistrado +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Ajusta el volumen del canal al nuevo volumen dado.
     * @param nuevoVolumen: el nuevo volumen del canal.
     */
    public void ajustarVolumen(int nuevoVolumen) {
        Validaciones.validarNumeroMayorOIgualACero(nuevoVolumen, "nuevoVolumen");
        if (nuevoVolumen > this.maximoVolumenRegistrado) {
            this.maximoVolumenRegistrado = nuevoVolumen;
        }
        this.ultimoVolumenRegistrado = nuevoVolumen;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el número del canal.
     * @return el número del canal.
     */
    public int numero() {
        return numeroDelCanal;
    }

    /**
     * post: Devuelve el último volumen registrado (volumen actual del canal).
     * @return el último volumen registrado.
     */
    public int ultimoVolumenRegistrado() {
        return ultimoVolumenRegistrado;
    }

    /**
     * post: Devuelve el máximo volumen registrado del canal.
     * @return el máximo volumen registrado.
     */
    public int maximoVolumenRegistrado() {
        return maximoVolumenRegistrado;
    }

}
