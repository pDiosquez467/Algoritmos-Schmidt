package examenes.parciales.listas.repaso.atuendos;

public class Comprador {

    private String nombre;
    private double presupuesto;

    @Override
    public String toString() {
        return "Comprador{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    /**
     * post: Devuelve el nombre del comprador.
     * @return el nombre del comprador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * post: Devuelve el presupuesto del comprador.
     * @return el presupuesto del comprador.
     */
    public double getPresupuesto() {
        return presupuesto;
    }
}
