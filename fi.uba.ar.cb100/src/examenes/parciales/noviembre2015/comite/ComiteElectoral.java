package examenes.parciales.noviembre2015.comite;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import validaciones.Validaciones;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComiteElectoral {

    /**
     * post: Devuelve una nueva lista con los números de mesa en las que los candidatos dados
     * (EN CONJUNTO) recibieron más votos que el total de los demás candidatos en dicha mesa.
     * Solo considera votos válidos (no recurridos) y que no son en blanco.
     */
    public List<Integer> buscarMesasEnLasQueFueronMuchaMasVotados(List<MesaElectoral> mesas,
                                                                  List<Candidato> candidatos) {
        Validaciones.validarNotNull(mesas, "mesas");
        Validaciones.validarNotNull(candidatos, "candidatos");
        List<Integer> numerosDeMesa = new ListaSimplementeEnlazada<>();
        List<Candidato> candidatosQueNoEstan = obtenerCandidatosQueNoEstanEnLaLista(candidatos);
        for (MesaElectoral mesaElectoral: mesas) {
            int totalVotosACandidatos = cantidadVotosDeCandidatosEnConjunto(mesaElectoral, candidatos);
            int totalOtrosVotos       = cantidadVotosDeCandidatosEnConjunto(mesaElectoral, candidatosQueNoEstan);
            if (totalVotosACandidatos > totalOtrosVotos) {
                numerosDeMesa.add(mesaElectoral.getNumero());
            }
        }
        return numerosDeMesa;
    }

    /**
     * post: Devuelve el total de votos de los candidatos en la lista indicada
     * en la mesa electoral dada.
     */
    public int cantidadVotosDeCandidatosEnConjunto(MesaElectoral mesaElectoral, List<Candidato> candidatos) {
        Validaciones.validarNotNull(mesaElectoral, "mesaElectoral");
        Validaciones.validarNotNull(candidatos, "candidatos");
        int totalVotos = 0;
        Set<Candidato> candidatosBuscados = new HashSet<>(candidatos);
        for (Voto voto: mesaElectoral.getVotos()) {
            if (esVotoAceptadoPorComite(mesaElectoral, voto)) {
                if (candidatosBuscados.contains(voto.candidatoVotado()))
                    totalVotos++;
            }
        }
        return totalVotos;
    }

    /**
     * post: Indica si el voto dado, de la mesa electoral indicada en válido para
     * el comite.
     */
    public boolean esVotoAceptadoPorComite(MesaElectoral mesaElectoral, Voto voto) {
        Validaciones.validarNotNull(mesaElectoral,"mesaElectoral");
        Validaciones.validarNotNull(voto, "voto");
        return mesaElectoral.esVotoValido(voto) && (!voto.esEnBlanco());
    }

    /**
     * post: Devuelve una lista con todos los candidatos del total de candidatos que NO
     * estén en la lista de candidatos indicada.
     */
    public List<Candidato> obtenerCandidatosQueNoEstanEnLaLista(List<Candidato> candidatos) {
        Validaciones.validarNotNull(candidatos,"candidatos");
        Set<Candidato> candidatosTotales = new HashSet<>(List.of(Candidato.values()));
        candidatosTotales.removeAll(new HashSet<>(candidatos));
        return candidatosTotales.stream().toList();
    }

}

