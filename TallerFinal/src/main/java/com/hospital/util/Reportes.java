package com.hospital.util;

import com.hospital.Hospital;
import com.hospital.entidades.*;
import com.hospital.estructuras.*;

/**
 * Clase utilitaria para generar reportes detallados del hospital
 */
public class Reportes {

    /**
     * Genera reporte del médico con más pacientes atendidos
     */
    public static void reporteMedicoMasActivo(Hospital hospital) {
        System.out.println("\n  Reporte: Médico con más pacientes atendidos");
        System.out.println();

        ListaDoble<Medico> listaMedicos = hospital.getListaMedicos();

        if (listaMedicos.estaVacia()) {
            System.out.println("  No hay médicos registrados");
            return;
        }

        Medico medicoMasActivo = null;
        int maxPacientes = 0;

        for (int i = 0; i < listaMedicos.getTamano(); i++) {
            Medico medico = listaMedicos.obtener(i);
            if (medico.getPacientesAtendidos() > maxPacientes) {
                maxPacientes = medico.getPacientesAtendidos();
                medicoMasActivo = medico;
            }
        }

        if (medicoMasActivo == null || maxPacientes == 0) {
            System.out.println("  Ningún médico ha atendido pacientes aún");
            return;
        }

        System.out.println("  Médico más activo:");
        System.out.println("  " + medicoMasActivo.toString().replace("\n", "\n  "));
        System.out.println();
    }

    /**
     * Genera reporte de todos los médicos ordenados por productividad
     */
    public static void reporteMedicosPorProductividad(Hospital hospital) {
        System.out.println("\n  Reporte: Médicos ordenados por pacientes atendidos");
        System.out.println();

        ListaDoble<Medico> listaMedicos = hospital.getListaMedicos();

        if (listaMedicos.estaVacia()) {
            System.out.println("  No hay médicos registrados");
            return;
        }

        // Convertir a array para ordenar
        Medico[] medicos = new Medico[listaMedicos.getTamano()];
        for (int i = 0; i < listaMedicos.getTamano(); i++) {
            medicos[i] = listaMedicos.obtener(i);
        }

        // Ordenamiento burbuja simple (descendente por pacientes atendidos)
        for (int i = 0; i < medicos.length - 1; i++) {
            for (int j = 0; j < medicos.length - i - 1; j++) {
                if (medicos[j].getPacientesAtendidos() < medicos[j + 1].getPacientesAtendidos()) {
                    Medico temp = medicos[j];
                    medicos[j] = medicos[j + 1];
                    medicos[j + 1] = temp;
                }
            }
        }

        System.out.println("  Ranking de médicos:");
        for (int i = 0; i < medicos.length; i++) {
            System.out.println("\n  " + (i + 1) + ". " + medicos[i].getNombre());
            System.out.println("     Especialidad: " + medicos[i].getEspecialidad());
            System.out.println("     Pacientes atendidos: " + medicos[i].getPacientesAtendidos());
        }
        System.out.println();
    }

    /**
     * Genera reporte de ocupación de camas
     */
    public static void reporteOcupacionCamas(Hospital hospital) {
        System.out.println("\n  Reporte: Ocupación de camas del hospital");
        System.out.println();

        MatrizCamas matriz = hospital.getMatrizCamas();
        int totalCamas = matriz.getPisos() * matriz.getCamasPorPiso();
        int camasLibres = matriz.contarCamasLibres();
        int camasOcupadas = matriz.contarCamasOcupadas();
        int camasMantenimiento = matriz.contarCamasMantenimiento();

        double porcentajeOcupacion = (camasOcupadas * 100.0) / totalCamas;
        double porcentajeDisponible = (camasLibres * 100.0) / totalCamas;
        double porcentajeMantenimiento = (camasMantenimiento * 100.0) / totalCamas;

        System.out.println("  Total de camas: " + totalCamas);
        System.out.println("  Camas libres: " + camasLibres + " (" + String.format("%.1f", porcentajeDisponible) + "%)");
        System.out.println("  Camas ocupadas: " + camasOcupadas + " (" + String.format("%.1f", porcentajeOcupacion) + "%)");
        System.out.println("  Camas en mantenimiento: " + camasMantenimiento + " (" + String.format("%.1f", porcentajeMantenimiento) + "%)");
        System.out.println();

        // Indicador visual
        System.out.println("  Indicador de ocupación:");
        if (porcentajeOcupacion < 50) {
            System.out.println("  [BAJA] El hospital tiene buena disponibilidad");
        } else if (porcentajeOcupacion < 80) {
            System.out.println("  [MEDIA] El hospital está moderadamente ocupado");
        } else if (porcentajeOcupacion < 95) {
            System.out.println("  [ALTA] El hospital está muy ocupado");
        } else {
            System.out.println("  [CRÍTICA] El hospital está al límite de capacidad");
        }
        System.out.println();

        // Ocupación por piso
        System.out.println("  Ocupación por piso:");
        for (int i = 0; i < matriz.getPisos(); i++) {
            int ocupadasPiso = 0;
            int libresPiso = 0;
            int mantenimientoPiso = 0;

            for (int j = 0; j < matriz.getCamasPorPiso(); j++) {
                int estado = matriz.getEstadoCama(i, j);
                if (estado == MatrizCamas.OCUPADA) ocupadasPiso++;
                else if (estado == MatrizCamas.LIBRE) libresPiso++;
                else if (estado == MatrizCamas.MANTENIMIENTO) mantenimientoPiso++;
            }

            double ocupacionPiso = (ocupadasPiso * 100.0) / matriz.getCamasPorPiso();
            System.out.println("  Piso " + (i + 1) + ": " + ocupadasPiso + "/" + matriz.getCamasPorPiso() +
                             " ocupadas (" + String.format("%.1f", ocupacionPiso) + "%)");
        }
        System.out.println();
    }

    /**
     * Genera reporte de distribución de pacientes por triage
     */
    public static void reporteDistribucionTriage(Hospital hospital) {
        System.out.println("\n  Reporte: Distribución de pacientes por nivel de triage");
        System.out.println();

        int[] conteoTriage = new int[5]; // índices 0-4 para niveles 1-5

        // Contar pacientes en cola de espera
        Paciente[] pacientesEspera = hospital.getColaEspera().obtenerTodos();
        for (Paciente p : pacientesEspera) {
            int nivel = p.getNivelTriage();
            if (nivel >= 1 && nivel <= 5) {
                conteoTriage[nivel - 1]++;
            }
        }

        // Contar pacientes hospitalizados
        ListaSimple<Paciente> hospitalizados = hospital.getListaPacientesHospitalizados();
        for (int i = 0; i < hospitalizados.getTamano(); i++) {
            Paciente p = hospitalizados.obtener(i);
            int nivel = p.getNivelTriage();
            if (nivel >= 1 && nivel <= 5) {
                conteoTriage[nivel - 1]++;
            }
        }

        int totalPacientes = 0;
        for (int count : conteoTriage) {
            totalPacientes += count;
        }

        if (totalPacientes == 0) {
            System.out.println("  No hay pacientes en el sistema");
            return;
        }

        System.out.println("  Pacientes por nivel de urgencia:");
        String[] niveles = {
            "Nivel 1 - Resucitación (Crítico)",
            "Nivel 2 - Emergencia (Muy urgente)",
            "Nivel 3 - Urgente",
            "Nivel 4 - Menos urgente",
            "Nivel 5 - No urgente"
        };

        for (int i = 0; i < 5; i++) {
            double porcentaje = (conteoTriage[i] * 100.0) / totalPacientes;
            System.out.println("  " + niveles[i] + ": " + conteoTriage[i] +
                             " (" + String.format("%.1f", porcentaje) + "%)");

            // Barra visual
            int barLength = (int)(porcentaje / 5);
            System.out.print("    ");
            for (int j = 0; j < barLength; j++) {
                System.out.print("█");
            }
            System.out.println();
        }
        System.out.println("\n  Total de pacientes: " + totalPacientes);
        System.out.println();
    }

    /**
     * Genera reporte completo del estado del hospital
     */
    public static void reporteCompleto(Hospital hospital) {
        System.out.println("\n");
        System.out.println("  ====================================================");
        System.out.println("  REPORTE COMPLETO DEL HOSPITAL");
        System.out.println("  ====================================================");

        reporteOcupacionCamas(hospital);
        reporteDistribucionTriage(hospital);
        reporteMedicosPorProductividad(hospital);

        System.out.println("\n  Resumen general:");
        System.out.println("  - Total pacientes atendidos: " + hospital.getTotalPacientesAtendidos());
        System.out.println("  - Pacientes en espera: " + hospital.getColaEspera().getTamano());
        System.out.println("  - Pacientes hospitalizados: " + hospital.getListaPacientesHospitalizados().getTamano());
        System.out.println("  - Médicos disponibles: " + hospital.getListaMedicos().getTamano());
        System.out.println();
        System.out.println("  ====================================================");
        System.out.println();
    }
}
