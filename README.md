# Conecta 4 con Inteligencia Artificial (Negamax + Alpha-Beta)

## Descripción

Este proyecto implementa el juego de **Conecta 4** junto con una **Inteligencia Artificial** capaz de tomar decisiones estratégicas utilizando:

* **Negamax**
* **Poda Alfa-Beta**
* Función heurística de evaluación de estados

El objetivo es simular un oponente competitivo capaz de anticipar jugadas y maximizar sus probabilidades de victoria.

---

## Objetivo del proyecto

Desarrollar una IA eficiente para un juego de tablero clásico, aplicando algoritmos de búsqueda en árboles de decisión y optimización mediante poda.

---

## Algoritmos implementados

### 🔹 Negamax

Variante simplificada de Minimax que aprovecha la simetría del juego:

```id="xk1z7k"
score = -negamax(hijo)
```

Permite implementar la lógica de decisión con menos código y mayor claridad.

---

### 🔹 Poda Alfa-Beta

Optimización del algoritmo de búsqueda que evita explorar ramas innecesarias del árbol:

* Reduce drásticamente el número de nodos evaluados
* Permite alcanzar mayor profundidad de búsqueda

---

## Funcionamiento de la IA

Para cada jugada posible:

1. Genera los estados hijos
2. Evalúa recursivamente el árbol de juego
3. Aplica poda alfa-beta para optimizar
4. Selecciona la jugada con mejor puntuación

---

## Heurística de evaluación

La IA utiliza una función heurística para evaluar posiciones no terminales, considerando:

* Posibles líneas de 2, 3 y 4 en raya
* Control del centro del tablero
* Bloqueo de jugadas del oponente

---

## Complejidad

El espacio de búsqueda en Conecta 4 es grande, por lo que:

* Sin poda → crecimiento exponencial
* Con alfa-beta → reducción significativa

Esto permite alcanzar profundidades de búsqueda mayores sin comprometer el rendimiento.

---

## Ejemplo de ejecución

```id="k9qzqv"
Jugador vs IA

Turno IA:
Analizando jugadas...

IA elige columna 3
```

---

## Tecnologías utilizadas

* Java
* Estructuras de datos (árboles implícitos)
* Algoritmos de búsqueda

---

## Aprendizajes

* Implementación de algoritmos de IA clásicos
* Optimización de búsqueda mediante poda
* Diseño de funciones heurísticas
* Manejo de árboles de decisión

---

## Posibles mejoras

* Ajuste fino de la heurística
* Uso de tablas de transposición
* Paralelización del cálculo
* Interfaz gráfica más avanzada
* Implementación de dificultad variable

---

## Cómo ejecutar

1. Compilar:

```bash id="7k8x0q"
javac *.java
```

2. Ejecutar:

```bash id="m92sld"
java Main
```

---

## Autor

Moisés Alejandro Paz Zamorano
GitHub: https://github.com/alepcito
