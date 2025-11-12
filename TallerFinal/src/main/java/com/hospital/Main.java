package com.hospital;

import com.hospital.entidades.*;
import com.hospital.util.GestorPersistencia;
import com.hospital.util.PersistenciaJSON;
import com.hospital.util.Reportes;
import com.hospital.util.SimuladorPacientes;
import com.hospital.util.Colores;
import java.util.Scanner;

/**
 * Clase principal con interfaz de consola
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Hospital hospital;

    public static void main(String[] args) {
        // Inicializar hospital con 3 pisos y 5 camas por piso
        hospital = new Hospital(3, 5);

        // Banner de bienvenida colorido
        System.out.println();
        System.out.println(Colores.CYAN_BRILLANTE + Colores.NEGRITA +
                         "            SISTEMA DE GESTIÓN DE HOSPITAL" + Colores.RESET);
        System.out.println(Colores.VERDE_BRILLANTE +
                         "              Estructura de Datos - Trabajo Final" + Colores.RESET);
        System.out.println(Colores.AMARILLO +
                         "           Tecnológico de Antioquia - Ingeniería Software" + Colores.RESET);
        System.out.println();

        // Preguntar si desea cargar datos guardados
        if (PersistenciaJSON.existenDatosGuardados()) {
            Colores.info("Se encontraron datos guardados.");
            System.out.print(Colores.AMARILLO_BRILLANTE + "  ¿Desea cargar los datos anteriores? (s/n): " + Colores.RESET);
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                GestorPersistencia.cargarHospital(hospital);
            }
        }

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("  Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    ingresarPaciente();
                    break;
                case 2:
                    asignarCama();
                    break;
                case 3:
                    atenderPaciente();
                    break;
                case 4:
                    buscarPaciente();
                    break;
                case 5:
                    darAltaPaciente();
                    break;
                case 6:
                    mostrarColaEspera();
                    break;
                case 7:
                    mostrarPacientesHospitalizados();
                    break;
                case 8:
                    mostrarMatrizCamas();
                    break;
                case 9:
                    mostrarMedicos();
                    break;
                case 10:
                    mostrarHistorialAtenciones();
                    break;
                case 11:
                    deshacerUltimaAtencion();
                    break;
                case 12:
                    gestionarTurnos();
                    break;
                case 13:
                    agregarMedico();
                    break;
                case 14:
                    mostrarEspecialidades();
                    break;
                case 15:
                    mostrarEstadisticas();
                    break;
                case 16:
                    guardarDatos();
                    break;
                case 17:
                    cargarDatos();
                    break;
                case 18:
                    mostrarEstadisticasDetalladas();
                    break;
                case 19:
                    menuReportes();
                    break;
                case 20:
                    menuSimulacion();
                    break;
                case 0:
                    // Guardar automáticamente antes de salir
                    System.out.println("Guardando estado del hospital...");
                    GestorPersistencia.guardarHospital(hospital);

                    continuar = false;
                    System.out.println("Gracias por usar el Sistema de Gestión de Hospital");
                    System.out.println("  Sus datos se han guardado automáticamente.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

            if (continuar) {
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        Colores.imprimirLinea("MENÚ PRINCIPAL - HOSPITAL", Colores.CYAN_BRILLANTE + Colores.NEGRITA);
        System.out.println();

        Colores.imprimirLinea("  GESTIÓN DE PACIENTES:", Colores.AZUL_BRILLANTE + Colores.NEGRITA);
        System.out.println(Colores.VERDE + "  1.  Ingresar nuevo paciente" + Colores.RESET);
        System.out.println(Colores.VERDE + "  2.  Asignar cama a paciente en espera" + Colores.RESET);
        System.out.println(Colores.VERDE + "  3.  Atender paciente" + Colores.RESET);
        System.out.println(Colores.VERDE + "  4.  Buscar paciente" + Colores.RESET);
        System.out.println(Colores.VERDE + "  5.  Dar de alta a paciente" + Colores.RESET);
        System.out.println();

        Colores.imprimirLinea("  VISUALIZACIÓN:", Colores.AZUL_BRILLANTE + Colores.NEGRITA);
        System.out.println(Colores.AMARILLO + "  6.  Ver cola de espera (prioridad)" + Colores.RESET);
        System.out.println(Colores.AMARILLO + "  7.  Ver pacientes hospitalizados" + Colores.RESET);
        System.out.println(Colores.AMARILLO + "  8.  Ver estado de camas (matriz)" + Colores.RESET);
        System.out.println(Colores.AMARILLO + "  9.  Ver médicos disponibles" + Colores.RESET);
        System.out.println(Colores.AMARILLO + "  10. Ver historial de atenciones" + Colores.RESET);
        System.out.println();

        Colores.imprimirLinea("  OPERACIONES:", Colores.AZUL_BRILLANTE + Colores.NEGRITA);
        System.out.println(Colores.MAGENTA + "  11. Deshacer última atención" + Colores.RESET);
        System.out.println(Colores.MAGENTA + "  12. Gestionar turnos médicos" + Colores.RESET);
        System.out.println(Colores.MAGENTA + "  13. Agregar nuevo médico" + Colores.RESET);
        System.out.println(Colores.MAGENTA + "  14. Ver especialidades y triage" + Colores.RESET);
        System.out.println();

        Colores.imprimirLinea("  REPORTES Y ANÁLISIS:", Colores.AZUL_BRILLANTE + Colores.NEGRITA);
        System.out.println(Colores.CYAN + "  15. Estadísticas básicas" + Colores.RESET);
        System.out.println(Colores.CYAN + "  18. Estadísticas detalladas" + Colores.RESET);
        System.out.println(Colores.CYAN + "  19. Reportes completos" + Colores.RESET);
        System.out.println();

        Colores.imprimirLinea("  PERSISTENCIA Y SIMULACIÓN:", Colores.AZUL_BRILLANTE + Colores.NEGRITA);
        System.out.println(Colores.VERDE_BRILLANTE + "  16. Guardar datos (JSON)" + Colores.RESET);
        System.out.println(Colores.VERDE_BRILLANTE + "  17. Cargar datos (JSON)" + Colores.RESET);
        System.out.println(Colores.AMARILLO_BRILLANTE + "  20. Simulación de pacientes" + Colores.RESET);
        System.out.println();

        Colores.imprimirLinea("  0.  Salir (auto-guarda)", Colores.ROJO_BRILLANTE);
        System.out.println();
    }

    private static void ingresarPaciente() {
        System.out.println("Ingresar Nuevo Paciente");

        String numeroDocumento = leerTexto("  Número de documento: ");
        String nombre = leerTexto("  Nombre completo: ");

        System.out.println();
        hospital.getArrayEspecialidades().mostrarNivelesTriage();
        int nivelTriage = leerEntero("  Nivel de triage (1-5): ");

        while (!hospital.getArrayEspecialidades().esNivelTriageValido(nivelTriage)) {
            System.out.println("  Nivel inválido. Debe ser entre 1 y 5");
            nivelTriage = leerEntero("  Nivel de triage (1-5): ");
        }

        Paciente paciente = new Paciente(numeroDocumento, nombre, nivelTriage);
        hospital.ingresarPaciente(paciente);
    }

    private static void asignarCama() {
        System.out.println("Asignar Cama a Paciente");

        hospital.asignarCamaPaciente();
    }

    private static void atenderPaciente() {
        System.out.println("Atender Paciente");

        String numeroDocumento = leerTexto("  Número de documento del paciente: ");

        Paciente paciente = hospital.buscarPaciente(numeroDocumento);
        if (paciente == null) {
            System.out.println("  Paciente no encontrado");
            return;
        }

        System.out.println("  Paciente encontrado: " + paciente.getNombre());
        System.out.println();

        hospital.getListaMedicos().mostrarAdelante();

        int indiceMedico = leerEntero("  Seleccione número de médico: ") - 1;
        String descripcion = leerTexto("  Diagnóstico/Descripción: ");

        hospital.atenderPaciente(numeroDocumento, indiceMedico, descripcion);
    }

    private static void buscarPaciente() {
        System.out.println("Buscar Paciente");

        String numeroDocumento = leerTexto("  Número de documento: ");

        Paciente paciente = hospital.buscarPaciente(numeroDocumento);
        if (paciente == null) {
            System.out.println("  Paciente no encontrado");
        } else {
            System.out.println("  " + paciente.toString().replace("", "  "));
        }
    }

    private static void darAltaPaciente() {
        System.out.println("Dar de Alta a Paciente");

        String numeroDocumento = leerTexto("  Número de documento: ");
        hospital.darAltaPaciente(numeroDocumento);
    }

    private static void mostrarColaEspera() {
        System.out.println("Cola de Espera");
        System.out.println();

        hospital.getColaEspera().mostrar();
    }

    private static void mostrarPacientesHospitalizados() {
        System.out.println("Pacientes Hospitalizados");
        System.out.println();

        hospital.getListaPacientesHospitalizados().mostrar();
    }

    private static void mostrarMatrizCamas() {
        System.out.println("Estado de las Camas");

        hospital.getMatrizCamas().mostrar();
    }

    private static void mostrarMedicos() {
        System.out.println("Médicos del Hospital");
        System.out.println();
        System.out.println("  Recorrido hacia adelante:");

        hospital.getListaMedicos().mostrarAdelante();

        System.out.println("  ¿Desea ver el recorrido hacia atrás? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("  Recorrido hacia atrás:");
            hospital.getListaMedicos().mostrarAtras();
        }
    }

    private static void mostrarHistorialAtenciones() {
        System.out.println("Historial de Atenciones");
        System.out.println();

        hospital.getPilaHistorial().mostrar();
    }

    private static void deshacerUltimaAtencion() {
        System.out.println("Deshacer Última Atención");

        hospital.deshacerUltimaAtencion();
    }

    private static void gestionarTurnos() {
        System.out.println("Gestión de Turnos Médicos");
        System.out.println();

        System.out.println("  Turnos disponibles:");
        hospital.getListaTurnos().mostrar();

        System.out.println("  ¿Desea avanzar al siguiente turno? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            hospital.avanzarTurno();
        }
    }

    private static void agregarMedico() {
        System.out.println("Agregar Nuevo Médico");

        String codigo = leerTexto("  Código del médico: ");
        String nombre = leerTexto("  Nombre completo: ");

        System.out.println();
        hospital.getArrayEspecialidades().mostrarEspecialidades();

        int indiceEspecialidad = leerEntero("  Seleccione especialidad: ") - 1;
        String especialidad = hospital.getArrayEspecialidades().getEspecialidad(indiceEspecialidad);

        if (especialidad == null) {
            System.out.println("  Especialidad inválida");
            return;
        }

        Medico medico = new Medico(codigo, nombre, especialidad);
        hospital.agregarMedico(medico);
    }

    private static void mostrarEspecialidades() {
        System.out.println("Especialidades y Niveles de Triage");

        hospital.getArrayEspecialidades().mostrarEspecialidades();
        hospital.getArrayEspecialidades().mostrarNivelesTriage();
    }

    private static void mostrarEstadisticas() {
        hospital.mostrarEstadisticas();
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("  Por favor, ingrese un número válido");
            }
        }
    }

    private static void guardarDatos() {
        System.out.println("Guardar Datos del Hospital");
        System.out.println();
        System.out.println("  Se guardará el estado actual del hospital en formato JSON.");
        System.out.print("  ¿Está seguro? (s/n): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            boolean exito = GestorPersistencia.guardarHospital(hospital);
            if (exito) {
                System.out.println("  Los datos se guardaron exitosamente.");
            } else {
                System.out.println("  Hubo un error al guardar los datos.");
            }
        } else {
            System.out.println("  Operación cancelada.");
        }
    }

    private static void cargarDatos() {
        System.out.println("Cargar Datos del Hospital");
        System.out.println();
        System.out.println("  Se cargará el estado guardado del hospital.");
        System.out.println("  ADVERTENCIA: Se perderán los datos actuales no guardados.");
        System.out.print("  ¿Está seguro? (s/n): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            boolean exito = GestorPersistencia.cargarHospital(hospital);
            if (!exito) {
                System.out.println("  No se pudieron cargar los datos.");
            }
        } else {
            System.out.println("  Operación cancelada.");
        }
    }

    private static void mostrarEstadisticasDetalladas() {
        hospital.mostrarEstadisticasDetalladas();
    }

    private static void menuReportes() {
        System.out.println("Reportes del Hospital");
        System.out.println();
        System.out.println("  1. Reporte completo del hospital");
        System.out.println("  2. Reporte de ocupación de camas");
        System.out.println("  3. Reporte de distribución por triage");
        System.out.println("  4. Ranking de médicos por productividad");
        System.out.println("  5. Médico más activo");
        System.out.println("  0. Volver al menú principal");
        System.out.println();

        int opcion = leerEntero("  Seleccione tipo de reporte: ");

        switch (opcion) {
            case 1:
                Reportes.reporteCompleto(hospital);
                break;
            case 2:
                Reportes.reporteOcupacionCamas(hospital);
                break;
            case 3:
                Reportes.reporteDistribucionTriage(hospital);
                break;
            case 4:
                Reportes.reporteMedicosPorProductividad(hospital);
                break;
            case 5:
                Reportes.reporteMedicoMasActivo(hospital);
                break;
            case 0:
                System.out.println("  Volviendo al menú principal...");
                break;
            default:
                System.out.println("  Opción inválida.");
        }
    }

    private static void menuSimulacion() {
        System.out.println("Simulación de Pacientes");
        System.out.println();
        System.out.println("  1. Generar pacientes aleatorios");
        System.out.println("  2. Simulación de flujo continuo");
        System.out.println("  3. Simulación de emergencia");
        System.out.println("  0. Volver al menú principal");
        System.out.println();

        int opcion = leerEntero("  Seleccione tipo de simulación: ");

        switch (opcion) {
            case 1:
                simularPacientesAleatorios();
                break;
            case 2:
                simularFlujoContinuo();
                break;
            case 3:
                simularEmergencia();
                break;
            case 0:
                System.out.println("  Volviendo al menú principal...");
                break;
            default:
                System.out.println("  Opción inválida.");
        }
    }

    private static void simularPacientesAleatorios() {
        System.out.println("Generar Pacientes Aleatorios");
        System.out.println();

        int cantidad = leerEntero("  ¿Cuántos pacientes desea generar? (1-50): ");

        if (cantidad < 1 || cantidad > 50) {
            System.out.println("  Cantidad inválida. Debe ser entre 1 y 50");
            return;
        }

        SimuladorPacientes.simularLlegadaPacientes(hospital, cantidad);
    }

    private static void simularFlujoContinuo() {
        System.out.println("Simulación de Flujo Continuo");
        System.out.println();

        int cantidad = leerEntero("  ¿Cuántos pacientes desea generar? (1-30): ");

        if (cantidad < 1 || cantidad > 30) {
            System.out.println("  Cantidad inválida. Debe ser entre 1 y 30");
            return;
        }

        System.out.print("  ¿Asignar camas automáticamente? (s/n): ");
        String respuesta = scanner.nextLine();
        boolean asignarAuto = respuesta.equalsIgnoreCase("s");

        SimuladorPacientes.simularFlujoContinuo(hospital, cantidad, asignarAuto);
    }

    private static void simularEmergencia() {
        System.out.println("Simulación de Emergencia");
        System.out.println();

        int cantidad = leerEntero("  ¿Cuántos pacientes críticos llegaron? (1-20): ");

        if (cantidad < 1 || cantidad > 20) {
            System.out.println("  Cantidad inválida. Debe ser entre 1 y 20");
            return;
        }

        SimuladorPacientes.simularEmergencia(hospital, cantidad);
    }
}
