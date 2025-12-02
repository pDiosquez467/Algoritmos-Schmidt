package examenes.parciales.noviembre2015.comite;

public record Voto(Candidato candidatoVotado) {

    /**
     * post: Voto para el candidato indicado.
     */
    public Voto {
    }

    @Override
    public Candidato candidatoVotado() {
        if (this.esEnBlanco()) {
            throw new RuntimeException("Voto en BLANCO");
        }
        return candidatoVotado;
    }

    public boolean esEnBlanco() {
        return this.candidatoVotado == null;
    }
}
