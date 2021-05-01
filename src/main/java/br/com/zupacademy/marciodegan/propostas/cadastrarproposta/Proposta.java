package br.com.zupacademy.marciodegan.propostas.cadastrarproposta;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;

import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(nullable = false)
    private String documento;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private BigDecimal salario;

    public Proposta(String documento, String email, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getId() {
        return id;
    }
}