# Conecta 4 con IA (Negamax + Poda Alfa-Beta)

Implementación del juego Conecta 4 en Java con una Inteligencia Artificial basada en el algoritmo **Negamax optimizado con poda alfa-beta**, heurística avanzada y análisis de rendimiento.

---

## Características principales

* Juego completo en consola (jugador vs IA)
* IA basada en **Negamax + poda alfa-beta**
* Optimización mediante **ordenamiento de movimientos**
* Heurística avanzada:

  * Evaluación por ventanas (líneas de 4)
  * Priorización del centro del tablero
  * Bloqueo de amenazas del rival
* Métricas de rendimiento en tiempo real:

  * Nodos explorados
  * Profundidad alcanzada
  * Tiempo de decisión
* Simulación automática **IA vs IA**

---

## Algoritmo utilizado

La IA implementa **Negamax con poda alfa-beta**, una variante simplificada de Minimax.

* Reduce el número de nodos evaluados
* Mejora significativamente el rendimiento
* Permite explorar mayor profundidad en menos tiempo

---

## Configuración de dificultad

La dificultad se controla mediante la profundidad de búsqueda:

| Nivel   | Profundidad |
| ------- | ----------- |
| Fácil   | 3           |
| Medio   | 5           |
| Difícil | 8           |

---

## Ejemplo de salida

```text
Movimiento IA: columna 3
Nodos explorados: 8234
Profundidad alcanzada: 8
Tiempo: 215.34 ms
```

---

## Experimentos realizados

### IA vs IA (misma profundidad)

Configuración:

* IA vs IA
* Profundidad: 6
* 20 partidas

Resultados:

```text
IA1 (X) gana: 14
IA2 (O) gana: 6
Empates: 0
```

---

## Análisis

* Se observa una **ventaja clara del primer jugador (X)**
* Esto coincide con la teoría: Conecta 4 está resuelto y el primer jugador puede forzar victoria
* La ausencia de empates indica que:

  * La IA aún no juega de forma perfecta
  * Existen errores debido a la profundidad limitada y heurística

---

## Optimizaciones implementadas

### 1. Ordenamiento de movimientos

Se priorizan columnas centrales:

```java
{3,2,4,1,5,0,6}
```

✔ Mejora la poda alfa-beta
✔ Reduce significativamente los nodos explorados

---

### 2. Heurística mejorada

Evaluación basada en:

* Número de fichas propias en una ventana
* Espacios disponibles
* Presencia de fichas del rival

Ejemplo:

* 3 en línea + 1 espacio → alto valor
* Bloqueo de rival → penalización

---

## Posibles mejoras futuras

* Implementación de **tablas de transposición (memoización)**
* Uso de **iterative deepening**
* Interfaz gráfica (JavaFX)
* Exportación de estadísticas de partidas
* Paralelización de la búsqueda

---

## Tecnologías

* Java
* Programación orientada a objetos
* Algoritmos de búsqueda adversaria

---

## Conclusión

Este proyecto demuestra la implementación de técnicas clásicas de inteligencia artificial aplicadas a juegos, combinando:

* Optimización algorítmica
* Diseño de heurísticas
* Evaluación experimental

El resultado es una IA capaz de tomar decisiones eficientes en un entorno competitivo.

---

## Autor

Moisés Alejandro Paz Zamorano
GitHub: https://github.com/alepcito
