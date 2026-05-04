# Sistema de Evaluación y Seguimiento del Bienestar Personal

## Descripción del Proyecto
Esta aplicación es un sistema de autoevaluación diseñado para ayudar a los usuarios a comprender mejor cómo reaccionan ante distintas situaciones de la vida cotidiana. A través de escenarios realistas, la app genera un perfil de comportamiento inicial enfocado en el manejo del estrés, la toma de decisiones y el estado emocional.

A diferencia de un test tradicional, esta herramienta permite el seguimiento a través de un historial de evaluaciones, facilitando una visión clara del progreso y cambios en el comportamiento a lo largo del tiempo.

## Tecnologías Utilizadas
- **Android Studio** (Koala o superior)
- **Kotlin**
- **Jetpack Compose** (Interfaz de usuario moderna y declarativa)
- **Jetpack Navigation (Type-Safe)** (Navegación robusta entre pantallas)
- **Kotlinx Serialization** (Para la gestión de rutas y datos)
- **Material Design 3** (Componentes visuales premium)

## Características Principales
- **Splash Screen Animada**: Entrada dinámica con animaciones de escala y opacidad.
- **Evaluación por Escenarios**: Preguntas basadas en situaciones de la vida real.
- **Resultados Personalizados**: Interpretación de datos según las respuestas.
- **Historial de Evolución**: Almacenamiento y visualización de evaluaciones pasadas.

## Instrucciones para Ejecutar la App
1.  Clonar el repositorio en tu máquina local.
2.  Abrir el proyecto en **Android Studio**.
3.  Sincronizar el proyecto con los archivos Gradle.
4.  Ejecutar la aplicación en un emulador o dispositivo físico con **Android 8.0 (API 26)** o superior.

## Principios de Programación Orientada a Objetos (POO) Aplicados
- **Encapsulamiento**: Gestión centralizada de datos a través de objetos Singleton (`HistoryManager`).
- **Modelado de Datos**: Uso de `data classes` para representar entidades del mundo real (`Scenario`, `Option`, `EvaluationResult`).
- **Abstracción**: Separación de la lógica de negocio de la interfaz de usuario.
