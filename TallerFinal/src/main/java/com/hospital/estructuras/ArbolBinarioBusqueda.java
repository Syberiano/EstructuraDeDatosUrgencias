package com.hospital.estructuras;

import com.hospital.entidades.Paciente;

/**
 * Implementación manual de un Árbol Binario de Búsqueda
 * Utilizado para búsqueda rápida de pacientes por número de documento
 */
public class ArbolBinarioBusqueda {

    /**
     * Nodo interno del árbol
     */
    private class Nodo {
        String clave; // Número de documento
        Paciente paciente;
        Nodo izquierdo;
        Nodo derecho;

        Nodo(String clave, Paciente paciente) {
            this.clave = clave;
            this.paciente = paciente;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    private Nodo raiz;
    private int tamano;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
        this.tamano = 0;
    }

    /**
     * Inserta un paciente en el árbol
     */
    public void insertar(Paciente paciente) {
        raiz = insertarRecursivo(raiz, paciente.getNumeroDocumento(), paciente);
        tamano++;
    }

    private Nodo insertarRecursivo(Nodo nodo, String clave, Paciente paciente) {
        if (nodo == null) {
            return new Nodo(clave, paciente);
        }

        int comparacion = clave.compareTo(nodo.clave);

        if (comparacion < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, clave, paciente);
        } else if (comparacion > 0) {
            nodo.derecho = insertarRecursivo(nodo.derecho, clave, paciente);
        } else {
            // Si ya existe, actualizar el paciente
            nodo.paciente = paciente;
        }

        return nodo;
    }

    /**
     * Busca un paciente por número de documento
     */
    public Paciente buscar(String numeroDocumento) {
        return buscarRecursivo(raiz, numeroDocumento);
    }

    private Paciente buscarRecursivo(Nodo nodo, String clave) {
        if (nodo == null) {
            return null;
        }

        int comparacion = clave.compareTo(nodo.clave);

        if (comparacion == 0) {
            return nodo.paciente;
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.izquierdo, clave);
        } else {
            return buscarRecursivo(nodo.derecho, clave);
        }
    }

    /**
     * Elimina un paciente del árbol por número de documento
     */
    public boolean eliminar(String numeroDocumento) {
        if (buscar(numeroDocumento) == null) {
            return false;
        }
        raiz = eliminarRecursivo(raiz, numeroDocumento);
        tamano--;
        return true;
    }

    private Nodo eliminarRecursivo(Nodo nodo, String clave) {
        if (nodo == null) {
            return null;
        }

        int comparacion = clave.compareTo(nodo.clave);

        if (comparacion < 0) {
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, clave);
        } else if (comparacion > 0) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, clave);
        } else {
            // Caso 1: Nodo sin hijos
            if (nodo.izquierdo == null && nodo.derecho == null) {
                return null;
            }
            // Caso 2: Nodo con un solo hijo
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            }
            if (nodo.derecho == null) {
                return nodo.izquierdo;
            }
            // Caso 3: Nodo con dos hijos
            Nodo minNodo = encontrarMinimo(nodo.derecho);
            nodo.clave = minNodo.clave;
            nodo.paciente = minNodo.paciente;
            nodo.derecho = eliminarRecursivo(nodo.derecho, minNodo.clave);
        }

        return nodo;
    }

    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    /**
     * Verifica si el árbol está vacío
     */
    public boolean estaVacio() {
        return raiz == null;
    }

    /**
     * Retorna el tamaño del árbol
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * Muestra el árbol en orden (in-order)
     */
    public void mostrarEnOrden() {
        if (estaVacio()) {
            System.out.println("  El árbol está vacío");
            return;
        }
        mostrarEnOrdenRecursivo(raiz);
    }

    private void mostrarEnOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            mostrarEnOrdenRecursivo(nodo.izquierdo);
            System.out.println("\n  " + nodo.paciente.toShortString());
            mostrarEnOrdenRecursivo(nodo.derecho);
        }
    }
}
