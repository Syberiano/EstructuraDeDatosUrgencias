package com.hospital.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa un registro de atención médica
 */
public class RegistroAtencion {
    private String pacienteDocumento;
    private String pacienteNombre;
    private String medicoNombre;
    private String descripcion;
    private LocalDateTime fecha;

    public RegistroAtencion(String pacienteDocumento, String pacienteNombre,
                           String medicoNombre, String descripcion) {
        this.pacienteDocumento = pacienteDocumento;
        this.pacienteNombre = pacienteNombre;
        this.medicoNombre = medicoNombre;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
    }

    // Getters y setters
    public String getPacienteDocumento() {
        return pacienteDocumento;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public String getMedicoNombre() {
        return medicoNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    //ToString para mostrar información del registro de atención
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format(
            "Registro de atención:\n" +
            "  Fecha: %s\n" +
            "  Paciente: %s (%s)\n" +
            "  Médico: %s\n" +
            "  Descripción: %s",
            fecha.format(formatter), pacienteNombre, pacienteDocumento,
            medicoNombre, descripcion
        );
    }
}
