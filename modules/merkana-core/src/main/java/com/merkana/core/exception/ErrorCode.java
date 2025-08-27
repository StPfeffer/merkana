package com.merkana.core.exception;

/**
 * Interface que representa um código de erro padronizado na aplicação.
 * As implementações dessa interface devem fornecer uma chave de erro única,
 * uma chave de mensagem para localização e um código de status HTTP associado.
 * <p>
 * Esta interface é destinada à definição de diversos tipos de erro que podem
 * ocorrer dentro da aplicação, permitindo um tratamento de erros consistente
 * e com suporte à localização.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public interface ErrorCode {

    /**
     * Recupera o código associado a este erro.
     *
     * @return uma {@link String} representando o código de erro.
     */
    String getCode();

    /**
     * Recupera a chave para uma mensagem no resource bundle da aplicação.
     * <p>
     * A chave da mensagem é utilizada para buscar uma mensagem de erro
     * localizada, facilitando o suporte a múltiplos idiomas e regiões. Essa
     * chave deve estar presente no resource bundle utilizado pela aplicação.
     *
     * @return uma {@link String} representando a chave da mensagem para
     * localização.
     */
    String getMessageKey();

    /**
     * Recupera o código de status HTTP associado a este erro.
     * <p>
     * Esse código de status indica o status da resposta HTTP que deve ser
     * retornado ao cliente quando esse erro ocorrer, permitindo uma comunicação
     * apropriada das condições de erro nas respostas da API.
     *
     * @return um {@code int} representando o código de status HTTP.
     */
    int getHttpStatus();

}

