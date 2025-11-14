1. Descripción del Proyecto

Este proyecto implementa el videojuego clásico Ping Pong utilizando el lenguaje Jack, como parte de la asignatura Organización de Computadores en la Universidad EAFIT.
El desarrollo permite comprender cómo se integran los conceptos de arquitectura del sistema Hack, la máquina virtual, el manejo de gráficos y la programación orientada a objetos en un entorno de bajo nivel.
El juego incluye dos jugadores simultáneos, detección de colisiones, puntaje dinámico y actualización de pantalla en tiempo real.

2. Estructura del Código y Principales Componentes

El sistema se organizó siguiendo un diseño modular, compuesto por las siguientes clases:

Main.jack
Inicializa el juego y gestiona el ciclo principal de ejecución. Se encarga del menú inicial (cuando aplica) y de la creación del entorno gráfico.

Game.jack
Coordina los elementos principales: pelota, paletas y puntaje. Controla las reglas del juego, actualiza los estados y evalúa condiciones de victoria.

Ball.jack
Maneja la posición, dirección, velocidad y rebotes de la pelota. Contiene la lógica de colisiones con bordes y paletas.

Paddle.jack
Representa cada paleta y controla su movimiento vertical mediante entrada del usuario.

Score.jack
Lleva el registro del puntaje de ambos jugadores y lo muestra en pantalla mediante primitivas gráficas.

Este diseño facilita la lectura del código, la depuración y la reutilización de componentes.

3. Documentación Técnica (UML + Descripción)

El modelo conceptual del juego se resume en un diagrama UML sencillo (generado en PlantUML), con las clases principales y sus relaciones:

Main usa → Game

Game compone → Ball, Paddle, Score

Ball interactúa con → Paddle para detección de colisiones

Score es notificado por → Game para actualizar y dibujar puntaje

La interacción sigue un flujo cíclico:

Leer entrada del usuario

Actualizar posiciones

Detectar colisiones

Redibujar pantalla

Evaluar fin del punto o fin del juego

Este ciclo permite mantener el estado del juego hasta que un jugador alcance el puntaje límite definido.

4. Ejecución y Controles

Cómo ejecutar:

Compilar los archivos .jack con el Jack Compiler.

Cargar la carpeta del proyecto en el VM Emulator.

Ejecutar el programa desde Main.vm.

Controles:

Jugador 1: teclas asignadas para mover la paleta izquierda.

Jugador 2: teclas asignadas para mover la paleta derecha.
(Dependiendo de la implementación: flechas, WASD, etc.)

5. Conclusiones y Trabajo Realizado

Este proyecto permitió reforzar:

Programación orientada a objetos en Jack

Manejo de gráficos y memoria en el entorno Hack

Diseño modular mediante clases y responsabilidades claras

Integración de lógica, eventos y dibujo en un ciclo continuo

Comprensión de cómo un juego simple se ejecuta sobre una arquitectura construida desde puertas lógicas hasta un lenguaje de alto nivel

El código final funciona correctamente en el entorno Jack, cumple los requisitos funcionales y está documentado mediante estructura de clases y descripción técnica del flujo.
