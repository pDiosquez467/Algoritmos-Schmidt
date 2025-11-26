package examenes.parciales.octubre2023.canales;

import validaciones.Validaciones;

public class IndicadorDeCanal {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final Canal[] canales;
    private int  posicionCanalActual;
    private int posicionCanalPrevio;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public IndicadorDeCanal(Canal canalMinimo, Canal canalMaximo) {
        Validaciones.validarNotNull(canalMinimo, "canalMinimo");
        Validaciones.validarNotNull(canalMaximo, "canalMaximo");
        Validaciones.validarNumeroMayorA(canalMaximo.getNumero(), canalMinimo.getNumero(), "Número de los canales");
        int totalCanales = canalMaximo.getNumero() - canalMinimo.getNumero() + 1;
        this.canales = new Canal[totalCanales];
        for (int i = canalMinimo.getNumero(); i <= canalMaximo.getNumero(); i++) {
            this.canales[i - canalMinimo.getNumero()] = new Canal(i);
        }
        this.posicionCanalActual = 0;
        this.posicionCanalPrevio = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    public void seleccionar(Canal canal) {
        Validaciones.validarNotNull(canal, "canal");
        int posicionDelCanal = this.buscarPosicionDeCanal(canal);
        if (posicionDelCanal == -1) {
            throw new RuntimeException("Canal inválido");
        }
        this.posicionCanalPrevio = this.posicionCanalActual;
        this.posicionCanalActual = posicionDelCanal;
    }

    public void avanzar() {
        this.posicionCanalPrevio = this.posicionCanalActual;
        this.posicionCanalActual = (this.posicionCanalActual + 1) % this.canales.length;
    }

    public void retroceder() {
        this.posicionCanalPrevio = this.posicionCanalActual;
        if (--this.posicionCanalActual == -1) {
            this.posicionCanalActual = this.canales.length - 1;
        }
    }

    public void volver() {
        int aux = this.posicionCanalActual;
        this.posicionCanalActual = this.posicionCanalPrevio;
        this.posicionCanalPrevio = aux;
    }

    public int obtener() {
        Canal canalActual = this.canales[posicionCanalActual];
        return canalActual.getNumero();
    }

    public int contar() {
        int totalActivos = 0;
        for (Canal canal: this.canales) {
            if (canal.estaActivo()) {
                totalActivos++;
            }
        }
        return totalActivos;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //MÉTODOS PRIVADOS-----------------------------------------------------------------------------------------

    private int buscarPosicionDeCanal(Canal canal) {
        for (int i = 0; i < this.canales.length; i++) {
            if (this.canales[i].equals(canal)) {
                return i;
            }
        }
        return -1;
    }
}
