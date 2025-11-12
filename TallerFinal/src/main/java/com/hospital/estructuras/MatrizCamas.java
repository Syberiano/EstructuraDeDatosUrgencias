package com.hospital.estructuras;

/**
 * Implementación de una Matriz para representar las camas del hospital
 * Filas = Pisos o áreas
 * Columnas = Camas por piso
 * Estados: 0 = Libre, 1 = Ocupada, 2 = Mantenimiento
 */
public class MatrizCamas {

    private int[][] matriz;
    private int pisos;
    private int camasPorPiso;

    // Constantes para estados de cama
    public static final int LIBRE = 0;
    public static final int OCUPADA = 1;
    public static final int MANTENIMIENTO = 2;

    /**
     * Constructor que inicializa la matriz de camas
     */
    public MatrizCamas(int pisos, int camasPorPiso) {
        this.pisos = pisos;
        this.camasPorPiso = camasPorPiso;
        this.matriz = new int[pisos][camasPorPiso];

        // Inicializar todas las camas como libres
        for (int i = 0; i < pisos; i++) {
            for (int j = 0; j < camasPorPiso; j++) {
                matriz[i][j] = LIBRE;
            }
        }
    }

    /**
     * Asigna una cama (la marca como ocupada)
     */
    public boolean asignarCama(int piso, int cama) {
        if (esValido(piso, cama) && matriz[piso][cama] == LIBRE) {
            matriz[piso][cama] = OCUPADA;
            return true;
        }
        return false;
    }

    /**
     * Libera una cama
     */
    public boolean liberarCama(int piso, int cama) {
        if (esValido(piso, cama)) {
            matriz[piso][cama] = LIBRE;
            return true;
        }
        return false;
    }

    /**
     * Pone una cama en mantenimiento
     */
    public boolean ponerEnMantenimiento(int piso, int cama) {
        if (esValido(piso, cama) && matriz[piso][cama] == LIBRE) {
            matriz[piso][cama] = MANTENIMIENTO;
            return true;
        }
        return false;
    }

    /**
     * Busca la primera cama libre disponible
     * Retorna un array [piso, cama] o null si no hay disponibles
     */
    public int[] buscarCamaLibre() {
        for (int i = 0; i < pisos; i++) {
            for (int j = 0; j < camasPorPiso; j++) {
                if (matriz[i][j] == LIBRE) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * Obtiene el estado de una cama
     */
    public int getEstadoCama(int piso, int cama) {
        if (esValido(piso, cama)) {
            return matriz[piso][cama];
        }
        return -1;
    }

    /**
     * Cuenta las camas libres
     */
    public int contarCamasLibres() {
        int contador = 0;
        for (int i = 0; i < pisos; i++) {
            for (int j = 0; j < camasPorPiso; j++) {
                if (matriz[i][j] == LIBRE) {
                    contador++;
                }
            }
        }
        return contador;
    }

    /**
     * Cuenta las camas ocupadas
     */
    public int contarCamasOcupadas() {
        int contador = 0;
        for (int i = 0; i < pisos; i++) {
            for (int j = 0; j < camasPorPiso; j++) {
                if (matriz[i][j] == OCUPADA) {
                    contador++;
                }
            }
        }
        return contador;
    }

    /**
     * Cuenta las camas en mantenimiento
     */
    public int contarCamasMantenimiento() {
        int contador = 0;
        for (int i = 0; i < pisos; i++) {
            for (int j = 0; j < camasPorPiso; j++) {
                if (matriz[i][j] == MANTENIMIENTO) {
                    contador++;
                }
            }
        }
        return contador;
    }

    /**
     * Valida si una posición es válida en la matriz
     */
    private boolean esValido(int piso, int cama) {
        return piso >= 0 && piso < pisos && cama >= 0 && cama < camasPorPiso;
    }

    /**
     * Obtiene el número de pisos
     */
    public int getPisos() {
        return pisos;
    }

    /**
     * Obtiene el número de camas por piso
     */
    public int getCamasPorPiso() {
        return camasPorPiso;
    }

    /**
     * Muestra el estado completo de la matriz de camas
     */
    public void mostrar() {
        System.out.println("\n  Estado de las camas del hospital:");
        System.out.println("  (L = Libre, O = Ocupada, M = Mantenimiento)\n");

        // Encabezado de camas
        System.out.print("      ");
        for (int j = 0; j < camasPorPiso; j++) {
            System.out.print(String.format("C%-3d ", j + 1));
        }
        System.out.println();

        // Contenido de la matriz
        for (int i = 0; i < pisos; i++) {
            System.out.print(String.format("  P%d | ", i + 1));
            for (int j = 0; j < camasPorPiso; j++) {
                String estado;
                switch (matriz[i][j]) {
                    case LIBRE:
                        estado = "L";
                        break;
                    case OCUPADA:
                        estado = "O";
                        break;
                    case MANTENIMIENTO:
                        estado = "M";
                        break;
                    default:
                        estado = "?";
                }
                System.out.print(String.format(" %-3s ", estado));
            }
            System.out.println();
        }

        System.out.println("\n  Resumen:");
        System.out.println("  - Camas libres: " + contarCamasLibres());
        System.out.println("  - Camas ocupadas: " + contarCamasOcupadas());
        System.out.println("  - Camas en mantenimiento: " + contarCamasMantenimiento());
    }
}
