# 📊 Finanzas App

![Kotlin](https://img.shields.io/badge/Kotlin-2.0+-blue.svg?logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Canvas-4285F4.svg?logo=android)
![Animations](https://img.shields.io/badge/Animations-Transitions-success.svg)

Aplicación financiera nativa para Android enfocada en la creación de **gráficos personalizados de alto rendimiento** utilizando exclusivamente la API de Canvas de Jetpack Compose, sin dependencias de bibliotecas de terceros para la visualización de datos.

## 📱 Capturas de pantalla y video

| Pantalla 1 | Pantalla 2 | Pantalla 3 |
| :---: | :---: | :---: |
| <img src="/assets/scr01.jpeg" width="250"/> | <img src="/assets/scr02.jpeg" width="250"/> | <img src="/assets/scr03.jpeg" width="250"/> |

| Video |
| :---: | 
| <img src="/assets/vdo01.gif" width="250"/> |

## ✨ Características Principales

* **Gráfico de Anillo Animado (Donut Chart):** Renderizado matemático de arcos (`drawArc`) con proporciones exactas e interpolación angular suave de 0 a 360 grados.
* **Curvas de Bézier (Line Chart):** Dibujo de tendencias históricas utilizando `Path` y `cubicTo` para líneas fluidas, incluyendo sombreado de gradiente (`Brush`) y revelado direccional (`clipRect`).
* **Gráfico de Barras Dinámico (Bar Chart):** Cálculo algorítmico de anchos y espaciados dinámicos con animación de crecimiento vertical independiente.
* **Navegación Personalizada:** Barra de pestañas interactiva construida desde cero con efectos de expansión (`animateContentSize`) y semántica accesible.

## 🛠️ Stack Tecnológico

* **Lenguaje:** [Kotlin](https://kotlinlang.org/)
* **UI Toolkit & Dibujo:** [Jetpack Compose (Canvas API)](https://developer.android.com/jetpack/compose/graphics/draw/overview)
* **Animaciones:** `animateFloatAsState`, `rememberTransition`, `tween`.
* **Arquitectura:** Componentes UI aislados y UDF (Unidirectional Data Flow).

## 🚀 Instalación y Configuración

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/JHugoVelarde/finanzas.git)
   ```

2. Ejecuta el proyecto:
- Abre el proyecto en Android Studio.
- Sincroniza Gradle (File > Sync Project with Gradle Files).
- Despliega en tu emulador o dispositivo físico para visualizar las animaciones a 60 FPS.

## 📂 Estructura del Proyecto

```
com.example.finanzas
│
├── data/                   # Simulación de datos financieros
│   └── DatosFinancieros.kt # Modelos y Mock Data (Cuentas, Gastos)
│
└── ui/                     # Capa de presentación (Compose)
    ├── FinanzasApp.kt      # Contenedor principal y Scaffold
    ├── components/         # Motor de gráficos (Canvas)
    │   ├── AnimatedDonutChart.kt
    │   ├── AnimatedLineChart.kt
    │   ├── AnimatedBarChart.kt
    │   └── FinanzasTabBar.kt
    ├── screens/            # Pantallas ensambladas
    │   ├── FinanzasScreen.kt
    │   ├── AccountsScreen.kt
    │   └── BillsScreen.kt
    └── theme/              # Paleta de colores institucionales y tipografía
```

## 💡 Lecciones Clave de Arquitectura y UI

- **Matemáticas para UI (Canvas):** Uso avanzado de geometría y cálculo de proporciones para renderizar arcos (`drawArc`) y curvas de Bézier (`cubicTo`) adaptables a cualquier tamaño de pantalla, eliminando la dependencia de librerías de terceros (Zero-Dependency Charts).
- **Optimización de Recomposiciones:** Implementación de cálculos eficientes en memoria (ej. reemplazar `map{}.sum()` por `sumOf{}`) y manejo preciso del estado de las animaciones utilizando `rememberTransition` y `LaunchedEffect` para garantizar 60 FPS consistentes.
- **Sinergia Insets & Edge-to-Edge:** Dominio completo del área de dibujado nativa mediante la configuración de barras de sistema transparentes (`SystemBarStyle.dark`) combinadas estratégicamente con `Modifier.safeDrawingPadding()`, logrando una experiencia visual inmersiva de borde a borde.

## 📄 Licencia
Este proyecto está licenciado bajo la Licencia Apache 2.0 - consulta el archivo [LICENSE](LICENSE) para más detalles.
