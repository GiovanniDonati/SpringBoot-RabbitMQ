package com.gdonati.backend_worker.consumer;

import com.gdonati.backend_worker.producer.PagamentoErroProdutor;
import com.gdonati.backend_worker.producer.PagamentoSucessoProdutor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PagamentoRequestConsumidor {
  @Autowired private PagamentoErroProdutor erroProdutor;
  @Autowired private PagamentoSucessoProdutor sucessoProdutor;


  @RabbitListener(queues = {"pagamento-request-queue"})
  public void receberMessage (@Payload Message message){
    System.out.println(message);
    if (new Random().nextBoolean()){
      sucessoProdutor.gerarResposta("Mensagem de sucesso pagamento" + message);
    } else {
      erroProdutor.gerarResposta("Mensagem de erro pagamento" + message);
    }
  }
}
