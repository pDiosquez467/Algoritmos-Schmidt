package examenes.parciales.julio2021.televisor;

import validaciones.Validaciones;

public class Televisor {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Canal[] canales;
    private EstadoDelTelevisor estadoDelTelevisor;
    private int volumenActual;
    private int indiceDelCanalActual;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa el televisor con la cantidad de canales disponibles dada,
     * con un número máximo de canales permitidos dado y volumen en cero.
     * @param cantidadDeCanalesDisponibles: cantidad de canales disponibles.
     * @param cantidadMaximaDeCanalesPermitidos: maxima cantidad de canales permitidos.
     */
    public Televisor(int cantidadDeCanalesDisponibles, int cantidadMaximaDeCanalesPermitidos) {
        Validaciones.validarNumeroMayorA(cantidadMaximaDeCanalesPermitidos,
                1,
                "cantidadMaximaDeCanalesPermitidos");
        Validaciones.validarNumeroEntre(cantidadDeCanalesDisponibles,
                1,
                cantidadMaximaDeCanalesPermitidos,
                "cantidadDeCanalesDisponibles");
        this.inicializarCanales(cantidadDeCanalesDisponibles);
        this.estadoDelTelevisor   = EstadoDelTelevisor.APAGADO;
        this.volumenActual        = 0;
        this.indiceDelCanalActual = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Televisor{" +
                "estadoDelTelevisor=" + estadoDelTelevisor +
                ", volumenActual=" + volumenActual +
                '}';
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Enciende el televisor con un encendido normal (con volumen).
     */
    public void encender() {
        this.estadoDelTelevisor = EstadoDelTelevisor.ENCENDIDO_NORMAL;
    }

    /**
     * post: Apaga el televisor.
     */
    public void apagar() {
        this.estadoDelTelevisor = EstadoDelTelevisor.APAGADO;
    }

    /**
     * post: Indica si el televisor está encendido.
     * @return verdadero si el televisor está encendido.
     */
    public boolean estaEncendido() {
        return this.estadoDelTelevisor.equals(EstadoDelTelevisor.ENCENDIDO_NORMAL);
    }

    /**
     * post: Indica si el televisor está muteado.
     * @return verdadero si el televisor está muteado.
     */
    public boolean estaMuteada() {
        return this.estadoDelTelevisor.equals(EstadoDelTelevisor.ENCENDIDO_MUTEADO);
    }

    /**
     * post: Mutea el televisor.
     * pre: El televisor debe estar encendido.
     */
    public void mutear() {
        this.validarEstadoEncendido();
        this.estadoDelTelevisor = EstadoDelTelevisor.ENCENDIDO_MUTEADO;
    }

    /**
     * post: Vuelve al televisor a su estado de encendido normal.
     */
    public void desmutear() {
        this.estadoDelTelevisor = EstadoDelTelevisor.ENCENDIDO_NORMAL;
    }

    /**
     * post: Sube el volumen actual del televisor.
     * pre: El televisor debe estar encendido y sin mutear.
     */
    public void subirVolumen() {
        this.validarEstadoEncendido();
        this.validarEstadoDeEncendidoNormal();
        this.volumenActual = Math.min(100, this.volumenActual + 1);
        this.canales[indiceDelCanalActual].ajustarVolumen(this.volumenActual);
    }

    /**
     * post: Baja el volumen actual del televisor.
     * pre: El televisor debe estar encendido y sin mutear.
     */
    public void bajarVolumen() {
        this.validarEstadoEncendido();
        this.validarEstadoDeEncendidoNormal();
        this.volumenActual = Math.max(0, this.volumenActual - 1);
        this.canales[indiceDelCanalActual].ajustarVolumen(this.volumenActual);
    }

    /**
     * post: Pasa al siguiente canal. En caso de llegar el límite final, empieza de nuevo.
     */
    public void getSiguienteCanal() {
        this.validarEstadoEncendido();
        this.indiceDelCanalActual = (this.indiceDelCanalActual + 1) % this.canales.length;
        this.canales[indiceDelCanalActual].ajustarVolumen(this.volumenActual);
    }

    /**
     * post: Pasa al canal anterior. En caso de llegar a 0, empieza desde el final.
     */
    public void getAnteriorCanal() {
        this.validarEstadoEncendido();
        int nuevoIndice = this.indiceDelCanalActual - 1;
        if (nuevoIndice < 0) {
            this.indiceDelCanalActual = this.canales.length - 1;
        } else {
            this.indiceDelCanalActual = nuevoIndice;
        }

        this.canales[this.indiceDelCanalActual].ajustarVolumen(this.volumenActual);
    }

    /**
     * post: Selecciona el canal cuyo número es el dado.
     * pre: El número del canal debe ser el de un canal válido (en el rango adecuado)
     * @param numeroDelCanal: número del canal seleccionado.
     */
    public void getCanal(int numeroDelCanal) {
        Validaciones.validarNumeroEntre(numeroDelCanal,
                1,
                this.canales.length,
                "numeroDelCanal");
        this.validarEstadoEncendido();
        this.indiceDelCanalActual = numeroDelCanal - 1;
        this.canales[indiceDelCanalActual].ajustarVolumen(this.volumenActual);
    }

    /**
     * post: Devuelve el último volumen registrado del canal cuyo número es el dado.
     * pre: El número del canal debe ser el de un canal válido (en el rango adecuado)
     * @param numeroDelCanal: número del canal dado.
     * @return el último volumen registrado del canal dado.
     */
    public int ultimoVolumenRegistrado(int numeroDelCanal) {
        Validaciones.validarNumeroEntre(numeroDelCanal,
                1,
                this.canales.length,
                "numeroDelCanal");
        return this.canales[numeroDelCanal-1].ultimoVolumenRegistrado();
    }

    /**
     * post: Devuelve el máximo volumen registrado del canal cuyo número es el dado.
     * pre: El número del canal debe ser el de un canal válido (en el rango adecuado)
     * @param numeroDelCanal: número del canal dado.
     * @return el último volumen registrado del canal dado.
     */
    public int maximoVolumenRegistrado(int numeroDelCanal) {
        Validaciones.validarNumeroEntre(numeroDelCanal,
                1,
                this.canales.length,
                "numeroDelCanal");
        return this.canales[numeroDelCanal-1].maximoVolumenRegistrado();
    }

    /**
     * post: Devuelve el canal con el máximo volumen registrado.
     * @return el canal con el máximo volumen registrado.
     */
    public Canal getCanalConMaximoVolumenRegistrado() {
        Canal maximoCanal = null;
        for (Canal canal: this.canales) {
            if ((maximoCanal == null) || (canal.maximoVolumenRegistrado() > maximoCanal.maximoVolumenRegistrado())) {
                maximoCanal = canal;
            }
        }
        return maximoCanal;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el estado actual del televisor (APAGADO, NORMAL, MUTEADO).
     * @return el estado del televisor.
     */
    public EstadoDelTelevisor estadoDelTelevisor() {
        return estadoDelTelevisor;
    }

    /**
     * post: Devuelve el volumen actual del televisor.
     * @return el volumen actual del televisor.
     */
    public int volumenActual() {
        return volumenActual;
    }

    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    /**
     * post: Inicializa los canales disponibles.
     * @param cantidadDeCanalesDisponibles: cantidad de canales disponibles.
     */
    private void inicializarCanales(int cantidadDeCanalesDisponibles) {
        this.canales = new Canal[cantidadDeCanalesDisponibles];
        for (int i = 0; i < this.canales.length; i++) {
            this.canales[i] = new Canal(i+1);
        }
    }

    /**
     * post: Valida el estado encendido del televisor.
     */
    private void validarEstadoEncendido() {
        if (!this.estaEncendido()) {
            throw new RuntimeException("Televisor APAGADO");
        }
    }

    /**
     * post: Valida el estado de encendido normal del televisor.
     */
    private void validarEstadoDeEncendidoNormal() {
        if (this.estaMuteada()) {
            throw new RuntimeException("Televisor muteado");
        }
    }
}
