package com.hospital.entidades;

/**
 * Clase que representa un médico en el sistema hospitalario
 */
public class Medico {
    private String codigo;
    private String nombre;
    private String especialidad;
    private boolean disponible;
    private int pacientesAtendidos;

    public Medico(String codigo, String nombre, String especialidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.disponible = true;
        this.pacientesAtendidos = 0;
    }

    // Getters y setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getPacientesAtendidos() {
        return pacientesAtendidos;
    }

    public void incrementarPacientesAtendidos() {
        this.pacientesAtendidos++;
    }


    //ToString para mostrar información del médico
    @Override
    public String toString() {
        return String.format(
            "Médico:\n" +
            "  Código: %s\n" +
            "  Nombre: %s\n" +
            "  Especialidad: %s\n" +
            "  Estado: %s\n" +
            "  Pacientes atendidos: %d",
            codigo, nombre, especialidad,
            disponible ? "Disponible" : "No disponible",
            pacientesAtendidos
        );
    }

    public String toShortString() {
        return String.format("%s - %s (%s)", codigo, nombre, especialidad);
    }
}
