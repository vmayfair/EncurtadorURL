# EncurtadorURL

Para o correto funcionamento do sistema é necessário instalar o Redis e o Gradle;
Em seguida, abrir o prompt de comando e digitar "redis-server" para iniciar a instância Redis.
Será necessário entrar no diretório onde se encontra o projeto, digitar "gradle build" e aguardar a finalização do processo.
Feito isso, digitar "gradle run".
Uma vez executando, pode-se utilizar a ferramenta Postman para realização dos testes.
Abra o software e informe a URL http://localhost:8080/encurtador mudando para o método "POST" e altere o "body" para application/json.
Informe "url" : "endereço que deseja encurtar". Pressione o botão "Send" e o software retornará o link encurtado.
Por fim, pode-se utilizar o novo endereço no navegador e este será redirecionado para a URL anteriormente informada.
