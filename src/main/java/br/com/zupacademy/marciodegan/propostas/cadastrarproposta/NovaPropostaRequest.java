package br.com.zupacademy.marciodegan.propostas.cadastrarproposta;

import br.com.zupacademy.marciodegan.propostas.compartilhado.Documento;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank
    private String nome;

    @NotBlank @Documento
    private String documento;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String endereco;

    @Positive @NotNull
    private BigDecimal salario;


    public NovaPropostaRequest(@NotBlank String nome, @NotBlank String documento, @Email @NotBlank String email, @NotBlank String endereco, @Positive @NotNull BigDecimal salario) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel() {
        return new Proposta(this.nome, this.documento, this.email, this.endereco, this.salario);
    }

    public String getDocumento() {
        return documento;
    }
}