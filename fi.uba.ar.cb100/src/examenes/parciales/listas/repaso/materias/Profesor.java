package examenes.parciales.listas.repaso.materias;

import java.util.HashSet;
import validaciones.Validaciones;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Profesor {

    /**
     * post: Busca los mejores alumnos de cada materia según su promedio de notas.
     */
    public List<Alumno> buscarMejoresAlumnos(List<Alumno> alumnos, List<Nota> promedios) {
        Validaciones.validarNotNull(alumnos, "alumnos");
        Validaciones.validarNotNull(promedios,"promedios");
        Set<Nota> mejoresNotas = new HashSet<>();
        for (Nota promedio: promedios) {
            if ((promedio == null) || (promedio.materia() == null)) continue;
            String materia = promedio.materia();
            Nota promedioGuardado = obtenerNotaSegunMateria(mejoresNotas, materia);
            if (promedioGuardado == null) {
                mejoresNotas.add(promedio);
            } else {
                if (promedio.valor() > promedioGuardado.valor()) {
                    mejoresNotas.remove(promedioGuardado);
                    mejoresNotas.add(promedio);
                }
            }
        }
        return obtenerAlumnosViaNotas(alumnos, mejoresNotas);
    }

    /**
     * post: Devuelve una lista de todos los alumnos que están en 'alumnos' según la colección
     * de notas dadas.
     */
    public List<Alumno> obtenerAlumnosViaNotas(Collection<Alumno> alumnos, Collection<Nota> notas) {
        Validaciones.validarNotNull(alumnos, "alumnos");
        Validaciones.validarNotNull(notas, "notas");
        Set<Alumno> alumnosFiltrados = new HashSet<>();
        for (Nota nota: notas) {
            if (nota == null) continue;
            Alumno alumno = obtenerAlumnoViaPadron(alumnos, nota.padron());
            if (alumno == null) continue;
            alumnosFiltrados.add(alumno);
        }
        return alumnosFiltrados.stream().toList();
    }

    /**
     * post: Devuelve el alumno de la colección de alumnos dada, según el padrón
     * indicado. En caso de no encontrarlo, devuelve null.
     */
    public Alumno obtenerAlumnoViaPadron(Collection<Alumno> alumnos, int padron) {
        Validaciones.validarNotNull(alumnos, "alumnos");
        Validaciones.validarNumeroMayorACero(padron, "padron");
        for (Alumno alumno: alumnos) {
            if (alumno == null) continue;
            if (alumno.padron() == padron) {
                return alumno;
            }
        }
        return null;
    }

    /**
     * post: Devuelve la nota de la colección de notas dada, según la materia indicada.
     * En caso de no encontrarlo, devuelve null.
     */
    public Nota obtenerNotaSegunMateria(Collection<Nota> notas, String materia) {
        Validaciones.validarNotNull(notas, "notas");
        Validaciones.validarNotNull(materia, "materia");
        Validaciones.validarNotBlank(materia, "materia");
        for (Nota nota: notas) {
            if ((nota == null) || (nota.materia() == null)) continue;
            if (nota.materia().equals(materia)) return nota;
        }
        return null;
    }
}
