package com.hospital.util;

import com.hospital.Hospital;
import com.hospital.entidades.*;
import com.hospital.estructuras.*;
import com.hospital.util.PersistenciaJSON.*;

import java.util.ArrayList;

/**
 * Gestor que coordina el guardado y carga del estado completo del hospital
 */
public class GestorPersistencia {

    /**
     * Guarda el estado completo del hospital en JSON
     */
    public static boolean guardarHospital(Hospital hospital) {
        DatosHospital datos = new DatosHospital();

        // Guardar pacientes en cola de espera
        datos.pacientesEnEspera = new ArrayList<>();
        Paciente[] pacientesEspera = hospital.getColaEspera().obtenerTodos();
        for (Paciente p : pacientesEspera) {
            datos.pacientesEnEspera.add(PersistenciaJSON.convertirPaciente(p));
        }

        // Guardar pacientes hospitalizados
        datos.pacientesHospitalizados = new ArrayList<>();
        ListaSimple<Paciente> listaHospitalizados = hospital.getListaPacientesHospitalizados();
        for (int i = 0; i < listaHospitalizados.getTamano(); i++) {
            Paciente p = listaHospitalizados.obtener(i);
            datos.pacientesHospitalizados.add(PersistenciaJSON.convertirPaciente(p));
        }

        // Guardar médicos
        datos.medicos = new ArrayList<>();
        ListaDoble<Medico> listaMedicos = hospital.getListaMedicos();
        for (int i = 0; i < listaMedicos.getTamano(); i++) {
            Medico m = listaMedicos.obtener(i);
            datos.medicos.add(PersistenciaJSON.convertirMedico(m));
        }

        // Guardar estado de camas (matriz)
        MatrizCamas matriz = hospital.getMatrizCamas();
        datos.estadoCamas = new int[matriz.getPisos()][matriz.getCamasPorPiso()];
        for (int i = 0; i < matriz.getPisos(); i++) {
            for (int j = 0; j < matriz.getCamasPorPiso(); j++) {
                datos.estadoCamas[i][j] = matriz.getEstadoCama(i, j);
            }
        }

        // Guardar otros datos
        datos.totalPacientesAtendidos = hospital.getTotalPacientesAtendidos();
        datos.turnoActual = hospital.getListaTurnos().obtenerActual() != null ?
                           hospital.getListaTurnos().obtenerActual().toString() : "Turno Mañana (06:00 - 14:00)";

        // Guardar historial (solo últimos 50 para no hacer el archivo muy grande)
        datos.historialAtenciones = new ArrayList<>();

        return PersistenciaJSON.guardarDatos(datos);
    }

    /**
     * Carga el estado del hospital desde JSON
     */
    public static boolean cargarHospital(Hospital hospital) {
        DatosHospital datos = PersistenciaJSON.cargarDatos();

        if (datos == null) {
            return false;
        }

        try {
            // Limpiar estado actual
            hospital.getColaEspera().limpiar();

            // Cargar pacientes en cola de espera
            if (datos.pacientesEnEspera != null) {
                for (PacienteData pd : datos.pacientesEnEspera) {
                    Paciente p = PersistenciaJSON.convertirAPaciente(pd);
                    hospital.getColaEspera().encolar(p);
                }
            }

            // Cargar pacientes hospitalizados
            if (datos.pacientesHospitalizados != null) {
                for (PacienteData pd : datos.pacientesHospitalizados) {
                    Paciente p = PersistenciaJSON.convertirAPaciente(pd);
                    hospital.getListaPacientesHospitalizados().agregar(p);
                    hospital.getArbolPacientes().insertar(p);
                }
            }

            // Restaurar estado de camas
            if (datos.estadoCamas != null) {
                MatrizCamas matriz = hospital.getMatrizCamas();
                for (int i = 0; i < datos.estadoCamas.length && i < matriz.getPisos(); i++) {
                    for (int j = 0; j < datos.estadoCamas[i].length && j < matriz.getCamasPorPiso(); j++) {
                        int estado = datos.estadoCamas[i][j];
                        if (estado == MatrizCamas.OCUPADA) {
                            matriz.asignarCama(i, j);
                        } else if (estado == MatrizCamas.MANTENIMIENTO) {
                            matriz.ponerEnMantenimiento(i, j);
                        }
                    }
                }
            }

            // Restaurar contador de pacientes atendidos
            if (datos.totalPacientesAtendidos > 0) {
                hospital.setTotalPacientesAtendidos(datos.totalPacientesAtendidos);
            }

            System.out.println("  Estado del hospital restaurado exitosamente");
            System.out.println("  - Pacientes en espera: " + (datos.pacientesEnEspera != null ? datos.pacientesEnEspera.size() : 0));
            System.out.println("  - Pacientes hospitalizados: " + (datos.pacientesHospitalizados != null ? datos.pacientesHospitalizados.size() : 0));
            System.out.println("  - Médicos: " + (datos.medicos != null ? datos.medicos.size() : 0));

            return true;

        } catch (Exception e) {
            System.err.println("\n  Error al restaurar datos: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
