# EstructuraDeDatosUrgencias
Desarrollar una aplicación que simule la gestión de un hospital o servicio de urgencias, aplicando de manera integrada las estructuras de datos vistas durante el curso: arrays, matrices, pilas, colas, listas simples, dobles, circulares y árboles.
El sistema debe permitir manejar pacientes, médicos, áreas de atención, historial clínico y turnos, mostrando el uso práctico de cada estructura.

# Participantes
Jhon Jander Correa Montoya
Luisa Fernanda Mosquera Mosquera
Juan David Silva Uribe

# Tener en cuenta: 

Estructura	Uso dentro del sistema hospitalario
Array	Almacenar los tipos de especialidades médicas (Urgencias, Pediatría, Cirugía, Medicina Interna, etc.) o niveles de triage (1 a 5).
Matriz	Representar las camas o habitaciones del hospital (filas = pisos o áreas, columnas = camas). Cada celda indica si está libre, ocupada o en mantenimiento.
Pila (Stack)	Guardar el historial de atención médica (últimos pacientes atendidos). Permitir “Deshacer última atención”.
Cola (Queue)	Manejar la fila de pacientes que esperan ser atendidos en urgencias, priorizados por orden de llegada o triage.
Lista Simple	Almacenar los pacientes actualmente hospitalizados con su información básica (ID, nombre, diagnóstico, cama asignada).
Lista Doble	Registrar los médicos del hospital, permitiendo recorrerlos hacia adelante y hacia atrás (útil para asignación de turnos o búsqueda por especialidad).
Lista Circular	Almacenar los turnos médicos (día-noche, semana actual). Cuando termina la lista, vuelve al primer turno automáticamente.
Árbol Binario de Búsqueda (BST)	Permitir la búsqueda rápida de pacientes por número de identificación o historia clínica.
________________________________________
Requerimientos Funcionales
1.	Ingreso de paciente:
o	Se registra en la cola de espera (por orden o prioridad de triage).
o	Si hay disponibilidad, se asigna cama (usando la matriz).
o	Se mueve a la lista simple de hospitalizados.
2.	Asignación de médico:
o	Se elige de la lista doble de médicos.
o	Se puede recorrer la lista hacia adelante o atrás para encontrar el siguiente disponible.
3.	Atención médica:
o	Al atender un paciente, se registra la acción en la pila de historial.
o	Permitir “Deshacer última atención” (sacar el último elemento de la pila).
4.	Búsqueda de pacientes:
o	Implementada con un árbol binario, usando como clave el número de documento o código de historia clínica.
5.	Gestión de turnos médicos:
o	Usar una lista circular para simular el cambio de turno (al avanzar en la lista, regresa al primero automáticamente).
6.	Estado del hospital:
o	Mostrar la matriz con camas disponibles, ocupadas o en mantenimiento.
o	Mostrar los pacientes actualmente hospitalizados (lista simple).
Extras (Opcional, tiene bonos)
•	Interfaz de consola con menús claros o GUI simple (Java Swing).
•	Persistencia de datos (guardar en archivos .txt o JSON).
•	Reportes: total de pacientes atendidos, ocupación de camas, médico con más pacientes, etc.
•	Módulo de simulación de llegada de pacientes cada cierto tiempo (uso de colas dinámicas).
Criterios de Evaluación
Criterio	Ponderación
Implementación correcta de cada estructura	30%
Integración lógica entre las estructuras	20%
Funcionalidad completa del sistema	25%
Documentación y legibilidad del código	15%
Presentación y demostración del proyecto	10%

 Entrega
•	Código fuente (comentado y organizado).
•	Documento técnico o diagrama que muestre cómo se usó cada estructura. mostrar (y explicar) en qué parte del sistema utilizaste cada estructura de datos que aprendiste en el curso (arrays, matrices, pilas, colas, listas, árboles, etc.). Recuerden incluir portada
•	Video corto (máx. 5 minutos) mostrando el funcionamiento.

Sugerencias Técnicas
•	Usa clases para representar entidades: Paciente, Medico, Cama, Turno, Hospital.
•	Crea módulos separados para cada estructura de datos.
•	Aplica operaciones básicas: inserción, eliminación, búsqueda y recorrido.
•	Evita usar estructuras listas del lenguaje (como ArrayList o Stack de Java); implementa las estructuras manualmente.

Ejemplo de Flujo del Sistema
1.	Llega un paciente → se agrega a la cola de espera.
2.	Se libera una cama → se toma el paciente de la cola y se asigna cama (matriz) → se agrega a la lista simple.
3.	Se asigna un médico (lista doble) → se registra la atención (pila).
4.	Se guarda el paciente en el árbol binario para búsquedas rápidas.
5.	Se rota el turno médico (lista circular).
