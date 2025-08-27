package com.merkana.core.util;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * A classe {@link StringUtils} define métodos relacionados à manipulação de
 * Strings.
 * <p>
 * A {@link StringUtils} trata Strings de entrada {@code null} silenciosamente.
 * Ou seja, um valor informado {@code null} retornará {@code null}. Nos casos onde
 * é retornado um {@code boolean} ou {@code int}, os retornos variam conforme
 * o método.
 * <p>
 * Um efeito colateral do tratamento de {@code null} é que uma {@link
 * NullPointerException} deve ser considerada um bug no {@link StringUtils}.
 *
 * @author Mateus Pfeffer
 * @see String
 * @since 1.0
 */
public final class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Verifica se a {@code String} fornecida realmente contém <em>texto</em>.
     * <p>
     * Mais especificamente, este método retorna {@code true} se a {@code
     * String} não for {@code null}, seu comprimento for maior que 0 e ela
     * contiver pelo menos um caractere que não seja espaço em branco.
     *
     * @param str a {@code String} a ser verificada (pode ser {@code null})
     * @return {@code true} se a {@code String} não for {@code null}, seu
     * comprimento for maior que 0 e não contiver apenas espaços em branco
     * @see Character#isWhitespace
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty();
    }

    /**
     * Obtém a mensagem usando {@link String#format(String, Object...)
     * String.format(mensagem, valores)} se os valores não estiverem vazios,
     * caso contrário retorna a mensagem sem formatação.
     * <p>
     * Este método existe para permitir que métodos de validação declarando
     * uma mensagem String e parâmetros varargs possam ser usados sem nenhum
     * parâmetro de mensagem quando a mensagem contém caracteres especiais.
     *
     * @param message a mensagem de exceção no formato {@link
     *                String#format(String, Object...)},se inválida, não pode
     *                ser {@code null}
     * @param args    os valores opcionais para a mensagem formatada
     * @return mensagem formatada usando {@link String#format(String, Object...)
     * String.format(mensagem, valores)} se os valores não estiverem vazios,
     * caso contrário retorna a mensagem sem formatação
     * @throws IllegalArgumentException se a mensagem for {@code null} ou vazia
     */
    public static String getMessageFormat(String message, Object... args) {
        if (isEmpty(message)) {
            throw new IllegalArgumentException("message cannot be null or empty");
        }

        if (args == null || args.length == 0) {
            return message;
        }

        Object[] processedArgs = new Object[args.length];

        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) {
                processedArgs[i] = "null";
            } else if (arg instanceof Collection<?>) {
                processedArgs[i] = ((Collection<?>) arg).stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(","));
            } else if (arg.getClass().isArray()) {
                int length = Array.getLength(arg);
                Collection<String> elements = new ArrayList<>(length);
                for (int j = 0; j < length; j++) {
                    Object element = Array.get(arg, j);
                    elements.add(element == null ? "null" : element.toString());
                }
                processedArgs[i] = String.join(",", elements);
            } else {
                processedArgs[i] = arg;
            }
        }

        return MessageFormat.format(message, processedArgs);
    }

    public static String defaultIfEmpty(String str, String defaultValue) {
        return isEmpty(str) ? defaultValue : str;
    }

    public static String collectionToCommaDelimitedString(@Nullable Collection<?> coll) {
        return collectionToDelimitedString(coll, ",");
    }

    public static String collectionToDelimitedString(@Nullable Collection<?> coll, String delim) {
        return collectionToDelimitedString(coll, delim, "", "");
    }

    public static String collectionToDelimitedString(@Nullable Collection<?> coll, String delim, String prefix,
                                                     String suffix) {

        if (CollectionUtils.isEmpty(coll)) {
            return "";
        }

        int totalLength = coll.size() * (prefix.length() + suffix.length()) + (coll.size() - 1) * delim.length();

        for (Object element : coll) {
            totalLength += String.valueOf(element).length();
        }

        StringBuilder sb = new StringBuilder(totalLength);
        Iterator<?> it = coll.iterator();

        while (it.hasNext()) {
            sb.append(prefix).append(it.next()).append(suffix);
            if (it.hasNext()) {
                sb.append(delim);
            }
        }

        return sb.toString();
    }

}
