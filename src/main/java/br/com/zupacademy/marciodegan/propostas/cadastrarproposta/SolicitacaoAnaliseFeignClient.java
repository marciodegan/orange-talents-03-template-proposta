package br.com.zupacademy.marciodegan.propostas.cadastrarproposta;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "solicitacaoAnalise", url = "${solicitacaoAnalise.host}")
public interface SolicitacaoAnaliseFeignClient {

    @RequestMapping(method = RequestMethod.POST, value="api/solicitacao")
    AnaliseResponse analisaProposta(@RequestBody AnaliseRequest request);
}