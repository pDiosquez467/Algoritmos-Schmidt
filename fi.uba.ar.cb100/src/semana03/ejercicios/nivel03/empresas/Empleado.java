package semana03.ejercicios.nivel03.empresas;

import semana03.ejercicios.utils.Validaciones;

import java.util.Objects;

/**
 * Representa un empleado de la empresa.
 * Contiene ID, nombre, salario.
 */
public class Empleado {
    private String id;
    private String nombre;
    private Double salario;

    /**
     * Constructor de la clase. Permite crear instancias de empleados.
     * @param id: Código de identificación único para cada empleado.
     * @param nombre: Nombre del empleado.
     * @param salario: Salario percibido por el empleado. Debe ser mayor a cero.
     */
    public Empleado(String id, String nombre, Double salario) {
        this.setId(id);
        this.setNombre(nombre);
        this.setSalario(salario);
    }

    /**
     * Sobre-escritura del metodo toString para imprimir la información de cada empleado.
     * @return una cadena de texto con los datos esenciales de cada empleado.
     */
    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", salario= $" + salario + '\'' +
                '}';
    }

    /**
     * Indica si el empleado que recibe el mensaje es igual al empleado dado por parámetro.
     * Dos empleados se consideran iguales si tienen el mismo ID.
     * @param o: Objecto que debe ser de la clase Empleado.
     * @return un booleano indicado si los empleados son iguales.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado empleado)) return false;
        return Objects.equals(this.id, empleado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    /**
     * Devuelve el ID del empleado.
     * @return el ID del empleado.
     */
    public String getId() {
        return id;
    }

    /**
     * Devuelve el nombre del empleado.
     * @return el nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el salario del empleado.
     * @return el salario del empleado.
     */
    public Double getSalario() {
        return salario;
    }

    /**
     * Permite modificar el ID del empleado.
     * @param id: Código de identificación único. Debe ser no vacío.
     */
    public void setId(String id) {
        Validaciones.validarNotBlank(id, "El identificador 'id' de un empleado no puede ser vacío.");
        this.id = id;
    }

    /**
     * Permite modificar el nombre del empleado.
     * @param nombre: Nombre del empleado. Debe ser no vacío.
     */
    public void setNombre(String nombre) {
        Validaciones.validarNotBlank(nombre, "El nombre de un empleado no debe ser vacío.");
        this.nombre = nombre;
    }

    /**
     * Permite modificar el ID del empleado.
     * @param salario: Salario percibido por el empleado. Debe ser mayor a cero.
     */
    public void setSalario(Double salario) {
        Validaciones.validarNumeroMayorACero(salario, "EL salario de un empleado debe ser mayor a cero.");
        this.salario = salario;
    }
}
