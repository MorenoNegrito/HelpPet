🐾 HelpPet - Sistema de Gestión Veterinaria
📋 Descripción del Proyecto
HelpPet es una aplicación móvil Android desarrollada en Kotlin con Jetpack Compose que permite gestionar el registro de desarrolladores, mascotas, reservas de horas veterinarias y administración de usuarios. La aplicación implementa persistencia de sesión mediante DataStore y navegación fluida entre diferentes módulos.

👥 Equipo de Desarrollo
Equipo Fabian Morande, Dylan Medalla, Joaquin Iturriaga


✨ Funcionalidades Implementadas
🔐 Autenticación y Sesión

Registro de desarrolladores con validación de formularios
Persistencia de sesión con DataStore
Detección automática de sesión activa
Cierre de sesión con limpieza de datos

🏠 Pantalla Principal (Home)

Grid de navegación con 6 opciones principales
Tarjeta de bienvenida personalizada con datos del usuario
Navegación a diferentes módulos de la aplicación

🐕 Gestión de Mascotas

Visualización de lista de mascotas registradas
Información detallada (nombre, tipo, raza, edad)
Interfaz con LazyColumn para rendimiento óptimo
FloatingActionButton para agregar nuevas mascotas

📅 Gestión de Reservas

Lista de reservas de horas veterinarias
Estados de reservas (Confirmada, Pendiente, Cancelada)
Tarjeta de resumen con estadísticas
Botones de acción para confirmar/cancelar citas pendientes

👤 Perfil de Usuario

Visualización de información personal
Avatar circular personalizado
Navegación a configuración
Opción de cierre de sesión

⚙️ Configuración

Opciones de cuenta y preferencias
Información de la aplicación
Diálogo de confirmación para cerrar sesión

📊 Módulos Adicionales

Resumen de citas del usuario
Resumen de usuarios registrados en el sistema

🛠️ Tecnologías Utilizadas

Lenguaje: Kotlin
UI Framework: Jetpack Compose
Arquitectura: MVVM (Model-View-ViewModel)
Gestión de Estado: StateFlow
Persistencia: DataStore Preferences
Navegación: Navigation Compose
Inyección de Dependencias: Manual
Coroutines: Para operaciones asíncronas
Instrucciones

Clonar el repositorio:

bash   git clone https://github.com/MorenoNegrito/HelpPet.git
   cd HelpPet

Abrir el proyecto en Android Studio:

File → Open
Seleccionar la carpeta del proyecto
Esperar a que Gradle sincronice


Configurar el emulador:

Tools → Device Manager
Crear un nuevo dispositivo virtual (Pixel 5, API 34 recomendado)


Ejecutar la aplicación:

Click en el botón "Run" (▶️) o presionar Shift + F10
Seleccionar el dispositivo/emulador
Esperar a que la aplicación se instale y abra


Probar las funcionalidades:

Registrar un usuario con datos válidos
Navegar por las diferentes pantallas
Cerrar sesión y verificar persistencia al reabrir




📝 Notas de Desarrollo
Validaciones Implementadas

Nombre: Campo obligatorio
Correo: Debe contener "@"
Contraseña: Mínimo 6 caracteres
Dirección: Campo obligatorio
Términos: Debe aceptar términos y condiciones

Persistencia de Datos

Los datos del desarrollador se guardan en DataStore
La sesión persiste entre cierres de la aplicación
Solo se guardan nombre y correo por seguridad

Datos de Ejemplo

Las mascotas y reservas utilizan datos hardcodeados para demostración
En una versión productiva, se integraría con una base de datos (Room o Firebase)


🐛 Problemas Conocidos

Los datos de mascotas y reservas son estáticos (no se persisten)
No hay integración con backend real
La funcionalidad de agregar mascotas no está implementada


🔮 Mejoras Futuras

 Integración con base de datos Room
 Implementar agregar/editar/eliminar mascotas
 Sistema de notificaciones para recordatorios
 Modo oscuro
 Sincronización con backend (API REST)
 Carga de imágenes de mascotas


📄 Licencia
Este proyecto fue desarrollado con fines académicos para el curso de Desarrollo Móvil.
