package com.hospital.estructuras;

/**
 * Implementación manual de una Lista Circular
 * Utilizada para gestionar los turnos médicos (día-noche)
 */
public class ListaCircular<T> {

    /**
     * Nodo interno de la lista circular
     */
    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo actual;
    private int tamano;

    public ListaCircular() {
        this.actual = null;
        this.tamano = 0;
    }

    /**
     * Agrega un elemento a la lista circular
     */
    public void agregar(T dato) {
        Nodo nuevoNodo = new Nodo(dato);

        if (actual == null) {
            actual = nuevoNodo;
            actual.siguiente = actual; // Apunta a sí mismo
        } else {
            // Encontrar el último nodo (el que apunta al actual)
            Nodo ultimo = actual;
            while (ultimo.siguiente != actual) {
                ultimo = ultimo.siguiente;
            }
            ultimo.siguiente = nuevoNodo;
            nuevoNodo.siguiente = actual;
        }
        tamano++;
    }

    /**
     * Avanza al siguiente turno en la lista circular
     */
    public T siguiente() {
        if (actual == null) {
            return null;
        }
        actual = actual.siguiente;
        return actual.dato;
    }

    /**
     * Obtiene el turno actual sin avanzar
     */
    public T obtenerActual() {
        if (actual == null) {
            return null;
        }
        return actual.dato;
    }

    /**
     * Verifica si la lista está vacía
     */
    public boolean estaVacia() {
        return actual == null;
    }

    /**
     * Retorna el tamaño de la lista
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * Muestra todos los elementos de la lista circular
     */
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("  La lista está vacía");
            return;
        }

        Nodo nodo = actual;
        int posicion = 1;
        do {
            System.out.println("  " + posicion + ". " + nodo.dato +
                             (nodo == actual ? " <- Turno actual" : ""));
            nodo = nodo.siguiente;
            posicion++;
        } while (nodo != actual);
    }
}
