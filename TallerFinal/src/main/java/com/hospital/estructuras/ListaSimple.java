package com.hospital.estructuras;

/**
 * Implementación manual de una Lista Simple
 * Utilizada para almacenar pacientes hospitalizados
 */
public class ListaSimple<T> {

    /**
     * Nodo interno de la lista
     */
    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;
    private int tamano;

    public ListaSimple() {
        this.cabeza = null;
        this.tamano = 0;
    }

    /**
     * Agrega un elemento al final de la lista
     */
    public void agregar(T dato) {
        Nodo nuevoNodo = new Nodo(dato);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamano++;
    }

    /**
     * Elimina un elemento por su posición (índice basado en 0)
     */
    public boolean eliminar(int indice) {
        if (indice < 0 || indice >= tamano) {
            return false;
        }

        if (indice == 0) {
            cabeza = cabeza.siguiente;
            tamano--;
            return true;
        }

        Nodo actual = cabeza;
        for (int i = 0; i < indice - 1; i++) {
            actual = actual.siguiente;
        }

        actual.siguiente = actual.siguiente.siguiente;
        tamano--;
        return true;
    }

    /**
     * Obtiene un elemento por su posición
     */
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamano) {
            return null;
        }

        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }

        return actual.dato;
    }

    /**
     * Busca un elemento y retorna su índice (-1 si no existe)
     */
    public int buscar(T dato) {
        Nodo actual = cabeza;
        int indice = 0;

        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return indice;
            }
            actual = actual.siguiente;
            indice++;
        }

        return -1;
    }

    /**
     * Verifica si la lista está vacía
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Retorna el tamaño de la lista
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * Muestra todos los elementos de la lista
     */
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("  La lista está vacía");
            return;
        }

        Nodo actual = cabeza;
        int posicion = 1;
        while (actual != null) {
            System.out.println("\n  --- Paciente " + posicion + " ---");
            System.out.println("  " + actual.dato.toString().replace("\n", "\n  "));
            actual = actual.siguiente;
            posicion++;
        }
    }
}
