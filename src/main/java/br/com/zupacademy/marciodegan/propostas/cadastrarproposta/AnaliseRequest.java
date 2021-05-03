package br.com.zupacademy.marciodegan.propostas.cadastrarproposta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnaliseRequest {

    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotNull
    private String idProposta;

    public AnaliseRequest(@Valid Proposta proposta){
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}