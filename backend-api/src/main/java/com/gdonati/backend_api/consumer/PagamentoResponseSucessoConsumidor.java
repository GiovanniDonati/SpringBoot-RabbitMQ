package com.gdonati.backend_api.consumer;

import com.gdonati.backend_api.facade.PagamentoFacade;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagamentoResponseSucessoConsumidor {
  @Autowired
  private PagamentoFacade pagamentoFacade;

  @RabbitListener(queues = {"pagamento-response-sucesso-queue"})
  public void receive(@Payload Message message){
    System.out.println("Message" + message + " " + LocalDateTime.now());
    String payload = String.valueOf(message.getPayload());

    pagamentoFacade.sucessoPagamento (payload);
  }
}
