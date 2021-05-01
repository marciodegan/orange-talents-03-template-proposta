package br.com.zupacademy.marciodegan.propostas.cadastrarproposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, String>{

    Optional<Proposta> findByDocumento(String documento);

}