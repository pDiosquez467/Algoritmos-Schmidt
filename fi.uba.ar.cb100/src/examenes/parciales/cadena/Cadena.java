package examenes.parciales.cadena;

import semana03.ejercicios.utils.Validaciones;

public class Cadena {
    private static final int CAPACIDAD_INICIAL = 10;

    private Eslabon[] eslabones;
    private final Eslabon eslabonInicial;
    private int indiceDelProximoEslabon;

    public Cadena(Eslabon eslabon) {
        Validaciones.validarNotNull(eslabon, "El eslabón inicial debe ser no nulo");
        this.eslabones = new Eslabon[CAPACIDAD_INICIAL];
        this.eslabonInicial = new Eslabon(eslabon.ancho(), eslabon.largo());
        this.indiceDelProximoEslabon = 1;
    }

    // --- Métodos de comportamiento (públicos) ---

    public void agregarEslabon(Eslabon eslabon) {
        Validaciones.validarNotNull(eslabon, "El eslabón a agregar debe ser no nulo");
        Validaciones.validarVerdadero(this.esEslabonAdecuado(eslabon), "El eslabón no es adecuado para agregar a la cadena");

        if (this.debeAumentarCapacidad()) {
            this.ajustarCapacidad(this.eslabones.length * 2);
        }

        this.eslabones[this.indiceDelProximoEslabon++] = new Eslabon(eslabon.ancho(), eslabon.largo());
    }

    public void retirarEslabon() {
        Validaciones.validarVerdadero(!this.estaVacia(), "La cadena está vacía");
        this.eslabones[--this.indiceDelProximoEslabon] = null;

        if (this.debeReducirCapacidad()) {
            this.ajustarCapacidad(this.eslabones.length / 2);
        }
    }

    public double largoTotal() {
        double largo = 0.0;
        for (int i = 0; i < this.indiceDelProximoEslabon; i++) {
            largo += this.eslabones[i].largo();
        }
        return largo;
    }

    public int cantidadDeEslabones() {
        return this.indiceDelProximoEslabon;
    }

    public Eslabon eslabonInicial() {
        Validaciones.validarVerdadero(!this.estaVacia(), "La cadena está vacía");
        return new Eslabon(this.eslabonInicial.ancho(), this.eslabonInicial.largo());
    }

    // --- Helpers privados ---

    private boolean esEslabonAdecuado(Eslabon eslabon) {
        double eps = 1e-9;
        return Math.abs(this.eslabonInicial.ancho() - eslabon.ancho()) < eps;
    }

    private boolean debeAumentarCapacidad() {
        return this.indiceDelProximoEslabon == this.eslabones.length;
    }

    private boolean debeReducirCapacidad() {
        return this.eslabones.length > CAPACIDAD_INICIAL && this.indiceDelProximoEslabon < (this.eslabones.length / 4);
    }

    private void ajustarCapacidad(int nuevaCapacidad) {
        Eslabon[] nuevos = new Eslabon[nuevaCapacidad];
        System.arraycopy(this.eslabones, 0, nuevos, 0, Math.min(this.indiceDelProximoEslabon, nuevaCapacidad));
        this.eslabones = nuevos;
    }

    private boolean estaVacia() {
        return indiceDelProximoEslabon == 0;
    }

}
