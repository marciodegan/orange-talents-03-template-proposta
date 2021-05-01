package br.com.zupacademy.marciodegan.propostas.cadastrarproposta;

import br.com.zupacademy.marciodegan.propostas.compartilhado.Documento;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @Documento @NotBlank
    private String documento;
    @Email @NotBlank
    private String email;
    @NotBlank
    private String endereco;
    @Positive @NotNull
    private BigDecimal salario;

    public NovaPropostaRequest(@Documento @NotBlank String documento, @Email @NotBlank String email, @NotBlank String endereco, @Positive @NotNull BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel() {
        return new Proposta(this.documento, this.email, this.endereco, this.salario);
    }
}