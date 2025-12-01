package examenes.parciales.junio2015.catapulta;

import tdas.vector.Vector;
import tdas.vector.VectorImpl;
import validaciones.Validaciones;

import java.util.Objects;

/**
 * Representa un TDA Catapulta, que posee un contrapeso para lanzar múltiples proyectiles.
 * La Catapulta gestiona la carga de proyectiles, su lanzamiento y el registro de métricas de tiro.
 *
 * El límite de carga es definido por el peso del contrapeso.
 */
public class Catapulta {
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final double contraPeso;
    private final Vector<Proyectil> proyectilesCargados;
    private int cantidadDeProyectilesLanzados;
    private double distanciaDeTiro;
    private double distanciaMaximaDeTiro;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa una catapulta a partir del peso del contrapeso dado.
     * La catapulta comienza descargada y con 0 proyectiles lanzados.
     * pre: 'contraPeso' debe ser mayor a cero.
     * @param contraPeso: el peso (en kilogramos) del contrapeso.
     */
    public Catapulta(double contraPeso) {
        Validaciones.validarNumeroMayorACero(contraPeso, "contraPeso");
        this.contraPeso = contraPeso;
        this.proyectilesCargados = new VectorImpl<>();
        this.cantidadDeProyectilesLanzados = 0;
        this.distanciaDeTiro               = 0;
        this.distanciaMaximaDeTiro         = 0;
    }

    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Agrega un proyectil a la carga de la catapulta.
     * pre: El peso del proyectil no debe exceder el peso disponible.
     * @param proyectil: el proyectil a cargar.
     * @throws RuntimeException si se excede el contrapeso.
     */
    public void cargar(Proyectil proyectil) {
        Validaciones.validarNotNull(proyectil, "proyectil");
        this.validarCargaDeProyectil(proyectil);
        this.proyectilesCargados.add(proyectil);
    }

    /**
     * post: Remueve todos los proyectiles antes cargados, dejando la catapulta descargada.
     * pre: La catapulta debe estar cargada.
     * @throws RuntimeException si la catapulta ya está descargada.
     */
    public void descargar() {
        this.validarCatapultaCargada();
        this.proyectilesCargados.clear();
    }

    /**
     * post: Lanza todos los proyectiles cargados, quedando descargada.
     * El contador de proyectiles lanzados se actualiza y la distancia máxima de tiro se
     * actualiza si corresponde.
     * @return la distancia (en metros) a la que llegarán dichos proyectiles.
     * @throws RuntimeException si la catapulta está descargada o si la distancia de tiro no fue ajustada (es cero).
     */
    public double disparar() {
        this.validarCatapultaCargada();
        this.validarDistanciaDeTiro();
        this.cantidadDeProyectilesLanzados += this.proyectilesCargados.size();
        this.proyectilesCargados.clear();
        this.actualizarDistanciaMaximaDeTiro();
        return this.distanciaDeTiro;
    }

    /**
     * post: Ajusta la distancia (en metros) a la que se lanzarán los proyectiles en el próximo disparo.
     * pre: 'distanciaDeTiro' debe ser mayor a cero.
     * @param distanciaDeTiro: la nueva distancia de tiro.
     */
    public void ajustarDistanciaDeTiro(double distanciaDeTiro) {
        Validaciones.validarNumeroMayorACero(distanciaDeTiro, "distanciaDeTiro");
        this.distanciaDeTiro = distanciaDeTiro;
    }

    /**
     * post: Indica el peso (en kilogramos) disponible para lanzar un nuevo proyectil.
     * Es la diferencia entre el peso del contrapeso y el peso actual de la carga.
     * @return el peso restante que puede ser cargado.
     */
    public double pesoDisponible() {
        return this.contraPeso - this.pesoActual();
    }

    /**
     * post: Devuelve el peso total de todos los proyectiles cargados actualmente.
     * @return el peso actual de la carga.
     */
    public double pesoActual() {
        double pesoActual = 0;
        for (int i = 0; i < this.proyectilesCargados.size(); i++) {
            Proyectil proyectil = this.proyectilesCargados.get(i);
            pesoActual += proyectil.Peso();
        }
        return pesoActual;
    }

    /**
     * post: Indica si la catapulta está sin proyectiles cargados.
     * @return verdadero si no hay proyectiles cargados.
     */
    public boolean estaDescargada() {
        return this.proyectilesCargados.isEmpty();
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    /**
     * post: Devuelve la distancia máxima a la que lanzó un proyectil.
     * @return la distancia máxima de tiro registrada.
     */
    public double getDistanciaMaximaAlcanzada() {
        return this.distanciaMaximaDeTiro;
    }

    /**
     * post: Devuelve la cantidad total de proyectiles lanzados desde la creación de la catapulta.
     * @return la cantidad de proyectiles lanzados.
     */
    public int getCantidadDeProyectilesLanzados() {
        return cantidadDeProyectilesLanzados;
    }

    /**
     * post: Devuelve la distancia de tiro actual ajustada.
     * @return la distancia de tiro.
     */
    public double getDistanciaDeTiro() {
        return distanciaDeTiro;
    }

    /**
     * post: Devuelve el peso del contrapeso.
     * @return el contrapeso.
     */
    public double getContraPeso() {
        return contraPeso;
    }

    //MÉTODOS PRIVADOS -----------------------------------------------------------------------------------------

    private void validarCargaDeProyectil(Proyectil proyectil) {
        if (this.pesoActual() + proyectil.Peso() > this.contraPeso) {
            throw new RuntimeException("Contrapeso excedido");
        }
    }

    private void validarCatapultaCargada() {
        if (this.estaDescargada()) {
            throw new RuntimeException("Catapulta descargada");
        }
    }

    private void validarDistanciaDeTiro() {
        if (this.distanciaDeTiro == 0) {
            throw new RuntimeException("Ajustar distancia de tiro");
        }
    }

    private void actualizarDistanciaMaximaDeTiro() {
        if (this.distanciaDeTiro > this.distanciaMaximaDeTiro) {
            this.distanciaMaximaDeTiro = this.distanciaDeTiro;
        }
    }
}