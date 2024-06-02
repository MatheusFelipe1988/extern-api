# extern-api
API externa onde usa o feign client pra consumir de forma eficiente uma api de forma paralela, e a api utilizada para esta aplicação foi uma loja de roupas que possui um sistema que cadastra roupas para uma loja. Banco de dados MySQL que realiza toda as operações CRUD. práticas de organização de forma eficiente e de fácil leitura.
&nbsp;

Explorando o framework Swagger com a sua integração para documentar a aplicação de maneira muito clara e concisa, com isso sendo capaz de construir uma documentação bem detalhada, facilitando a leitura e compreensão para desenvolvedores verificarem o comportamento de toda a API caso tudo esteja como os conformes estabelecidos.
&nbsp;

### Recursos Adicionais
Tratamento de exceções implementado para personalizar de forma eficiente erros na hora de injetar,alterar,buscar e deletar dados. Implementado testes unitários com mock e junit para verificar se todo o codigo construído esta funcionando como esperado.

Utilizando o RabbitMQ para um sistema de mensageria que faz o consumo e produção de mensagens se comunicando na mesma API assim aprimorando a distribuição e escalabilidade do projeto usando o protocolo "Advanced Message Queuing Protocol" o AMQP, desta forma ficando mais claro na hora de checar eventos de eventuais bugs ou falhas.

Implementado um sistema de email que faz o envio de emails usando a programação orientada a Aspectos ou POA. Utilizado protocolo Simple Mail Transfer Protocol o SMTP, dessa forma os envios podem ser realizados com o google mail bem mais eficiente e rápido na hora de entregar o email após um novo dado criado, juto com as anotações personalizadas do springboot 3 proporcionando um codigo limpo e fluído.
&nbsp;

## Stacks
- Java 17
- Springboot 3.1.2
- MySQL 8.0
- Swagger 2.0.6
- RabbitMQ
- Junit
- Mockito
