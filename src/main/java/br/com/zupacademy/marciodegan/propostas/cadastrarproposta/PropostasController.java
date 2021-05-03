package br.com.zupacademy.marciodegan.propostas.cadastrarproposta;

import br.com.zupacademy.marciodegan.propostas.compartilhado.exception.FeignExceptionError;
import feign.FeignException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

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
    private final SolicitacaoAnaliseFeignClient solicitacaoAnaliseFeignClient;
    private final Logger log = LoggerFactory.getLogger(PropostasController.class);

    public PropostasController(PropostaRepository propostaRepository, SolicitacaoAnaliseFeignClient solicitacaoAnaliseFeignClient) {
        this.propostaRepository = propostaRepository;
        this.solicitacaoAnaliseFeignClient = solicitacaoAnaliseFeignClient;
    }

    @PostMapping(value = "/propostas")
    public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request,
                                          UriComponentsBuilder builder) {
        Optional<Proposta> propostaBuscada = propostaRepository.findByDocumento(request.getDocumento());
        if (propostaBuscada.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("já existe proposta para este cpf/cnpj");
        }

        Proposta novaProposta = propostaRepository.save(request.toModel());
        resultadoAnalise(novaProposta);
        log.info("Resposta Solicitação Análise: " + novaProposta.getPropostaStatus() + "! ID da Proposta: " + novaProposta.getId());

        URI enderecoConsulta = builder.path("/propostas/{id}").build(novaProposta.getId());
        return ResponseEntity.created(enderecoConsulta).build();
    }

    public void resultadoAnalise(Proposta proposta) {
        AnaliseRequest analiseRequest = new AnaliseRequest(proposta);
        try {
            AnaliseResponse analiseResponse = solicitacaoAnaliseFeignClient.analisaProposta(analiseRequest);
            proposta.aceitaProposta(analiseResponse.getResultadoSolicitacao());
            propostaRepository.save(proposta);
        } catch (FeignException e) {
            proposta.aceitaProposta("COM_RESTRICAO");
            propostaRepository.save(proposta);
            log.warn("Resposta Solicitação Análise: " + proposta.getPropostaStatus() + "! ID da Proposta: " + proposta.getId());
            throw new FeignExceptionError(HttpStatus.UNPROCESSABLE_ENTITY, "NÃO ELEGÍVEL. Proposta salva como não elegível");
        }
    }
}