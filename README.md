# Ejercicio 1 – Pokédex con PokéAPI (Android)

## Descripción del proyecto

Este proyecto es una aplicación Android desarrollada en Kotlin que consume datos reales desde la PokéAPI para mostrar un listado paginado de Pokémon y una pantalla de detalle para cada uno.

La aplicación cumple con los requisitos del ejercicio E1:

- Consumo real de API REST
- Estados de carga, error y vacío
- Paginación (mínimo 3 páginas)
- Navegación entre listado y detalle
- Control de errores sin crash

---

## Funcionalidades

### Listado de Pokémon

- Consumo de:
  ```
  GET https://pokeapi.co/api/v2/pokemon?limit=&offset=
  ```
- Paginación implementada (3 páginas mínimas)
- Cada ítem muestra:
  - Imagen del Pokémon
  - Nombre (capitalizado)
  - ID visible
- Estados gestionados:
  - Loading
  - Error (con reintento)
  - Empty

---

### Pantalla de Detalle

Al pulsar sobre un Pokémon se navega a su detalle mostrando:

- Nombre
- ID
- Sprite oficial
- Tipos
- Altura
- Peso

También incluye:

- Estado de carga
- Manejo de error sin crash
- Opción de reintento

---

## Arquitectura

El proyecto sigue el patrón MVVM:

- Model
  - Modelos de datos (PokemonListResponse, PokemonDetailResponse)
- View
  - Fragments
  - Layouts XML
- ViewModel
  - PokemonListViewModel
  - PokemonDetailViewModel
- Repository
  - PokemonRepository
- Remote
  - RetrofitInstance
  - PokeApiService

---

## Tecnologías utilizadas

- Kotlin
- Android SDK
- Retrofit 2
- Moshi (JSON)
- Coroutines
- ViewModel + LiveData
- Navigation Component
- Glide (carga de imágenes)

---

## Permisos

La aplicación requiere acceso a internet:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

---

## Estructura principal

```
app/
 ├── data/
 │   ├── remote/
 │   └── PokemonRepository.kt
 │
 ├── ui/
 │   ├── list/
 │   ├── detail/
 │   └── common/
 │
 ├── res/
 │   ├── layout/
 │   └── navigation/
```

---

## Estado del proyecto

- Compila correctamente  
- Funciona en dispositivo físico  
- Cumple todos los requisitos del ejercicio  
- Manejo correcto de errores  
- Sin crashes  

---

## Objetivo académico

Este proyecto demuestra:

- Consumo de APIs REST en Android
- Arquitectura limpia y organizada
- Manejo de estados en UI
- Navegación entre pantallas
- Buenas prácticas en desarrollo Android moderno

---

Desarrollado como parte del ejercicio E1 – Pokédex con PokéAPI.
