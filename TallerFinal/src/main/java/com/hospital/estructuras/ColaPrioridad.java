package com.hospital.estructuras;

import com.hospital.entidades.Paciente;

/**
 * Implementación manual de una Cola de Prioridad
 * Los pacientes se ordenan por nivel de triage (1 = más urgente, 5 = menos urgente)
 * Si tienen el mismo nivel de triage, se respeta el orden de llegada (FIFO)
 */
public class ColaPrioridad {

    /**
     * Nodo interno de la cola de prioridad
     */
    private class Nodo {
        Paciente paciente;
        int prioridad; // Nivel de triage (1-5)
        long ordenLlegada; // Para mantener FIFO en mismo nivel de prioridad
        Nodo siguiente;

        Nodo(Paciente paciente, int prioridad, long ordenLlegada) {
            this.paciente = paciente;
            this.prioridad = prioridad;
            this.ordenLlegada = ordenLlegada;
            this.siguiente = null;
        }
    }

    private Nodo frente;
    private int tamano;
    private long contadorLlegada; // Para mantener orden de llegada

    public ColaPrioridad() {
        this.frente = null;
        this.tamano = 0;
        this.contadorLlegada = 0;
    }

    /**
     * Agrega un paciente a la cola según su prioridad de triage
     * Menor número = mayor prioridad (1 es más urgente que 5)
     * Si tienen la misma prioridad, se respeta el orden de llegada
     */
    public void encolar(Paciente paciente) {
        int prioridad = paciente.getNivelTriage();
        Nodo nuevoNodo = new Nodo(paciente, prioridad, contadorLlegada++);

        // Si la cola está vacía o el nuevo nodo tiene mayor prioridad que el frente
        if (frente == null || prioridad < frente.prioridad ||
            (prioridad == frente.prioridad && nuevoNodo.ordenLlegada < frente.ordenLlegada)) {
            nuevoNodo.siguiente = frente;
            frente = nuevoNodo;
        } else {
            // Buscar la posición correcta según prioridad y orden de llegada
            Nodo actual = frente;

            while (actual.siguiente != null &&
                   (actual.siguiente.prioridad < prioridad ||
                    (actual.siguiente.prioridad == prioridad &&
                     actual.siguiente.ordenLlegada < nuevoNodo.ordenLlegada))) {
                actual = actual.siguiente;
            }

            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        }

        tamano++;
    }

    /**
     * Elimina y retorna el paciente con mayor prioridad (menor nivel de triage)
     * En caso de empate, retorna el que llegó primero
     */
    public Paciente desencolar() {
        if (estaVacia()) {
            return null;
        }

        Paciente paciente = frente.paciente;
        frente = frente.siguiente;
        tamano--;
        return paciente;
    }

    /**
     * Retorna el paciente del frente sin eliminarlo
     */
    public Paciente verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.paciente;
    }

    /**
     * Verifica si la cola está vacía
     */
    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * Retorna el tamaño de la cola
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * Muestra todos los pacientes en la cola ordenados por prioridad
     */
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("  La cola está vacía");
            return;
        }

        System.out.println("  Pacientes ordenados por prioridad (1 = más urgente):\n");

        Nodo actual = frente;
        int posicion = 1;
        while (actual != null) {
            System.out.println("  " + posicion + ". [Triage " + actual.prioridad + "] " +
                             actual.paciente.toShortString());
            actual = actual.siguiente;
            posicion++;
        }
    }

    /**
     * Obtiene todos los pacientes como array para serialización
     */
    public Paciente[] obtenerTodos() {
        if (estaVacia()) {
            return new Paciente[0];
        }

        Paciente[] pacientes = new Paciente[tamano];
        Nodo actual = frente;
        int i = 0;

        while (actual != null) {
            pacientes[i++] = actual.paciente;
            actual = actual.siguiente;
        }

        return pacientes;
    }

    /**
     * Limpia toda la cola
     */
    public void limpiar() {
        frente = null;
        tamano = 0;
        contadorLlegada = 0;
    }
}
