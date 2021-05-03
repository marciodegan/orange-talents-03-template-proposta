package br.com.zupacademy.marciodegan.propostas.cadastrarproposta;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String documento;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private PropostaStatus propostaStatus;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String nome, String documento, String email, String endereco, BigDecimal salario) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public PropostaStatus getPropostaStatus() {
        return propostaStatus;
    }

    public String getNome() {
        return nome;
    }

    public void aceitaProposta(String resultadoAnalise) {
        this.propostaStatus = resultadoAnalise.equals("SEM_RESTRICAO") ? propostaStatus.ELEGIVEL : propostaStatus.NAO_ELEGIVEL;
    }


}