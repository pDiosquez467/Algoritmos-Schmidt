package semana03.ejercicios.nivel02.cursos;

import semana03.ejercicios.nivel02.cursos.excepciones.CursoException;
import semana03.ejercicios.utils.Validaciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Curso {
    private static final int CUPO_MAXIMO = 35;

    private String codigo;
    private String nombre;
    private List<Estudiante> estudiantes;

    public Curso(String codigo, String nombre) {
        Validaciones.validarNotNull(codigo, "El código no debe ser null");
        Validaciones.validarNotBlank(codigo, "El código no debe ser vacío");
        Validaciones.validarNotNull(nombre, "El nombre del curso no debe ser null");
        Validaciones.validarNotBlank(nombre, "El nombre del curso no debe ser vacío");

        this.codigo = codigo;
        this.nombre = nombre;
        this.estudiantes = new ArrayList<>();
    }

    public boolean estaInscripto(Estudiante estudiante) {
        return this.estudiantes.stream()
                .anyMatch(e -> e.equals(estudiante));
    }

    public boolean estaLleno() {
        return this.estudiantes.size() == CUPO_MAXIMO;
    }

    public int cuposDisponibles() {
        return CUPO_MAXIMO - this.estudiantes.size();
    }

    public List<Estudiante> estudiantes() {
        return Collections.unmodifiableList(this.estudiantes);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Curso curso)) return false;
        return Objects.equals(codigo, curso.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    // --- Helpers package-private

    void agregarEstudiante(Estudiante estudiante) {
        if (!this.estaInscripto(estudiante)) {
            this.estudiantes.add(estudiante);
        }
    }

    void eliminarEstudiante(Estudiante estudiante) {
        this.estudiantes.remove(estudiante);
    }
}
