package com.hospital.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hospital.entidades.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Clase utilitaria para guardar y cargar el estado del hospital en formato JSON
 */
public class PersistenciaJSON {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String ARCHIVO_DATOS = "datos_hospital.json";

    /**
     * Clase interna para serializar el estado completo del hospital
     */
    public static class DatosHospital {
        public List<PacienteData> pacientesEnEspera;
        public List<PacienteData> pacientesHospitalizados;
        public List<MedicoData> medicos;
        public List<RegistroAtencionData> historialAtenciones;
        public int[][] estadoCamas;
        public int totalPacientesAtendidos;
        public String turnoActual;
    }

    public static class PacienteData {
        public String numeroDocumento;
        public String nombre;
        public int nivelTriage;
        public String diagnostico;
        public int camaAsignada;
        public int pisoAsignado;
        public String medicoAsignado;
    }

    public static class MedicoData {
        public String codigo;
        public String nombre;
        public String especialidad;
        public boolean disponible;
        public int pacientesAtendidos;
    }

    public static class RegistroAtencionData {
        public String pacienteDocumento;
        public String pacienteNombre;
        public String medicoNombre;
        public String descripcion;
        public String fecha;
    }

    /**
     * Guarda el estado del hospital en un archivo JSON
     */
    public static boolean guardarDatos(DatosHospital datos) {
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(ARCHIVO_DATOS), StandardCharsets.UTF_8)) {
            gson.toJson(datos, writer);
            System.out.println("\n  Datos guardados exitosamente en " + ARCHIVO_DATOS);
            return true;
        } catch (IOException e) {
            System.err.println("\n  Error al guardar datos: " + e.getMessage());
            return false;
        }
    }

    /**
     * Carga el estado del hospital desde un archivo JSON
     */
    public static DatosHospital cargarDatos() {
        File archivo = new File(ARCHIVO_DATOS);

        if (!archivo.exists()) {
            System.out.println("\n  No existe archivo de datos guardados");
            return null;
        }

        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(ARCHIVO_DATOS), StandardCharsets.UTF_8)) {
            DatosHospital datos = gson.fromJson(reader, DatosHospital.class);
            System.out.println("\n  Datos cargados exitosamente desde " + ARCHIVO_DATOS);
            return datos;
        } catch (IOException e) {
            System.err.println("\n  Error al cargar datos: " + e.getMessage());
            return null;
        }
    }

    /**
     * Convierte un Paciente a PacienteData para serialización
     */
    public static PacienteData convertirPaciente(Paciente p) {
        PacienteData data = new PacienteData();
        data.numeroDocumento = p.getNumeroDocumento();
        data.nombre = p.getNombre();
        data.nivelTriage = p.getNivelTriage();
        data.diagnostico = p.getDiagnostico();
        data.camaAsignada = p.getCamaAsignada();
        data.pisoAsignado = p.getPisoAsignado();
        data.medicoAsignado = p.getMedicoAsignado();
        return data;
    }

    /**
     * Convierte PacienteData a Paciente
     */
    public static Paciente convertirAPaciente(PacienteData data) {
        Paciente p = new Paciente(data.numeroDocumento, data.nombre, data.nivelTriage);
        p.setDiagnostico(data.diagnostico);
        p.setCamaAsignada(data.camaAsignada);
        p.setPisoAsignado(data.pisoAsignado);
        p.setMedicoAsignado(data.medicoAsignado);
        return p;
    }

    /**
     * Convierte un Medico a MedicoData para serialización
     */
    public static MedicoData convertirMedico(Medico m) {
        MedicoData data = new MedicoData();
        data.codigo = m.getCodigo();
        data.nombre = m.getNombre();
        data.especialidad = m.getEspecialidad();
        data.disponible = m.isDisponible();
        data.pacientesAtendidos = m.getPacientesAtendidos();
        return data;
    }

    /**
     * Convierte MedicoData a Medico
     */
    public static Medico convertirAMedico(MedicoData data) {
        Medico m = new Medico(data.codigo, data.nombre, data.especialidad);
        m.setDisponible(data.disponible);
        for (int i = 0; i < data.pacientesAtendidos; i++) {
            m.incrementarPacientesAtendidos();
        }
        return m;
    }

    /**
     * Verifica si existe un archivo de datos guardados
     */
    public static boolean existenDatosGuardados() {
        return new File(ARCHIVO_DATOS).exists();
    }
}
