# Comparativo de algoritmos de ordenamiento en Java

**Autor:** Daniel Flores
**Carrera:** Ciencias de la Computación, Universidad Politécnica Salesiana
**Asignatura:** [nombre de la asignatura]

## 1. Objetivo

Comparar el tiempo de ejecución de cuatro formas de ordenar arreglos de enteros (`int[]`) de 10 000, 50 000 y 100 000 elementos, usando un benchmark propio basado en una interfaz funcional y lambdas.

## 2. Métodos seleccionados

| Versión | Método | Descripción |
|---|---|---|
| V1 | Burbuja (`sortBubble`) | Variante optimizada: usa una bandera `swap` y termina si en una pasada no hubo intercambios. |
| V2 | Merge Sort (`sortMerge`) | Implementación propia top-down, estable, con arreglo auxiliar. Se explica en `INVESTIGACION.md`. |
| V3 | `Arrays.sort(int[])` (`sortLibrary`) | Método de la biblioteca, usado como referencia de rendimiento. |
| V4 | `Arrays.sort(Integer[], Comparator<Integer>)` (`sortLibraryComparator`) | Comparator definido con la lambda `(x, y) -> Integer.compare(x, y)`, sin restar valores. Requiere convertir `int[]` a `Integer[]` y de vuelta. |

## 3. Entorno

- Java: [versión, por ejemplo 17]
- Sistema operativo: [SO y versión]
- IDE / editor: [VS Code, IntelliJ, etc.]
- Procesador y RAM: [modelo / GB]

## 4. Compilación y ejecución

Desde la carpeta donde están los archivos `.java`:

```bash
javac *.java
java App
```

[Si usas carpeta `src`, ajusta los comandos a los reales, por ejemplo `javac -d out src/*.java` y `java -cp out App`.]

## 5. Organización de paquetes y responsabilidades de las clases

Todas las clases están en el paquete por defecto (sin `package`), en la misma carpeta.

| Clase | Responsabilidad |
|---|---|
| `App` | Genera las muestras y los clones, ejecuta las 12 mediciones, guarda los `Results` en una lista, imprime el resultado final y verifica que los arreglos queden ordenados. |
| `Metodos` | Contiene los cuatro métodos de ordenamiento (V1 a V4) y los auxiliares de Merge Sort. |
| `Generador` | Genera un `int[]` de tamaño `n` con números aleatorios entre 0 y `n`. |
| `Benchmark` | Mide el tiempo de una tarea recibida como `Runnable`. |
| `Results` | Guarda el nombre del método, el sample y el tiempo, y define cómo se imprime. |

## 6. Explicación del benchmarking

- **Interfaz funcional:** `Benchmark.medir` recibe un `Runnable`, que tiene un único método abstracto `run()`.
- **Envío mediante lambdas:** cada medición pasa la tarea como lambda, por ejemplo `() -> a.sortBubble(bubble1)`. Así el benchmark es independiente del algoritmo medido.
- **Cronómetro:** se toma `System.nanoTime()` justo antes de `tarea.run()` y otra vez justo después. El tiempo es la diferencia entre ambos.
- **Unidades:** internamente se guarda en nanosegundos (`long`). Al imprimir y en las tablas se muestra en milisegundos (`ns / 1 000 000`).
- **Significado de sample:** es el tamaño del arreglo medido (10000, 50000 o 100000), guardado como texto.
- **Tratamiento de errores:** si la tarea es `null` se lanza `IllegalArgumentException`. Si la tarea lanza una excepción, se captura, se imprime un mensaje y se devuelve un `Results` con tiempo `-1`, que se imprime como `ERROR`.
- **Operaciones excluidas del cronómetro:** la generación de la muestra, la creación de los clones, la verificación de orden y la impresión. Solo se mide la ejecución del algoritmo.
  - **Excepción en V4:** la conversión `int[]` a `Integer[]` y de vuelta va dentro del método y sí entra en el tiempo.
- **Almacenamiento e impresión:** los 12 objetos `Results` se guardan en una `List<Results>` y se imprimen al final recorriéndola, en el orden en que se ejecutaron.

## 7. Procedimiento y resultados

**Tamaños:** 10 000, 50 000 y 100 000.
**Métodos:** Burbuja, Merge Sort, `Arrays.sort`, `Arrays.sort` + Comparator.
**Orden de ejecución:** primero Burbuja (10 000, 50 000, 100 000), luego Merge Sort, luego `Arrays.sort` y por último `Arrays.sort` + Comparator.

**Procedimiento:**
1. Se genera **una sola muestra por tamaño** (`list1`, `list2`, `list3`).
2. Se crea un clon de cada muestra para cada método (`.clone()`), fuera del cronómetro. Así los cuatro métodos ordenan exactamente los mismos datos.
3. Se ejecutan las 12 mediciones y se imprimen los resultados.

### Tabla de resultados

Estructura: Burbuja | Muestra: 10000 | Tiempo: 86 ns
Estructura: Burbuja | Muestra: 50000 | Tiempo: 2460 ns
Estructura: Burbuja | Muestra: 100000 | Tiempo: 9464 ns

Estructura: Merge Sort | Muestra: 10000 | Tiempo: 1 ns
Estructura: Merge Sort | Muestra: 50000 | Tiempo: 5 ns
Estructura: Merge Sort | Muestra: 100000 | Tiempo: 6 ns

Estructura: Arrays.sort | Muestra: 10000 | Tiempo: 4 ns
Estructura: Arrays.sort | Muestra: 50000 | Tiempo: 3 ns
Estructura: Arrays.sort | Muestra: 100000 | Tiempo: 5 ns

Estructura: Arrays.sort + Comparator | Muestra: 10000 | Tiempo: 4 ns
Estructura: Arrays.sort + Comparator | Muestra: 50000 | Tiempo: 9 ns
Estructura: Arrays.sort + Comparator | Muestra: 100000 | Tiempo: 19 ns

### Captura de la consola

![Salida completa en consola](evidencias/1.png)


## 9. Conclusiones

1. Burbuja no escala bien porque al multiplicar por 10 el tamaño de la muestra, de 10000 a 100000 elementos, su tiempo crece cerca de 100 veces, mientras que Merge Sort crece un poco más de 10 veces.
2. El método de la biblioteca Arrays.sort es el más rápido porque está muy optimizado y trabaja directamente con datos primitivos, sin crear objetos.
3. Usar un Comparator tiene un costo extra, ya que Arrays.sort con Comparator es más lento que Arrays.sort normal por el paso de int a Integer y por la comparación objeto por objeto.