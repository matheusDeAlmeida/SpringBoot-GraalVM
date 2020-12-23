Este repositório contém um projeto em Spring Boot, com MySQL e alguns scripts de teste para ser executado pelo JMeter (todos os arquivos com a extenção .jmx na raiz do repositório são scripts do Apache JMeter).

Para executar este projeto, é necessário ter instalado:

-- Java

-- MySQL 8

-- GraalVM e GraalVM Native (caso queira gerar imagem nativa)

É recomendável instalar o Java e o GraalVM via https://sdkman.io/ (um gerenciador de versões de diversos SDKs, inclusive Java. Funciona bem em MacOS e em Linux).

O banco de dados MySQL deve estar configurado de acordo com o arquivo:

-- src/main/resources/application.properties

-- Se necessário, altere o usuário e a senha de acordo com os usuários de sua máquina

-- Deve existir um banco chamado "mydatabase" (ou altere-o na linha 2 do arquivo application.properties -- localhost:3306/mydatabase[...] -- para corresponder a um banco já existente)

Como gerar imagem nativa junto junto o jar:

./compile.sh jpa com.example.jpa.JpaApplication 0.0.1-SNAPSHOT

Como gerar só o jar:
mvn clean package

Como gerar o jar e executar a aplicação em seguida:

-- mvn spring-boot:run

-- ou acesse a pasta "target" e abra a aplicação nativa ou a aplicação .jar com o comando "java -jar <nome da aplicação>"


Versões recomendadas:
graalvm 20.2, Java 8 ou 11
