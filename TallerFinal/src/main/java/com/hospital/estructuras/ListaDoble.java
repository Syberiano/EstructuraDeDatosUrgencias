package com.hospital.estructuras;

/**
 * Implementación manual de una Lista Doblemente Enlazada
 * Utilizada para almacenar médicos del hospital
 */
public class ListaDoble<T> {

    /**
     * Nodo interno de la lista doble
     */
    private class Nodo {
        T dato;
        Nodo siguiente;
        Nodo anterior;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    private Nodo cabeza;
    private Nodo cola;
    private int tamano;

    public ListaDoble() {
        this.cabeza = null;
        this.cola = null;
        this.tamano = 0;
    }

    /**
     * Agrega un elemento al final de la lista
     */
    public void agregar(T dato) {
        Nodo nuevoNodo = new Nodo(dato);

        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.siguiente = nuevoNodo;
            nuevoNodo.anterior = cola;
            cola = nuevoNodo;
        }
        tamano++;
    }

    /**
     * Elimina un elemento por su posición
     */
    public boolean eliminar(int indice) {
        if (indice < 0 || indice >= tamano) {
            return false;
        }

        Nodo nodoAEliminar;

        if (indice == 0) {
            nodoAEliminar = cabeza;
            cabeza = cabeza.siguiente;
            if (cabeza != null) {
                cabeza.anterior = null;
            } else {
                cola = null;
            }
        } else if (indice == tamano - 1) {
            nodoAEliminar = cola;
            cola = cola.anterior;
            cola.siguiente = null;
        } else {
            nodoAEliminar = obtenerNodo(indice);
            nodoAEliminar.anterior.siguiente = nodoAEliminar.siguiente;
            nodoAEliminar.siguiente.anterior = nodoAEliminar.anterior;
        }

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

        return obtenerNodo(indice).dato;
    }

    /**
     * Método auxiliar para obtener un nodo por su índice
     */
    private Nodo obtenerNodo(int indice) {
        Nodo actual;

        // Optimización: recorrer desde el extremo más cercano
        if (indice < tamano / 2) {
            actual = cabeza;
            for (int i = 0; i < indice; i++) {
                actual = actual.siguiente;
            }
        } else {
            actual = cola;
            for (int i = tamano - 1; i > indice; i--) {
                actual = actual.anterior;
            }
        }

        return actual;
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
     * Muestra todos los elementos desde la cabeza hacia adelante
     */
    public void mostrarAdelante() {
        if (estaVacia()) {
            System.out.println("  La lista está vacía");
            return;
        }

        Nodo actual = cabeza;
        int posicion = 1;
        while (actual != null) {
            System.out.println("\n  --- Médico " + posicion + " ---");
            System.out.println("  " + actual.dato.toString().replace("\n", "\n  "));
            actual = actual.siguiente;
            posicion++;
        }
    }

    /**
     * Muestra todos los elementos desde la cola hacia atrás
     */
    public void mostrarAtras() {
        if (estaVacia()) {
            System.out.println("  La lista está vacía");
            return;
        }

        Nodo actual = cola;
        int posicion = tamano;
        while (actual != null) {
            System.out.println("\n  --- Médico " + posicion + " ---");
            System.out.println("  " + actual.dato.toString().replace("\n", "\n  "));
            actual = actual.anterior;
            posicion--;
        }
    }
}
