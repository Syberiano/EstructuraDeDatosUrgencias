package com.hospital.util;

import com.hospital.Hospital;
import com.hospital.entidades.Paciente;
import java.util.Random;

/**
 * Simulador de llegada automática de pacientes al hospital
 * Genera pacientes con datos aleatorios y niveles de triage realistas
 */
public class SimuladorPacientes {

    private static final Random random = new Random();

    // Nombres aleatorios para simular pacientes
    private static final String[] NOMBRES = {
        "Juan Pérez", "María García", "Carlos López", "Ana Martínez",
        "Pedro Rodríguez", "Laura Fernández", "José González", "Carmen Sánchez",
        "Miguel Torres", "Isabel Ramírez", "Francisco Díaz", "Teresa Moreno",
        "Antonio Romero", "Rosa Jiménez", "Manuel Ruiz", "Pilar Navarro",
        "David Hernández", "Elena Castro", "Javier Ortiz", "Lucía Rubio",
        "Rafael Molina", "Mercedes Álvarez", "Fernando Gil", "Dolores Serrano",
        "Alberto Blanco", "Cristina Vega", "Sergio Morales", "Patricia Núñez",
        "Roberto Ramos", "Beatriz Guerrero", "Ángel Prieto", "Raquel Medina",
        "Luis Castillo", "Marta Iglesias", "Pablo Garrido", "Silvia Santos",
        "Jorge Campos", "Andrea León", "Ricardo Herrera", "Natalia Vargas"
    };

    /**
     * Genera un número de documento aleatorio
     */
    private static String generarDocumento() {
        long numero = 10000000L + random.nextInt(90000000);
        return String.valueOf(numero);
    }

    /**
     * Genera un nivel de triage aleatorio con distribución realista
     * En un hospital real:
     * - Nivel 1 (Crítico): ~5%
     * - Nivel 2 (Muy urgente): ~15%
     * - Nivel 3 (Urgente): ~30%
     * - Nivel 4 (Menos urgente): ~35%
     * - Nivel 5 (No urgente): ~15%
     */
    private static int generarNivelTriageRealista() {
        int valor = random.nextInt(100);

        if (valor < 5) {
            return 1; // 5% crítico
        } else if (valor < 20) {
            return 2; // 15% muy urgente
        } else if (valor < 50) {
            return 3; // 30% urgente
        } else if (valor < 85) {
            return 4; // 35% menos urgente
        } else {
            return 5; // 15% no urgente
        }
    }

    /**
     * Genera un paciente aleatorio
     */
    public static Paciente generarPacienteAleatorio() {
        String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
        String documento = generarDocumento();
        int nivelTriage = generarNivelTriageRealista();

        return new Paciente(documento, nombre, nivelTriage);
    }

    /**
     * Simula la llegada de múltiples pacientes
     */
    public static void simularLlegadaPacientes(Hospital hospital, int cantidad) {
        System.out.println("\n  Simulando llegada de " + cantidad + " pacientes...");
        System.out.println();

        int[] conteoPorTriage = new int[5];

        for (int i = 0; i < cantidad; i++) {
            Paciente paciente = generarPacienteAleatorio();
            hospital.ingresarPaciente(paciente);
            conteoPorTriage[paciente.getNivelTriage() - 1]++;

            // Pausa corta para simular tiempo entre llegadas
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n  Simulación completada!");
        System.out.println("  " + cantidad + " pacientes agregados a la cola de espera");
        System.out.println();
        System.out.println("  Distribución generada:");
        System.out.println("  - Nivel 1 (Crítico): " + conteoPorTriage[0]);
        System.out.println("  - Nivel 2 (Muy urgente): " + conteoPorTriage[1]);
        System.out.println("  - Nivel 3 (Urgente): " + conteoPorTriage[2]);
        System.out.println("  - Nivel 4 (Menos urgente): " + conteoPorTriage[3]);
        System.out.println("  - Nivel 5 (No urgente): " + conteoPorTriage[4]);
        System.out.println();
    }

    /**
     * Simula un flujo continuo de pacientes con asignación automática de camas
     */
    public static void simularFlujoContinuo(Hospital hospital, int cantidadPacientes, boolean asignarCamasAuto) {
        System.out.println("\n  Iniciando simulación de flujo continuo...");
        System.out.println("  Pacientes a generar: " + cantidadPacientes);
        System.out.println("  Asignación automática de camas: " + (asignarCamasAuto ? "Sí" : "No"));
        System.out.println();

        int pacientesIngresados = 0;
        int pacientesConCama = 0;

        for (int i = 0; i < cantidadPacientes; i++) {
            // Generar paciente
            Paciente paciente = generarPacienteAleatorio();
            System.out.println("  [" + (i + 1) + "] Llega: " + paciente.toShortString());

            hospital.ingresarPaciente(paciente);
            pacientesIngresados++;

            // Intentar asignar cama si está habilitado
            if (asignarCamasAuto) {
                if (hospital.asignarCamaPaciente()) {
                    pacientesConCama++;
                }
            }

            // Pausa entre llegadas
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n  Simulación finalizada!");
        System.out.println("  - Pacientes ingresados: " + pacientesIngresados);
        if (asignarCamasAuto) {
            System.out.println("  - Pacientes con cama asignada: " + pacientesConCama);
            System.out.println("  - Pacientes aún en espera: " + (pacientesIngresados - pacientesConCama));
        }
        System.out.println();
    }

    /**
     * Genera un escenario de emergencia con pacientes críticos
     */
    public static void simularEmergencia(Hospital hospital, int cantidadCriticos) {
        System.out.println("\n  ¡SIMULACIÓN DE EMERGENCIA!");
        System.out.println("  Llegada masiva de " + cantidadCriticos + " pacientes críticos");
        System.out.println();

        for (int i = 0; i < cantidadCriticos; i++) {
            String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
            String documento = generarDocumento();

            // Todos son nivel 1 o 2 (críticos/muy urgentes)
            int nivelTriage = random.nextBoolean() ? 1 : 2;

            Paciente paciente = new Paciente(documento, nombre, nivelTriage);
            System.out.println("  [EMERGENCIA] " + paciente.toShortString());

            hospital.ingresarPaciente(paciente);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n  Emergencia registrada en el sistema");
        System.out.println("  Estos pacientes tienen máxima prioridad");
        System.out.println();
    }
}
