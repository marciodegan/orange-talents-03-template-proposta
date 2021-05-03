package br.com.zupacademy.marciodegan.propostas.compartilhado.exception;

import java.util.ArrayList;
import java.util.Collection;

public class ErroPadronizado {
    private Collection<String> mensagens = new ArrayList<>();

    public ErroPadronizado(Collection<String> mensagens) {
        this.mensagens = mensagens;
    }

    public Collection<String> getMensagens() {
        return mensagens;
    }
}