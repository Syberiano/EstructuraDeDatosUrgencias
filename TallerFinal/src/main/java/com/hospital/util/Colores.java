package com.hospital.util;

/**
 * Clase utilitaria para agregar colores ANSI a la consola
 * Compatible con Windows 10+, Linux y macOS
 */
public class Colores {

    // Reset
    public static final String RESET = "\033[0m";

    // Colores de texto
    public static final String NEGRO = "\033[0;30m";
    public static final String ROJO = "\033[0;31m";
    public static final String VERDE = "\033[0;32m";
    public static final String AMARILLO = "\033[0;33m";
    public static final String AZUL = "\033[0;34m";
    public static final String MAGENTA = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String BLANCO = "\033[0;37m";

    // Colores brillantes
    public static final String ROJO_BRILLANTE = "\033[1;31m";
    public static final String VERDE_BRILLANTE = "\033[1;32m";
    public static final String AMARILLO_BRILLANTE = "\033[1;33m";
    public static final String AZUL_BRILLANTE = "\033[1;34m";
    public static final String MAGENTA_BRILLANTE = "\033[1;35m";
    public static final String CYAN_BRILLANTE = "\033[1;36m";
    public static final String BLANCO_BRILLANTE = "\033[1;37m";

    // Fondos
    public static final String FONDO_NEGRO = "\033[40m";
    public static final String FONDO_ROJO = "\033[41m";
    public static final String FONDO_VERDE = "\033[42m";
    public static final String FONDO_AMARILLO = "\033[43m";
    public static final String FONDO_AZUL = "\033[44m";
    public static final String FONDO_MAGENTA = "\033[45m";
    public static final String FONDO_CYAN = "\033[46m";
    public static final String FONDO_BLANCO = "\033[47m";

    // Estilos
    public static final String NEGRITA = "\033[1m";
    public static final String SUBRAYADO = "\033[4m";
    public static final String INVERSO = "\033[7m";

    /**
     * Imprime un texto en color
     */
    public static void imprimir(String texto, String color) {
        System.out.print(color + texto + RESET);
    }

    /**
     * Imprime una línea en color
     */
    public static void imprimirLinea(String texto, String color) {
        System.out.println(color + texto + RESET);
    }

    /**
     * Imprime un título con formato
     */
    public static void imprimirTitulo(String texto) {
        System.out.println(CYAN_BRILLANTE + NEGRITA + texto + RESET);
    }

    /**
     * Imprime un mensaje de éxito
     */
    public static void exito(String texto) {
        System.out.println(VERDE_BRILLANTE + texto + RESET);
    }

    /**
     * Imprime un mensaje de error
     */
    public static void error(String texto) {
        System.out.println(ROJO_BRILLANTE + texto + RESET);
    }

    /**
     * Imprime un mensaje de advertencia
     */
    public static void advertencia(String texto) {
        System.out.println(AMARILLO_BRILLANTE + texto + RESET);
    }

    /**
     * Imprime un mensaje de información
     */
    public static void info(String texto) {
        System.out.println(AZUL_BRILLANTE + texto + RESET);
    }

    /**
     * Imprime un separador visual
     */
    public static void separador() {
        System.out.println(CYAN + "-------------------------------------------------------" + RESET);
    }

    /**
     * Imprime un banner con el texto centrado
     */
    public static void banner(String texto) {
        separador();
        System.out.println(CYAN_BRILLANTE + NEGRITA + "  " + texto + RESET);
        separador();
    }

    /**
     * Limpia la pantalla (multiplataforma)
     */
    public static void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Si no se puede limpiar, solo imprime líneas en blanco
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
