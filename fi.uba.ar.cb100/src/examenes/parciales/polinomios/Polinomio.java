package examenes.parciales.polinomios;

import tdas.vector.Iterador;
import tdas.vector.Vector;

public class Polinomio {

    private final Vector<Termino> terminos;
    private int grado;
    private double coeficientePrincipal;

    public Polinomio() {
        this.terminos = new Vector<>();
        Termino unidad = new Termino(0, 1);
        terminos.agregar(unidad);

        this.grado = unidad.getGrado();
        this.coeficientePrincipal = unidad.getCoeficiente();
    }

    public double evaluar(double x) throws Exception {
        double resultado = 0.0;
        Iterador<Termino> it = terminos.iterador();
        while (it.haySiguiente()) {
            Termino termino = it.verActual();
            resultado += termino.getCoeficiente() * Math.pow(x, termino.getGrado());
            it.siguiente();
        }
        return resultado;
    }

    public void agregarTermino(Termino nuevo) throws Exception {
        Iterador<Termino> it = terminos.iterador();
        while (it.haySiguiente()) {
            Termino termino = it.verActual();
            if (termino.getGrado() == nuevo.getGrado()) {
                double nuevoCoeficiente = termino.getCoeficiente() + nuevo.getCoeficiente();
                termino.setCoeficiente(nuevoCoeficiente);

                if (nuevo.getGrado() == grado) {
                    coeficientePrincipal = nuevoCoeficiente;
                }

                return;
            }
            it.siguiente();
        }

        if (nuevo.getGrado() > grado) {
            grado = nuevo.getGrado();;
            coeficientePrincipal = nuevo.getCoeficiente();
        }
        terminos.agregar(nuevo);
    }

    public void removerTermino(Termino termino) {
        int indice = terminos.indiceDe(termino);
        if (indice == -1) {
            throw new RuntimeException("El término no existe en el polinomio");
        }
        terminos.remover(indice);
    }

    public double obtenerValor(int gradoDelTermino) throws Exception {
        Iterador<Termino> it = terminos.iterador();
        while (it.haySiguiente()) {
            Termino termino = it.verActual();
            if (termino.getGrado() == gradoDelTermino) {
                return termino.getCoeficiente();
            }
            it.siguiente();
        }
        throw new RuntimeException("No hay término de grado " + gradoDelTermino);
    }

    public void cambiarValor(int gradoDelTermino, double valor) throws Exception {
        Iterador<Termino> it = terminos.iterador();
        while (it.haySiguiente()) {
            Termino termino = it.verActual();
            if (termino.getGrado() == gradoDelTermino) {
                termino.setCoeficiente(valor);
                if (gradoDelTermino == grado) {
                    coeficientePrincipal = valor;
                }
                return;
            }
            it.siguiente();
        }
        throw new RuntimeException("No hay término de grado " + gradoDelTermino);
    }


    public int getGrado() {
        return grado;
    }

    public double getCoeficientePrincipal() {
        return coeficientePrincipal;
    }
}
