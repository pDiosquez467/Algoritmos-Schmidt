package examenes.parciales.julio2010.concursos;

import validaciones.Validaciones;

public class Participante {
    private final String nombre;
    private int puntos;

    /**
     * post: Inicializa el participante con el nombre indicado y 0 puntos.
     * @param nombre: el nombre del participante.
     */
    public Participante(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    /**
     * post: Devuelve el nombre del participante.
     * @return el nombre del participante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * post: Devuelve la cantidad de puntos del participante.
     * @return la cantidad de puntos del participante.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * post: Cambia la cantidad de puntos del participante.
     * @param puntos: la nueva cantidad de puntos del participante.
     */
    public void setPuntos(int puntos) {
        Validaciones.validarNumeroMayorACero(puntos, "puntos");
        this.puntos = puntos;
    }
}
