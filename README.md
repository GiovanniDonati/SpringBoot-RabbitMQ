# Mensageria com RabbitMQ e SpringBoot

A mensageria serve para facilitar a comunicação entre serviços que não precisam de uma resposta imediata. Por exemplo, quando um usuário se cadastra, o e-mail de boas-vindas não precisa ser enviado na mesma hora. Ele pode ser processado depois, por um serviço à parte.

Em um sistema de mensageria, você tem um **Produtor** que cria e envia uma mensagem (tópico) e um **Consumidor** que monitora e lê as mensagens que lhe interessam.

A **Fila (Queue)** é onde as mensagens ficam guardadas até o consumidor estar pronto para processá-las.

| Produtor           | Fila           | Consumidor                    |
| :----------------- | :------------- | :---------------------------- |
| `Serviço de Login` | `enviar_email` | `Serviço de Envio de E-mails` |

### **Como Funciona na Prática?**

1. O **Produtor** (seu serviço de login, por exemplo) gera uma mensagem para a fila `enviar_email`.

2. A mensagem fica na **Fila** esperando.

3. O **Consumidor** (seu serviço de e-mail) monitora a fila e, quando a mensagem chega, ele a pega e executa a tarefa (enviar o e-mail).

O **RabbitMQ** atua como o intermediário (o broker) que gerencia as filas e as mensagens.

---

# _Exemplo de uso:_

## Sistema de Pagamento

> ### **BACKEND-API**
>
> Rota para encaminhar requisições de pagamentos
> | Endpoint | Payload | Funcionalidade |
> | :--------| :--------------| :-------|
> |`localhost:8080/product/`| `{ numeroPedido: 'str', valor: int, produto: 'str'} `| cadastrar pagamento|
>
> - **_Produtor_** que encaminha o tópico `pagamento-request-exchange`, `pagamento-request-rout-key` e o pagamento.
> - Monitorando os tópicos `pagamento-response-erro-queue` e `pagamento-response-sucesso-queue` como **_Consumidor_**.

> ### **BACKEND-WORKER**
>
> Rota para processar pagamentos.
>
> - **_Consumidor_** que monitora o tópico `pagamento-request-exchange`.
> - Retornando os tópicos `pagamento-response-erro-queue`, `pagamento-response-sucesso-queue` de acordo com o processamento (atualmente, com um Random, apenas para fins de aprendizado), como **_Produtor_**.
