package com.gdonati.backend_api.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdonati.backend_api.dto.PagamentoDTO;
import com.gdonati.backend_api.producer.PagamentoRequestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoFacade {
  @Autowired private PagamentoRequestProducer producer;

  public String solicitarPagamento(PagamentoDTO request) {
    try {
      producer.integrar(request);
    } catch (JsonProcessingException e) {
      return "Ocorreu um erro ao solicitar pagamento.." + e.getMessage();
    }
    return "Pagamento aguradando confirmação...";
  }

  public void erroPagamento(String payload) {
    System.err.println("==== RESPOSTA ERRO ====" + payload);
  }

  public void sucessoPagamento(String payload) {
    System.out.println("==== RESPOSTA SUCESSO ====" + payload);
  }
}
