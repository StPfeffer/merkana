package com.merkana.core.util;

import lombok.Getter;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Classe utilitária responsável por resolver mensagens e nomes de entidades
 * localizadas.
 * <p>
 * Esta classe oferece métodos estáticos para buscar mensagens e nomes de
 * entidades a partir de arquivos de propriedades localizados (resource bundles),
 * com base em uma chave e um {@link Locale} informado.
 * <p>
 * Os arquivos utilizados para resolução são:
 * <ul>
 *   <li><b>i18n/messages</b> – para mensagens gerais</li>
 *   <li><b>i18n/entities</b> – para nomes de entidades</li>
 *   <li><b>i18n/operations</b> – para nomes de operações</li>
 * </ul>
 * <p>
 * Esta classe não pode ser instanciada.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public final class MessageResolver {

    private MessageResolver() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Recupera uma mensagem localizada correspondente à chave de mensagem
     * fornecida e ao locale especificado.
     * <p>
     * Este método busca a mensagem em um resource bundle utilizando o locale
     * informado. Se a chave não existir no resource bundle, uma
     * {@link MissingResourceException} poderá ser lançada.
     *
     * @param key    a chave da mensagem desejada
     * @param locale o {@link Locale} no qual a mensagem deve ser recuperada
     * @return a mensagem localizada como uma {@link String}
     * @throws MissingResourceException se nenhum resource bundle com o nome base
     *                                  especificado for encontrado ou se a chave
     *                                  fornecida não estiver presente no bundle
     */
    public static String resolveMessage(String key, Locale locale) {
        return resolve(BundleType.MESSAGES, key, locale);
    }

    /**
     * Recupera o nome localizado de uma entidade com base na chave fornecida
     * e no locale especificado.
     * <p>
     * Este método busca a entidade em um resource bundle utilizando o locale
     * informado. Se a chave não existir no resource bundle, uma
     * {@link MissingResourceException} poderá ser lançada.
     *
     * @param key    a chave da entidade desejada
     * @param locale o {@link Locale} no qual o nome da entidade deve ser
     *               recuperado
     * @return o nome localizado da entidade como uma {@link String}
     * @throws MissingResourceException se nenhum resource bundle com o nome
     *                                  especificado for encontrado ou se a chave
     *                                  fornecida não estiver presente no bundle
     */
    public static String resolveEntity(String key, Locale locale) {
        return resolve(BundleType.ENTITIES, key, locale);
    }

    /**
     * Recupera o nome localizado de uma operação com base na chave fornecida
     * e no locale especificado.
     * <p>
     * Este método busca a operação em um resource bundle utilizando o locale
     * informado. Se a chave não existir no resource bundle, uma
     * {@link MissingResourceException} poderá ser lançada.
     *
     * @param key    a chave da operação desejada
     * @param locale o {@link Locale} no qual o nome da operação deve ser
     *               recuperado
     * @return o nome localizado da operação como uma {@link String}
     * @throws MissingResourceException se nenhum resource bundle com o nome
     *                                  especificado for encontrado ou se a chave
     *                                  fornecida não estiver presente no bundle
     */
    public static String resolveOperation(String key, Locale locale) {
        return resolve(BundleType.OPERATIONS, key, locale);
    }

    private static String resolve(BundleType bundleType, String key, Locale locale) {
        return ResourceBundle.getBundle(bundleType.getBundleName(), locale).getString(key);
    }

    @Getter
    private enum BundleType {

        MESSAGES("i18n/messages"),
        ENTITIES("i18n/entities"),
        OPERATIONS("i18n/operations");

        private final String bundleName;

        BundleType(String bundleName) {
            this.bundleName = bundleName;
        }

    }

}
