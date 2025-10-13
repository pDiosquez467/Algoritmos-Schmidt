package examenes.parciales.mayo2025.alumnos;

import validaciones.Validaciones;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Profesor {

    /**
     * post: Devuelve una lista con los mejores alumnos de cada materia
     * según su promedio de notas.
     * @param alumnos: lista de alumnos.
     * @param promedios: lista de promedios.
     * @return una nueva lista con los mejores alumnos.
     */
    public List<Alumno> buscarMejoresAlumnos(List<Alumno> alumnos,
                                             List<Nota> promedios) {
        Validaciones.validarNotNull(alumnos, "'alumnos'");
        Validaciones.validarNotNull(promedios, "'promedios'");

        Set<Nota> mejoresNotas = this.obtenerMejoresNotasPorMateria(promedios);
        return this.obtenerAlumnosViaNotas(alumnos, mejoresNotas).stream()
                .toList();
    }

    /**
     * post: Devuelve un conjunto de las mejores notas por materia.
     * @param notas: colección de notas.
     * @return las mejores notas por materia.
     */
    public Set<Nota> obtenerMejoresNotasPorMateria(Collection<Nota> notas) {
        Validaciones.validarNotNull(notas, "'notas'");

        Set<Nota> mejoresNotas = new HashSet<>();
        for (Nota nota: notas) {
            if (nota == null) continue;

            Nota notaGuardada = this.obtenerNotaViaMateria(mejoresNotas, nota.materia());
            if (notaGuardada == null || nota.valor() > notaGuardada.valor()) {

                if (notaGuardada != null) {
                    mejoresNotas.remove(notaGuardada);
                }
                mejoresNotas.add(nota);
            }
        }
        return mejoresNotas;
    }

    /**
     * post: A partir de la colección de alumnos, devuelve la colección de todos los alumnos (sin repetir) que
     * están en la colección de notas dada.
     * @param alumnos: colección de alumnos.
     * @param notas: colección de notas.
     * @return colección de alumnos que estén las dos colecciones dadas.
     */
    public Collection<Alumno> obtenerAlumnosViaNotas(Collection<Alumno> alumnos, Collection<Nota> notas) {
        Set<Alumno> res = new HashSet<>();
        for (Nota nota: notas) {
            Alumno alumno = this.obtenerAlumnoViaPadron(alumnos, nota.padron());
            res.add(alumno);
        }
        return res;
    }

    /**
     * post: Devuelve el alumno cuyo padron coincide con el dado.
     * En caso de no encontrarlo, devuelve null.
     * @param alumnos: colección de alumnos.
     * @param padron: padrón de un alumno.
     * @return el alumno con el padron dado.
     */
    public Alumno obtenerAlumnoViaPadron(Collection<Alumno> alumnos, int padron) {
        return alumnos.stream()
                .filter(alumno -> alumno.padron() == padron)
                .findFirst()
                .orElse(null);
    }

    /**
     * post: Devuelve la nota cuya materia coincide con la materia dada.
     * En caso de no encontrarlo, devuelve null.
     * @param notas: colección de notas.
     * @param materia: materia buscada.
     * @return la nota con la materia dada.
     */
    public Nota obtenerNotaViaMateria(Collection<Nota> notas, String materia) {
        return notas.stream()
                .filter(nota -> nota.materia().equals(materia))
                .findFirst()
                .orElse(null);
    }
}