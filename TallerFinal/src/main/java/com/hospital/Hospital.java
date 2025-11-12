package com.hospital;

import com.hospital.entidades.*;
import com.hospital.estructuras.*;

/**
 * Clase principal que gestiona todo el sistema hospitalario
 * Integra todas las estructuras de datos
 */
public class Hospital {

    // Array para especialidades y niveles de triage
    private ArrayEspecialidades arrayEspecialidades;

    // Matriz para gestión de camas
    private MatrizCamas matrizCamas;

    // Pila para historial de atención
    private Pila<RegistroAtencion> pilaHistorial;

    // Cola de Prioridad para pacientes en espera (ordenados por triage)
    private ColaPrioridad colaEspera;

    // Lista Simple para pacientes hospitalizados
    private ListaSimple<Paciente> listaPacientesHospitalizados;

    // Lista Doble para médicos
    private ListaDoble<Medico> listaMedicos;

    // Lista Circular para turnos médicos
    private ListaCircular<Turno> listaTurnos;

    // Árbol Binario para búsqueda rápida de pacientes
    private ArbolBinarioBusqueda arbolPacientes;

    // Contador de pacientes atendidos
    private int totalPacientesAtendidos;

    /**
     * Constructor del hospital
     */
    public Hospital(int pisos, int camasPorPiso) {
        this.arrayEspecialidades = new ArrayEspecialidades();
        this.matrizCamas = new MatrizCamas(pisos, camasPorPiso);
        this.pilaHistorial = new Pila<>();
        this.colaEspera = new ColaPrioridad();
        this.listaPacientesHospitalizados = new ListaSimple<>();
        this.listaMedicos = new ListaDoble<>();
        this.listaTurnos = new ListaCircular<>();
        this.arbolPacientes = new ArbolBinarioBusqueda();
        this.totalPacientesAtendidos = 0;

        inicializarTurnos();
        inicializarMedicos();
    }

    /**
     * Inicializa los turnos médicos
     */
    private void inicializarTurnos() {
        listaTurnos.agregar(new Turno("Turno Mañana", "06:00 - 14:00"));
        listaTurnos.agregar(new Turno("Turno Tarde", "14:00 - 22:00"));
        listaTurnos.agregar(new Turno("Turno Noche", "22:00 - 06:00"));
    }

    /**
     * Inicializa algunos médicos de ejemplo
     */
    private void inicializarMedicos() {
        listaMedicos.agregar(new Medico("M001", "Dr. Carlos Pérez", "Urgencias"));
        listaMedicos.agregar(new Medico("M002", "Dra. Ana Gómez", "Pediatría"));
        listaMedicos.agregar(new Medico("M003", "Dr. Luis Martínez", "Cirugía"));
        listaMedicos.agregar(new Medico("M004", "Dra. María Rodríguez", "Medicina Interna"));
        listaMedicos.agregar(new Medico("M005", "Dr. José López", "Cardiología"));
    }

    /**
     * Ingresa un nuevo paciente al sistema
     * Los pacientes se agregan a una cola de prioridad basada en nivel de triage.
     * Nivel 1 (más crítico) tiene prioridad sobre nivel 5 (menos urgente).
     * Si tienen el mismo nivel de triage, se respeta el orden de llegada (FIFO).
     */
    public void ingresarPaciente(Paciente paciente) {
        // Agregar a la cola de prioridad (ordenada por triage)
        colaEspera.encolar(paciente);
        System.out.println("\n  Paciente " + paciente.getNombre() + " agregado a la cola de espera");
        System.out.println("  Nivel de triage: " + arrayEspecialidades.getNivelTriage(paciente.getNivelTriage()));
        System.out.println("  Total de pacientes en espera: " + colaEspera.getTamano());
        System.out.println("  (Los pacientes se atienden por prioridad de triage)");
    }

    /**
     * Asigna una cama a un paciente de la cola
     */
    public boolean asignarCamaPaciente() {
        if (colaEspera.estaVacia()) {
            System.out.println("\n  No hay pacientes en la cola de espera");
            return false;
        }

        // Buscar cama libre
        int[] camaLibre = matrizCamas.buscarCamaLibre();
        if (camaLibre == null) {
            System.out.println("\n  No hay camas disponibles en este momento");
            return false;
        }

        // Sacar paciente de la cola
        Paciente paciente = colaEspera.desencolar();

        // Asignar cama
        int piso = camaLibre[0];
        int cama = camaLibre[1];
        matrizCamas.asignarCama(piso, cama);
        paciente.setPisoAsignado(piso);
        paciente.setCamaAsignada(cama);

        // Agregar a lista de hospitalizados
        listaPacientesHospitalizados.agregar(paciente);

        // Agregar al árbol para búsqueda rápida
        arbolPacientes.insertar(paciente);

        System.out.println("\n  Paciente " + paciente.getNombre() + " asignado a:");
        System.out.println("  Piso: " + (piso + 1) + ", Cama: " + (cama + 1));

        return true;
    }

    /**
     * Registra atención médica de un paciente
     */
    public void atenderPaciente(String numeroDocumento, int indiceMedico, String descripcion) {
        // Buscar paciente en el árbol
        Paciente paciente = arbolPacientes.buscar(numeroDocumento);
        if (paciente == null) {
            System.out.println("\n  Paciente no encontrado");
            return;
        }

        // Obtener médico
        Medico medico = listaMedicos.obtener(indiceMedico);
        if (medico == null) {
            System.out.println("\n  Médico no encontrado");
            return;
        }

        // Asignar médico al paciente
        paciente.setMedicoAsignado(medico.getNombre());
        paciente.setDiagnostico(descripcion);

        // Incrementar contador del médico
        medico.incrementarPacientesAtendidos();

        // Registrar en la pila de historial
        RegistroAtencion registro = new RegistroAtencion(
            paciente.getNumeroDocumento(),
            paciente.getNombre(),
            medico.getNombre(),
            descripcion
        );
        pilaHistorial.apilar(registro);

        totalPacientesAtendidos++;

        System.out.println("\n  Atención registrada exitosamente");
        System.out.println("  Paciente: " + paciente.getNombre());
        System.out.println("  Médico: " + medico.getNombre());
        System.out.println("  Diagnóstico: " + descripcion);
    }

    /**
     * Deshacer última atención (sacar de la pila)
     */
    public void deshacerUltimaAtencion() {
        RegistroAtencion registro = pilaHistorial.desapilar();
        if (registro == null) {
            System.out.println("\n  No hay atenciones para deshacer");
            return;
        }

        System.out.println("\n  Última atención deshecha:");
        System.out.println("  " + registro.toString().replace("\n", "\n  "));
        totalPacientesAtendidos--;
    }

    /**
     * Busca un paciente por número de documento
     */
    public Paciente buscarPaciente(String numeroDocumento) {
        return arbolPacientes.buscar(numeroDocumento);
    }

    /**
     * Da de alta a un paciente (libera su cama)
     */
    public boolean darAltaPaciente(String numeroDocumento) {
        // Buscar paciente
        Paciente paciente = arbolPacientes.buscar(numeroDocumento);
        if (paciente == null) {
            System.out.println("\n  Paciente no encontrado");
            return false;
        }

        // Liberar cama
        matrizCamas.liberarCama(paciente.getPisoAsignado(), paciente.getCamaAsignada());

        // Eliminar de la lista de hospitalizados
        for (int i = 0; i < listaPacientesHospitalizados.getTamano(); i++) {
            Paciente p = listaPacientesHospitalizados.obtener(i);
            if (p.getNumeroDocumento().equals(numeroDocumento)) {
                listaPacientesHospitalizados.eliminar(i);
                break;
            }
        }

        // Eliminar del árbol
        arbolPacientes.eliminar(numeroDocumento);

        System.out.println("\n  Paciente " + paciente.getNombre() + " dado de alta");
        return true;
    }

    /**
     * Avanza al siguiente turno médico
     */
    public void avanzarTurno() {
        Turno siguienteTurno = listaTurnos.siguiente();
        System.out.println("\n  Turno cambiado a: " + siguienteTurno);
    }

    /**
     * Agrega un nuevo médico
     */
    public void agregarMedico(Medico medico) {
        listaMedicos.agregar(medico);
        System.out.println("\n  Médico agregado: " + medico.getNombre());
    }

    // Getters para las estructuras
    public ArrayEspecialidades getArrayEspecialidades() {
        return arrayEspecialidades;
    }

    public MatrizCamas getMatrizCamas() {
        return matrizCamas;
    }

    public Pila<RegistroAtencion> getPilaHistorial() {
        return pilaHistorial;
    }

    public ColaPrioridad getColaEspera() {
        return colaEspera;
    }

    public ListaSimple<Paciente> getListaPacientesHospitalizados() {
        return listaPacientesHospitalizados;
    }

    public ListaDoble<Medico> getListaMedicos() {
        return listaMedicos;
    }

    public ListaCircular<Turno> getListaTurnos() {
        return listaTurnos;
    }

    public ArbolBinarioBusqueda getArbolPacientes() {
        return arbolPacientes;
    }

    public int getTotalPacientesAtendidos() {
        return totalPacientesAtendidos;
    }

    /**
     * Muestra estadísticas básicas del hospital
     */
    public void mostrarEstadisticas() {
        System.out.println("\n  Estadísticas del Hospital");
        System.out.println();
        System.out.println("  Total de pacientes atendidos: " + totalPacientesAtendidos);
        System.out.println("  Pacientes en cola de espera: " + colaEspera.getTamano());
        System.out.println("  Pacientes hospitalizados: " + listaPacientesHospitalizados.getTamano());
        System.out.println("  Camas libres: " + matrizCamas.contarCamasLibres());
        System.out.println("  Camas ocupadas: " + matrizCamas.contarCamasOcupadas());
        System.out.println("  Camas en mantenimiento: " + matrizCamas.contarCamasMantenimiento());
        System.out.println("  Médicos disponibles: " + listaMedicos.getTamano());
        System.out.println("  Turno actual: " + listaTurnos.obtenerActual());
        System.out.println();
    }

    /**
     * Muestra estadísticas detalladas del hospital
     */
    public void mostrarEstadisticasDetalladas() {
        System.out.println("\n  Estadísticas Detalladas del Hospital");
        System.out.println();

        // Estadísticas generales
        int totalCamas = matrizCamas.getPisos() * matrizCamas.getCamasPorPiso();
        int camasLibres = matrizCamas.contarCamasLibres();
        int camasOcupadas = matrizCamas.contarCamasOcupadas();
        int camasMantenimiento = matrizCamas.contarCamasMantenimiento();

        double tasaOcupacion = (camasOcupadas * 100.0) / totalCamas;

        System.out.println("  OCUPACIÓN:");
        System.out.println("  - Total de camas: " + totalCamas);
        System.out.println("  - Tasa de ocupación: " + String.format("%.1f", tasaOcupacion) + "%");
        System.out.println("  - Capacidad disponible: " + camasLibres + " camas");
        System.out.println();

        // Estadísticas de pacientes
        int totalEnSistema = colaEspera.getTamano() + listaPacientesHospitalizados.getTamano();

        System.out.println("  PACIENTES:");
        System.out.println("  - Total en sistema: " + totalEnSistema);
        System.out.println("  - En cola de espera: " + colaEspera.getTamano());
        System.out.println("  - Hospitalizados: " + listaPacientesHospitalizados.getTamano());
        System.out.println("  - Total atendidos (histórico): " + totalPacientesAtendidos);
        System.out.println();

        // Estadísticas de médicos
        System.out.println("  PERSONAL MÉDICO:");
        System.out.println("  - Total de médicos: " + listaMedicos.getTamano());

        // Encontrar médico más activo
        Medico medicoMasActivo = null;
        int maxAtenciones = 0;
        int totalAtenciones = 0;

        for (int i = 0; i < listaMedicos.getTamano(); i++) {
            Medico m = listaMedicos.obtener(i);
            totalAtenciones += m.getPacientesAtendidos();
            if (m.getPacientesAtendidos() > maxAtenciones) {
                maxAtenciones = m.getPacientesAtendidos();
                medicoMasActivo = m;
            }
        }

        if (medicoMasActivo != null && maxAtenciones > 0) {
            System.out.println("  - Médico más activo: " + medicoMasActivo.getNombre());
            System.out.println("    Pacientes atendidos: " + maxAtenciones);
        }

        double promedioAtenciones = listaMedicos.getTamano() > 0 ?
                                   (double)totalAtenciones / listaMedicos.getTamano() : 0;
        System.out.println("  - Promedio de atenciones por médico: " + String.format("%.1f", promedioAtenciones));
        System.out.println();

        // Información del turno
        System.out.println("  OPERACIÓN:");
        System.out.println("  - Turno actual: " + listaTurnos.obtenerActual());
        System.out.println();
    }

    /**
     * Obtiene el total de pacientes atendidos
     */
    public void setTotalPacientesAtendidos(int total) {
        this.totalPacientesAtendidos = total;
    }
}
