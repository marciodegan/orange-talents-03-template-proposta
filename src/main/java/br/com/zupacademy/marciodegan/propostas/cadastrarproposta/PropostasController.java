package br.com.zupacademy.marciodegan.propostas.cadastrarproposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class PropostasController {

    private final PropostaRepository propostaRepository;

    @Autowired
    public PropostasController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping(value = "/propostas")
    public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request,
                                          UriComponentsBuilder builder) {
        Optional<Proposta> propostaBuscada = propostaRepository.findByDocumento(request.getDocumento());
        if (propostaBuscada.isEmpty()) {
            Proposta propostaSalva = propostaRepository.save(request.toModel());
            URI enderecoConsulta = builder.path("/propostas/{id}").build(propostaSalva.getId());
            return ResponseEntity.created(enderecoConsulta).build();
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("já existe proposta para este cpf/cnpj"); // talvez só um .build();
    }
}