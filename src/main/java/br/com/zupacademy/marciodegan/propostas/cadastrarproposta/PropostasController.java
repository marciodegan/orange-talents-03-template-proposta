package br.com.zupacademy.marciodegan.propostas.cadastrarproposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostasController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value="/propostas")
    @Transactional
    public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request,
                                          UriComponentsBuilder builder){
        Proposta proposta = request.toModel();
        manager.persist(proposta);
        URI enderecoConsulta = builder.path("/propostas/{id}").build(proposta.getId());

        return ResponseEntity.created(enderecoConsulta).build();
    }
}