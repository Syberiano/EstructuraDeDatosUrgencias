package com.hospital.entidades;

/**
 * Clase que representa un paciente en el sistema hospitalario
 */
public class Paciente {
    private String numeroDocumento;
    private String nombre;
    private int nivelTriage; // 1-5 (1 más urgente, 5 menos urgente)
    private String diagnostico;
    private int camaAsignada; // -1 si no tiene cama asignada
    private int pisoAsignado; // -1 si no tiene piso asignado
    private String medicoAsignado;

    public Paciente(String numeroDocumento, String nombre, int nivelTriage) {
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.nivelTriage = nivelTriage;
        this.diagnostico = "Sin diagnóstico";
        this.camaAsignada = -1;
        this.pisoAsignado = -1;
        this.medicoAsignado = "Sin asignar";
    }

    // Getters y setters
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelTriage() {
        return nivelTriage;
    }

    public void setNivelTriage(int nivelTriage) {
        this.nivelTriage = nivelTriage;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getCamaAsignada() {
        return camaAsignada;
    }

    public void setCamaAsignada(int camaAsignada) {
        this.camaAsignada = camaAsignada;
    }

    public int getPisoAsignado() {
        return pisoAsignado;
    }

    public void setPisoAsignado(int pisoAsignado) {
        this.pisoAsignado = pisoAsignado;
    }

    public String getMedicoAsignado() {
        return medicoAsignado;
    }

    public void setMedicoAsignado(String medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }

    //ToString para mostrar información del paciente
    @Override
    public String toString() {
        return String.format(
            "Paciente:\n" +
            "  Documento: %s\n" +
            "  Nombre: %s\n" +
            "  Triage: Nivel %d\n" +
            "  Diagnóstico: %s\n" +
            "  Ubicación: Piso %d, Cama %d\n" +
            "  Médico: %s",
            numeroDocumento, nombre, nivelTriage, diagnostico,
            pisoAsignado + 1, camaAsignada + 1, medicoAsignado
        );
    }

    // ToString corto para listas
    public String toShortString() {
        return String.format("%s - %s (Triage: %d)", numeroDocumento, nombre, nivelTriage);
    }
}
