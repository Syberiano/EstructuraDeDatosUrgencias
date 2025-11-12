package com.hospital.estructuras;

/**
 * Implementación manual de una Pila (Stack) usando nodos enlazados
 * Utilizada para guardar el historial de atención médica
 */
public class Pila<T> {

    /**
     * Nodo interno de la pila
     */
    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo tope;
    private int tamano;

    public Pila() {
        this.tope = null;
        this.tamano = 0;
    }

    /**
     * Agrega un elemento al tope de la pila
     */
    public void apilar(T dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
        tamano++;
    }

    /**
     * Elimina y retorna el elemento del tope de la pila
     */
    public T desapilar() {
        if (estaVacia()) {
            return null;
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        tamano--;
        return dato;
    }

    /**
     * Retorna el elemento del tope sin eliminarlo
     */
    public T verTope() {
        if (estaVacia()) {
            return null;
        }
        return tope.dato;
    }

    /**
     * Verifica si la pila está vacía
     */
    public boolean estaVacia() {
        return tope == null;
    }

    /**
     * Retorna el tamaño de la pila
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * Muestra todos los elementos de la pila
     */
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("  La pila está vacía");
            return;
        }

        Nodo actual = tope;
        int posicion = 1;
        while (actual != null) {
            System.out.println("\n  --- Registro " + posicion + " ---");
            System.out.println("  " + actual.dato.toString().replace("\n", "\n  "));
            actual = actual.siguiente;
            posicion++;
        }
    }
}
