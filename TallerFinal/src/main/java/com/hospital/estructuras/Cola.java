package com.hospital.estructuras;

/**
 * Implementación manual de una Cola (Queue) usando nodos enlazados
 * Utilizada para manejar la fila de pacientes en espera
 */
public class Cola<T> {

    /**
     * Nodo interno de la cola
     */
    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo frente;
    private Nodo fin;
    private int tamano;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }

    /**
     * Agrega un elemento al final de la cola
     */
    public void encolar(T dato) {
        Nodo nuevoNodo = new Nodo(dato);

        if (estaVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
        }
        tamano++;
    }

    /**
     * Elimina y retorna el elemento del frente de la cola
     */
    public T desencolar() {
        if (estaVacia()) {
            return null;
        }

        T dato = frente.dato;
        frente = frente.siguiente;

        if (frente == null) {
            fin = null;
        }

        tamano--;
        return dato;
    }

    /**
     * Retorna el elemento del frente sin eliminarlo
     */
    public T verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.dato;
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
     * Muestra todos los elementos de la cola
     */
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("  La cola está vacía");
            return;
        }

        Nodo actual = frente;
        int posicion = 1;
        while (actual != null) {
            System.out.println("  " + posicion + ". " + actual.dato);
            actual = actual.siguiente;
            posicion++;
        }
    }
}
