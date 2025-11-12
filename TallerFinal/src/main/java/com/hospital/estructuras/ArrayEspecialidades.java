package com.hospital.estructuras;

/**
 * Implementación de Array para almacenar especialidades médicas y niveles de triage
 * Utiliza un array estático para demostrar el uso de esta estructura
 */
public class ArrayEspecialidades {

    // Array de especialidades médicas
    private String[] especialidades;

    // Array de niveles de triage (1-5)
    private String[] nivelesTriage;

    public ArrayEspecialidades() {
        // Inicialización del array de especialidades
        this.especialidades = new String[]{
            "Urgencias",
            "Pediatría",
            "Cirugía",
            "Medicina Interna",
            "Cardiología",
            "Neurología",
            "Traumatología",
            "Ginecología"
        };

        // Inicialización del array de niveles de triage
        this.nivelesTriage = new String[]{
            "Nivel 1 - Resucitación (Crítico)",
            "Nivel 2 - Emergencia (Muy urgente)",
            "Nivel 3 - Urgente",
            "Nivel 4 - Menos urgente",
            "Nivel 5 - No urgente"
        };
    }

    /**
     * Obtiene todas las especialidades
     */
    public String[] getEspecialidades() {
        return especialidades;
    }

    /**
     * Obtiene una especialidad por índice
     */
    public String getEspecialidad(int indice) {
        if (indice >= 0 && indice < especialidades.length) {
            return especialidades[indice];
        }
        return null;
    }

    /**
     * Obtiene el número total de especialidades
     */
    public int getCantidadEspecialidades() {
        return especialidades.length;
    }

    /**
     * Obtiene todos los niveles de triage
     */
    public String[] getNivelesTriage() {
        return nivelesTriage;
    }

    /**
     * Obtiene la descripción de un nivel de triage (1-5)
     */
    public String getNivelTriage(int nivel) {
        if (nivel >= 1 && nivel <= 5) {
            return nivelesTriage[nivel - 1];
        }
        return "Nivel inválido";
    }

    /**
     * Muestra todas las especialidades
     */
    public void mostrarEspecialidades() {
        System.out.println("\n  Especialidades médicas disponibles:");
        for (int i = 0; i < especialidades.length; i++) {
            System.out.println("  " + (i + 1) + ". " + especialidades[i]);
        }
    }

    /**
     * Muestra todos los niveles de triage
     */
    public void mostrarNivelesTriage() {
        System.out.println("\n  Niveles de triage:");
        for (int i = 0; i < nivelesTriage.length; i++) {
            System.out.println("  " + (i + 1) + ". " + nivelesTriage[i]);
        }
    }

    /**
     * Valida si un nivel de triage es válido
     */
    public boolean esNivelTriageValido(int nivel) {
        return nivel >= 1 && nivel <= 5;
    }

    /**
     * Valida si un índice de especialidad es válido
     */
    public boolean esEspecialidadValida(int indice) {
        return indice >= 0 && indice < especialidades.length;
    }
}
