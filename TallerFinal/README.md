# Sistema de Gestión de Hospital

## Información del proyecto

**Asignatura:** Estructura de datos
**Programa:** Ingeniería en Software
**Institución:** Tecnológico de Antioquia
**Semestre:** III
**Integrantes**:
- Luisa Mosquera
- Jhon Correa
- Juan Silva

## Descripción general

Este proyecto es un sistema completo de gestión hospitalaria que simula las operaciones de un hospital o servicio de urgencias. Fue desarrollado con el objetivo de demostrar la aplicación práctica de todas las estructuras de datos estudiadas durante el curso, incluyendo:

- Arrays
- Matrices
- Pilas (Stack)
- Colas (Queue)
- Listas simples
- Listas doblemente enlazadas
- Listas circulares
- Árboles binarios de búsqueda

El sistema permite gestionar pacientes, médicos, asignación de camas, turnos médicos, historial clínico y atención de urgencias, mostrando cómo cada estructura de datos puede aplicarse a problemas reales del mundo hospitalario.

## Estructura del proyecto

```
TallerFinal/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── hospital/
│   │   │           ├── Main.java
│   │   │           ├── Hospital.java
│   │   │           ├── entidades/
│   │   │           │   ├── Paciente.java
│   │   │           │   ├── Medico.java
│   │   │           │   ├── Turno.java
│   │   │           │   └── RegistroAtencion.java
│   │   │           └── estructuras/
│   │   │               ├── ArrayEspecialidades.java
│   │   │               ├── MatrizCamas.java
│   │   │               ├── Pila.java
│   │   │               ├── Cola.java
│   │   │               ├── ListaSimple.java
│   │   │               ├── ListaDoble.java
│   │   │               ├── ListaCircular.java
│   │   │               └── ArbolBinarioBusqueda.java
│   │   └── resources/
│   │       └── index.html
```

## Estructuras de datos implementadas

### 1. Array (ArrayEspecialidades.java)

**Ubicación en el código:** `com.hospital.estructuras.ArrayEspecialidades`

**Propósito:** Almacenar las especialidades médicas disponibles en el hospital y los niveles de triage.

**Implementación:**
```java
private String[] especialidades;
private String[] nivelesTriage;
```

**Uso en el sistema:**
- Almacena 8 especialidades médicas: Urgencias, Pediatría, Cirugía, Medicina Interna, Cardiología, Neurología, Traumatología y Ginecología
- Almacena 5 niveles de triage según el sistema de clasificación de urgencias:
  - Nivel 1: Resucitación (Crítico)
  - Nivel 2: Emergencia (Muy urgente)
  - Nivel 3: Urgente
  - Nivel 4: Menos urgente
  - Nivel 5: No urgente

**Operaciones principales:**
- `getEspecialidad(int indice)`: Obtiene una especialidad específica
- `getNivelTriage(int nivel)`: Obtiene la descripción de un nivel de triage
- `mostrarEspecialidades()`: Muestra todas las especialidades disponibles
- `esNivelTriageValido(int nivel)`: Valida que un nivel de triage sea correcto

**Por qué se usa un array:** Los arrays son perfectos para datos estáticos y de tamaño fijo como las especialidades médicas y niveles de triage, que no cambian durante la ejecución del programa.

---

### 2. Matriz (MatrizCamas.java)

**Ubicación en el código:** `com.hospital.estructuras.MatrizCamas`

**Propósito:** Representar visualmente las camas del hospital organizadas por pisos y posiciones.

**Implementación:**
```java
private int[][] matriz;
// Estados posibles: 0 = Libre, 1 = Ocupada, 2 = Mantenimiento
```

**Uso en el sistema:**
- Las filas representan los pisos del hospital
- Las columnas representan las camas en cada piso
- Cada celda contiene un valor que indica el estado de la cama:
  - 0: Cama libre (disponible)
  - 1: Cama ocupada (paciente asignado)
  - 2: Cama en mantenimiento (no disponible)

**Operaciones principales:**
- `asignarCama(int piso, int cama)`: Marca una cama como ocupada
- `liberarCama(int piso, int cama)`: Marca una cama como libre
- `buscarCamaLibre()`: Encuentra la primera cama disponible
- `contarCamasLibres()`: Cuenta cuántas camas están disponibles
- `mostrar()`: Visualiza el estado completo de todas las camas

**Ejemplo de visualización:**
```
      C0   C1   C2   C3   C4
      ---- ---- ---- ---- ----
  P0 |  L    O    L    M    O
  P1 |  O    L    L    O    L
  P2 |  L    L    O    L    M
```

**Por qué se usa una matriz:** Las matrices son ideales para representar datos organizados en dos dimensiones (pisos x camas), permitiendo acceso rápido y visualización clara del estado del hospital.

---

### 3. Pila / Stack (Pila.java)

**Ubicación en el código:** `com.hospital.estructuras.Pila`

**Propósito:** Guardar el historial de atenciones médicas siguiendo el principio LIFO (Last In, First Out).

**Implementación:**
```java
private class Nodo {
    T dato;
    Nodo siguiente;
}
private Nodo tope;
```

**Uso en el sistema:**
- Cada vez que un médico atiende a un paciente, se registra la atención
- El registro incluye: paciente, médico, diagnóstico y fecha/hora
- Permite "deshacer" la última atención registrada (útil para corregir errores)
- Mantiene un historial ordenado cronológicamente

**Operaciones principales:**
- `apilar(T dato)`: Agrega una nueva atención al historial
- `desapilar()`: Elimina y retorna la última atención (deshacer)
- `verTope()`: Consulta la última atención sin eliminarla
- `mostrar()`: Muestra todo el historial de atenciones

**Flujo de uso:**
1. Médico atiende paciente → se crea RegistroAtencion
2. RegistroAtencion se apila en el historial
3. Si se cometió un error, se puede desapilar para deshacer

**Por qué se usa una pila:** Las pilas son perfectas para implementar funcionalidad de "deshacer" ya que siempre acceden al elemento más reciente primero.

---

### 4. Cola de Prioridad (ColaPrioridad.java)

**Ubicación en el código:** `com.hospital.estructuras.ColaPrioridad`

**Propósito:** Gestionar la fila de pacientes esperando atención con priorización por nivel de triage.

**Implementación:**
```java
private class Nodo {
    Paciente paciente;
    int prioridad;        // Nivel de triage (1-5)
    long ordenLlegada;    // Para mantener FIFO en mismo nivel
    Nodo siguiente;
}
```

**Uso en el sistema:**
- Los pacientes se ordenan automáticamente por nivel de triage
- Nivel 1 (crítico) tiene prioridad sobre nivel 5 (no urgente)
- Si dos pacientes tienen el mismo nivel de triage, se respeta el orden de llegada (FIFO)
- Implementa correctamente el sistema de triage hospitalario

**Operaciones principales:**
- `encolar(Paciente)`: Inserta el paciente en la posición correcta según su prioridad
- `desencolar()`: Saca al paciente con mayor prioridad (menor nivel de triage)
- `verFrente()`: Consulta quién es el siguiente paciente sin sacarlo
- `mostrar()`: Muestra todos los pacientes ordenados por prioridad

**Ejemplo de funcionamiento:**
```
Llegan 3 pacientes:
1. Juan (Triage 5 - No urgente)
2. María (Triage 1 - Crítico)
3. Pedro (Triage 3 - Urgente)

Orden en cola de prioridad:
1. María (Triage 1) <- Se atiende primero
2. Pedro (Triage 3)
3. Juan (Triage 5) <- Se atiende último
```

**Flujo de uso:**
1. Paciente llega al hospital → se encola según su nivel de triage
2. Se libera una cama → se desencola el paciente más crítico
3. Paciente es asignado a una cama y pasa a hospitalizado

**Por qué se usa una cola de prioridad:** En un hospital real, los pacientes críticos deben atenderse antes que los menos urgentes, independientemente del orden de llegada. La cola de prioridad garantiza que los casos más graves se atiendan primero, cumpliendo con el protocolo de triage médico.

---

### 5. Lista Simple (ListaSimple.java)

**Ubicación en el código:** `com.hospital.estructuras.ListaSimple`

**Propósito:** Almacenar los pacientes actualmente hospitalizados con toda su información.

**Implementación:**
```java
private class Nodo {
    T dato;
    Nodo siguiente;
}
private Nodo cabeza;
```

**Uso en el sistema:**
- Contiene todos los pacientes que tienen una cama asignada
- Cada paciente incluye: documento, nombre, diagnóstico, cama asignada, médico asignado
- Permite recorrer todos los pacientes hospitalizados
- Se actualiza cuando un paciente es dado de alta

**Operaciones principales:**
- `agregar(T dato)`: Agrega un paciente hospitalizado
- `eliminar(int indice)`: Elimina un paciente (cuando es dado de alta)
- `obtener(int indice)`: Consulta información de un paciente específico
- `mostrar()`: Lista todos los pacientes hospitalizados

**Flujo de uso:**
1. Paciente sale de la cola → se le asigna cama
2. Paciente se agrega a lista de hospitalizados
3. Médico puede consultar la lista para ver quiénes están internados
4. Paciente es dado de alta → se elimina de la lista

**Por qué se usa una lista simple:** Las listas simples permiten crecimiento dinámico y son eficientes para agregar/eliminar elementos cuando solo necesitamos recorrido en una dirección.

---

### 6. Lista Doble (ListaDoble.java)

**Ubicación en el código:** `com.hospital.estructuras.ListaDoble`

**Propósito:** Registrar los médicos del hospital permitiendo recorrido bidireccional.

**Implementación:**
```java
private class Nodo {
    T dato;
    Nodo siguiente;
    Nodo anterior;
}
private Nodo cabeza;
private Nodo cola;
```

**Uso en el sistema:**
- Almacena todos los médicos con su código, nombre y especialidad
- Permite recorrer la lista hacia adelante y hacia atrás
- Útil para buscar médicos por especialidad
- Facilita la asignación de turnos

**Operaciones principales:**
- `agregar(T dato)`: Agrega un nuevo médico al hospital
- `obtener(int indice)`: Obtiene información de un médico
- `mostrarAdelante()`: Recorre los médicos de primero a último
- `mostrarAtras()`: Recorre los médicos de último a primero

**Ventajas del doble enlace:**
- Búsqueda más eficiente: puede buscar desde ambos extremos
- Navegación flexible: útil para interfaces que permiten "anterior/siguiente"
- Eliminación más rápida: no necesita recorrer desde el inicio para eliminar

**Por qué se usa una lista doble:** Las listas dobles son ideales cuando necesitamos recorrer datos en ambas direcciones, como cuando buscamos el médico anterior o siguiente disponible.

---

### 7. Lista Circular (ListaCircular.java)

**Ubicación en el código:** `com.hospital.estructuras.ListaCircular`

**Propósito:** Gestionar los turnos médicos que rotan continuamente.

**Implementación:**
```java
private class Nodo {
    T dato;
    Nodo siguiente; // El último nodo apunta al primero
}
private Nodo actual;
```

**Uso en el sistema:**
- Almacena los turnos: Mañana (06:00-14:00), Tarde (14:00-22:00), Noche (22:00-06:00)
- Al avanzar después del último turno, vuelve automáticamente al primero
- Simula la rotación continua de turnos en un hospital real
- Siempre hay un turno "actual" activo

**Operaciones principales:**
- `agregar(T dato)`: Agrega un nuevo turno a la rotación
- `siguiente()`: Avanza al siguiente turno (después de Noche vuelve a Mañana)
- `obtenerActual()`: Consulta cuál es el turno actual
- `mostrar()`: Muestra todos los turnos indicando cuál está activo

**Ejemplo de rotación:**
```
Turno 1: Mañana (06:00-14:00) <- Actual
Turno 2: Tarde (14:00-22:00)
Turno 3: Noche (22:00-06:00)

Después de siguiente():
Turno 1: Mañana (06:00-14:00)
Turno 2: Tarde (14:00-22:00) <- Actual
Turno 3: Noche (22:00-06:00)

Después de siguiente() x2:
Turno 1: Mañana (06:00-14:00) <- Actual (volvió al inicio)
```

**Por qué se usa una lista circular:** Las listas circulares son perfectas para datos que se repiten cíclicamente, como turnos, días de la semana, o procesos rotativos.

---

### 8. Árbol Binario de Búsqueda (ArbolBinarioBusqueda.java)

**Ubicación en el código:** `com.hospital.estructuras.ArbolBinarioBusqueda`

**Propósito:** Permitir búsqueda rápida de pacientes por número de documento.

**Implementación:**
```java
private class Nodo {
    String clave; // Número de documento
    Paciente paciente;
    Nodo izquierdo;
    Nodo derecho;
}
private Nodo raiz;
```

**Uso en el sistema:**
- Cada paciente se inserta usando su número de documento como clave
- La búsqueda es mucho más rápida que buscar en una lista (O(log n) vs O(n))
- Mantiene los pacientes ordenados por documento
- Permite encontrar rápidamente un paciente para atenderlo o darle de alta

**Operaciones principales:**
- `insertar(Paciente paciente)`: Agrega un paciente al árbol
- `buscar(String numeroDocumento)`: Encuentra un paciente rápidamente
- `eliminar(String numeroDocumento)`: Elimina un paciente del árbol
- `mostrarEnOrden()`: Muestra pacientes ordenados por documento

**Cómo funciona la búsqueda:**
1. Comparar documento buscado con el nodo actual
2. Si es menor, buscar en subárbol izquierdo
3. Si es mayor, buscar en subárbol derecho
4. Si es igual, paciente encontrado

**Ejemplo de estructura:**
```
        "1234567"
       /         \
  "1000123"    "5678901"
      /            \
"0987654"      "9999999"
```

**Complejidad temporal:**
- Búsqueda: O(log n) en promedio
- Inserción: O(log n) en promedio
- Eliminación: O(log n) en promedio

**Por qué se usa un árbol binario:** Los árboles binarios de búsqueda son la estructura más eficiente para búsquedas frecuentes cuando necesitamos encontrar elementos rápidamente por una clave única.

---

## Clases de entidades

### Paciente.java
Representa un paciente en el sistema con sus atributos:
- Número de documento (identificador único)
- Nombre completo
- Nivel de triage (1-5)
- Diagnóstico
- Cama asignada (piso y número)
- Médico asignado

### Medico.java
Representa un médico del hospital con:
- Código único
- Nombre completo
- Especialidad
- Estado de disponibilidad
- Contador de pacientes atendidos

### Turno.java
Representa un turno médico con:
- Nombre del turno
- Horario (inicio - fin)

### RegistroAtencion.java
Representa un registro de atención médica con:
- Documento del paciente
- Nombre del paciente
- Nombre del médico
- Descripción/diagnóstico
- Fecha y hora de la atención

---

## Clase principal: Hospital.java

La clase `Hospital` es el núcleo del sistema y orquesta todas las estructuras de datos:

### Atributos principales

```java
private ArrayEspecialidades arrayEspecialidades;
private MatrizCamas matrizCamas;
private Pila<RegistroAtencion> pilaHistorial;
private Cola<Paciente> colaEspera;
private ListaSimple<Paciente> listaPacientesHospitalizados;
private ListaDoble<Medico> listaMedicos;
private ListaCircular<Turno> listaTurnos;
private ArbolBinarioBusqueda arbolPacientes;
```

### Métodos principales

#### ingresarPaciente(Paciente paciente)
- Agrega un paciente a la cola de espera
- Muestra el nivel de triage asignado
- **Estructura usada:** Cola

#### asignarCamaPaciente()
- Busca una cama libre en la matriz
- Desencola el primer paciente de la cola
- Asigna la cama al paciente
- Agrega el paciente a la lista de hospitalizados
- Inserta el paciente en el árbol para búsquedas rápidas
- **Estructuras usadas:** Cola, Matriz, Lista Simple, Árbol Binario

#### atenderPaciente(String documento, int medico, String diagnostico)
- Busca el paciente en el árbol
- Asigna el médico de la lista doble
- Actualiza el diagnóstico del paciente
- Crea un registro y lo apila en el historial
- **Estructuras usadas:** Árbol Binario, Lista Doble, Pila

#### deshacerUltimaAtencion()
- Desapila la última atención del historial
- Útil para corregir errores de registro
- **Estructura usada:** Pila

#### buscarPaciente(String documento)
- Busca rápidamente un paciente por documento
- **Estructura usada:** Árbol Binario

#### darAltaPaciente(String documento)
- Busca el paciente en el árbol
- Libera su cama en la matriz
- Elimina el paciente de la lista de hospitalizados
- Elimina el paciente del árbol
- **Estructuras usadas:** Árbol Binario, Matriz, Lista Simple

#### avanzarTurno()
- Avanza al siguiente turno en la lista circular
- **Estructura usada:** Lista Circular

---

## Flujo completo del sistema

### Caso de uso: Ingreso y atención de un paciente

1. **Llega un paciente al hospital**
   - Se crea objeto `Paciente` con sus datos
   - Se asigna nivel de triage usando el `Array` de niveles
   - Se encola en `Cola` de espera

2. **Se libera una cama**
   - Sistema busca cama libre en `Matriz`
   - Se desencola el primer paciente de la `Cola`
   - Se marca la cama como ocupada en la `Matriz`
   - Se agrega paciente a `Lista Simple` de hospitalizados
   - Se inserta paciente en `Árbol Binario` para búsquedas

3. **Médico atiende al paciente**
   - Se busca paciente en `Árbol Binario` (búsqueda rápida)
   - Se selecciona médico de `Lista Doble`
   - Se actualiza información del paciente
   - Se crea `RegistroAtencion` y se apila en `Pila` de historial

4. **Paciente es dado de alta**
   - Se busca paciente en `Árbol Binario`
   - Se libera cama en `Matriz`
   - Se elimina de `Lista Simple` de hospitalizados
   - Se elimina del `Árbol Binario`

5. **Cambio de turno**
   - Se avanza en `Lista Circular` de turnos
   - El sistema vuelve al primer turno automáticamente

---

## Interfaz de usuario (Main.java)

El sistema incluye una interfaz de consola interactiva con 20 opciones principales más submenús:

### Menú principal

```
1.  Ingresar nuevo paciente
2.  Asignar cama a paciente en espera
3.  Atender paciente
4.  Buscar paciente
5.  Dar de alta a paciente
6.  Ver cola de espera (ordenada por prioridad)
7.  Ver pacientes hospitalizados
8.  Ver estado de camas (matriz visual)
9.  Ver médicos disponibles
10. Ver historial de atenciones (pila)
11. Deshacer última atención
12. Gestionar turnos médicos (lista circular)
13. Agregar nuevo médico
14. Ver especialidades y niveles de triage (array)
15. Ver estadísticas del hospital
16. Guardar datos en archivo JSON
17. Cargar datos desde archivo JSON
18. Ver estadísticas detalladas ← NUEVO
19. Generar reportes completos ← NUEVO
20. Simulación de pacientes ← NUEVO
0.  Salir
```

### Características de la interfaz

- **Menús claros y organizados** con márgenes adecuados
- **Validación de entrada** para números y textos
- **Mensajes informativos** que guían al usuario
- **Visualización estructurada** de la matriz de camas
- **Formato consistente** sin mayúsculas sostenidas

---

## Visualización HTML

El proyecto incluye un archivo `index.html` responsive que documenta:

- Descripción general del proyecto
- Todas las estructuras implementadas
- Funcionalidades principales
- Tecnologías utilizadas
- Instrucciones de ejecución

**Características:**
- Diseño moderno con gradientes
- Cards interactivas para cada estructura
- Responsive design para móviles
- Colores corporativos (púrpura/violeta)

---

## Instalación y ejecución

### Requisitos previos

- Java JDK 11 o superior
- Maven 3.6 o superior

### Compilación

```bash
mvn clean compile
```

### Ejecución

```bash
mvn exec:java -Dexec.mainClass="com.hospital.Main"
```

### Empaquetado

```bash
mvn package
```

Esto genera un archivo JAR en `target/sistema-gestion-hospital-1.0-SNAPSHOT.jar`

### Ejecución del JAR

```bash
java -jar target/sistema-gestion-hospital-1.0-SNAPSHOT.jar
```

---

## Decisiones de diseño

### ¿Por qué implementación manual?

Todas las estructuras de datos fueron implementadas manualmente sin usar las colecciones de Java (`ArrayList`, `Stack`, `LinkedList`, etc.) por las siguientes razones:

1. **Propósito educativo:** Demostrar comprensión profunda de cómo funcionan internamente
2. **Cumplimiento de requisitos:** El proyecto requiere implementación manual
3. **Control total:** Permite personalizar operaciones específicas del dominio
4. **Aprendizaje:** Refuerza conceptos de punteros, nodos y algoritmos

### Ventajas de la implementación manual

- **Transparencia:** Se puede ver exactamente cómo se insertan, eliminan y buscan elementos
- **Personalización:** Métodos adaptados a las necesidades del hospital
- **Rendimiento:** Optimizaciones específicas para nuestro caso de uso
- **Documentación:** Código bien comentado que sirve como material de estudio

### Desventajas y cómo se mitigaron

- **Más código:** Se compensó con organización clara en paquetes
- **Posibles bugs:** Se agregaron validaciones exhaustivas
- **Sin métodos avanzados:** Se implementaron solo los necesarios para el sistema

---

## Características técnicas

### Complejidad temporal de operaciones

| Estructura | Inserción | Búsqueda | Eliminación |
|------------|-----------|----------|-------------|
| Array | O(1) | O(n) | O(n) |
| Matriz | O(1) | O(1) | O(1) |
| Pila | O(1) | - | O(1) |
| Cola | O(1) | - | O(1) |
| Lista Simple | O(n) | O(n) | O(n) |
| Lista Doble | O(n) | O(n) | O(n) |
| Lista Circular | O(n) | O(n) | O(n) |
| Árbol Binario | O(log n) | O(log n) | O(log n) |

### Gestión de memoria

- Uso de referencias (punteros) para estructuras dinámicas
- Liberación automática con garbage collector de Java
- Sin memory leaks por diseño orientado a objetos

### Validaciones implementadas

- Validación de niveles de triage (1-5)
- Validación de índices en todas las estructuras
- Validación de camas disponibles antes de asignar
- Validación de existencia de pacientes antes de operar
- Manejo de casos de estructuras vacías

---

## Pruebas sugeridas

### Escenario 1: Flujo completo

1. Ingresar 5 pacientes con diferentes niveles de triage
2. Asignar camas a 3 de ellos
3. Atender 2 pacientes con diferentes médicos
4. Buscar un paciente específico
5. Ver estado de camas
6. Ver historial de atenciones
7. Dar de alta a un paciente
8. Verificar que la cama quedó libre

### Escenario 2: Gestión de turnos

1. Ver turno actual
2. Avanzar turno 3 veces
3. Verificar que volvió al turno inicial (circular)

### Escenario 3: Deshacer atención

1. Atender un paciente
2. Ver historial
3. Deshacer última atención
4. Verificar que desapareció del historial

### Escenario 4: Búsqueda en árbol

1. Agregar 10 pacientes
2. Buscar por documento (debe ser inmediato)
3. Comparar con búsqueda lineal en lista

---

## Funcionalidades implementadas

### Características principales

✅ **Cola de prioridad por triage:** Implementada completamente. Los pacientes se ordenan automáticamente por nivel de urgencia (1-5), y si tienen el mismo nivel, se respeta el orden de llegada.

✅ **Persistencia de datos en JSON:** El sistema puede guardar y cargar el estado completo del hospital usando la librería Gson:
- Pacientes en cola de espera (con su prioridad)
- Pacientes hospitalizados
- Estado de todas las camas (libre/ocupada/mantenimiento)
- Médicos y sus estadísticas
- Total de pacientes atendidos

**Cómo usar la persistencia:**
- Opción 16: Guardar datos → Crea archivo `datos_hospital.json`
- Opción 17: Cargar datos → Restaura el estado guardado
- Al iniciar, pregunta si desea cargar datos anteriores

✅ **Reportes completos del sistema (Opción 19):**
1. **Reporte completo:** Consolidado de toda la información del hospital
2. **Reporte de ocupación de camas:**
   - Porcentajes de ocupación total y por piso
   - Indicadores visuales (Baja/Media/Alta/Crítica)
   - Distribución por estado (libre/ocupada/mantenimiento)
3. **Reporte de distribución por triage:**
   - Cantidad y porcentaje de pacientes por nivel
   - Gráficos de barras ASCII para visualización
4. **Ranking de médicos por productividad:**
   - Médicos ordenados por pacientes atendidos
   - Permite identificar al personal más activo
5. **Médico más activo:** Identifica al médico con más atenciones

✅ **Módulo de simulación de pacientes (Opción 20):**
1. **Generar pacientes aleatorios:**
   - Crea hasta 50 pacientes con nombres y documentos aleatorios
   - Distribución realista de niveles de triage (5% crítico, 15% muy urgente, etc.)
2. **Simulación de flujo continuo:**
   - Genera pacientes y opcionalmente asigna camas automáticamente
   - Simula el funcionamiento real del hospital
   - Permite ver cómo se llena la capacidad
3. **Simulación de emergencia:**
   - Genera pacientes críticos (nivel 1 y 2)
   - Simula situaciones de emergencia masiva
   - Prueba la capacidad del sistema de priorización

✅ **Estadísticas detalladas (Opción 18):**
- Tasa de ocupación con porcentajes
- Promedios de atenciones por médico
- Identificación automática del médico más activo
- Total de pacientes en el sistema
- Métricas de rendimiento del hospital

### Posibles mejoras futuras

- **Reportes avanzados:** Generar reportes en PDF con gráficos de ocupación
- **Interfaz gráfica:** Migrar a JavaFX o Swing para GUI completa
- **Sistema de citas:** Agregar agenda de citas programadas
- **Histórico completo:** Ver todas las atenciones de un paciente específico

### Optimizaciones técnicas posibles

- **Árbol AVL:** Balancear el árbol binario para garantizar O(log n)
- **Hash Table:** Para búsquedas aún más rápidas de pacientes
- **Multithreading:** Simular múltiples médicos atendiendo simultáneamente

---

## Conclusiones

Este proyecto demuestra exitosamente:

1. **Aplicación práctica:** Todas las estructuras de datos tienen uso real en el sistema
2. **Integración:** Las estructuras trabajan juntas de forma cohesiva
3. **Diseño orientado a objetos:** Código organizado, reutilizable y mantenible
4. **Implementación completa:** Desde estructuras básicas hasta sistema funcional
5. **Documentación exhaustiva:** Código comentado y README detallado

El Sistema de Gestión de Hospital es un ejemplo completo de cómo las estructuras de datos fundamentales pueden combinarse para resolver problemas complejos del mundo real, mostrando la importancia de elegir la estructura correcta para cada tipo de operación.

---

## Referencias

- Material del curso de Estructura de Datos
- Documentación oficial de Java SE 11
- Algoritmos y estructuras de datos clásicas
- Sistemas de triage hospitalario internacional

---

## Autor

Proyecto desarrollado como trabajo final para la asignatura de Estructura de Datos del programa de Ingeniería en Software del Tecnológico de Antioquia.

**Todas las estructuras de datos fueron implementadas manualmente sin utilizar las colecciones de Java.**
