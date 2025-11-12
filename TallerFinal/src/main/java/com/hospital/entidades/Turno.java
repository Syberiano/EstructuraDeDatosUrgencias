package com.hospital.entidades;

/**
 * Clase que representa un turno médico
 */
public class Turno {
    private String nombre;
    private String horario;

    public Turno(String nombre, String horario) {
        this.nombre = nombre;
        this.horario = horario;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", nombre, horario);
    }
}
