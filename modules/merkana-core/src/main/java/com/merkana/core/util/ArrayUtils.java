package com.merkana.core.util;

import java.lang.reflect.Array;

public final class ArrayUtils {

    private ArrayUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Verifica se um array de objetos está vazio ou é {@code null}.
     *
     * @param array o array a ser testado
     * @return {@code true} se o array estiver vazio ou for {@code null}
     */
    public static boolean isEmpty(Object[] array) {
        return isArrayEmpty(array);
    }

    /**
     * Verifica se um array está vazio ou é {@code null}.
     *
     * @param array o array a ser testado
     * @return {@code true} se o array estiver vazio ou for {@code null}
     */
    private static boolean isArrayEmpty(Object array) {
        return getLength(array) == 0;
    }

    /**
     * Retorna o tamanho do array especificado. Este método pode lidar com
     * arrays de {@link Object} e com arrays primitivos.
     * <p>
     * Se o array de entrada for {@code null}, {@code 0} será retornado.
     *
     * <pre>
     * ArrayUtils.getLength(null)            = 0
     * ArrayUtils.getLength([])              = 0
     * ArrayUtils.getLength([null])          = 1
     * ArrayUtils.getLength([true, false])   = 2
     * ArrayUtils.getLength([1, 2, 3])       = 3
     * ArrayUtils.getLength(["a", "b", "c"]) = 3
     * </pre>
     *
     * @param array o array do qual se deseja obter o tamanho, pode ser {@code
     *              null}
     * @return O tamanho do array, ou {@code 0} se o array for {@code null}
     * @throws IllegalArgumentException se o argumento fornecido não for um
     *                                  array.
     */
    public static int getLength(Object array) {
        return array != null ? Array.getLength(array) : 0;
    }

}

