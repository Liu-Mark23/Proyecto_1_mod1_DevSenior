import java.util.Scanner;

public class App {

    // Variables estáticas y privadas
    private static String nombreEstudiante = "N/A";
    private static double nota1 = 0;
    private static double nota2 = 0;
    private static double nota3 = 0;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int opcion = -1;

        // 👋 Saludo inicial
        System.out.println("🎓 ¡Bienvenidos estudiantes! Hellou 😄\n");

        while (opcion != 0) {
            mostrarMenu();
            System.out.print("Ingrese su opción: ");

            if (keyboard.hasNextInt()) {
                opcion = keyboard.nextInt();
                keyboard.nextLine(); // Limpiar enter pendiente

                switch (opcion) {
                    case 1:
                        confirmarYRegistrarEstudiante(keyboard);
                        break;
                    case 2:
                        mostrarInfoEstudiante();
                        break;
                    case 3:
                        mostrarPromedioYEstado();
                        break;
                    case 4:
                        limpiarDatos();
                        break;
                    case 0:
                        System.out.println("👋 ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("❌ Opción no válida.");
                }
            } else {
                System.out.println("❌ Entrada inválida. Debes ingresar un número.");
                keyboard.nextLine(); // Limpiar entrada no válida
            }

            System.out.println(); // Espacio visual
        }

        keyboard.close();
    }

    private static void mostrarMenu() {
        System.out.println("----- MENÚ -----");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Mostrar datos del estudiante");
        System.out.println("3. Calcular promedio y estado");
        System.out.println("4. Borrar datos del estudiante");
        System.out.println("0. Salir");
    }

    private static void confirmarYRegistrarEstudiante(Scanner keyboard) {
        if (!nombreEstudiante.equals("N/A")) {
            System.out.print("Ya hay un estudiante registrado. ¿Deseas sobrescribirlo? (s/n): ");
            String respuesta = keyboard.nextLine().strip().toLowerCase();
            if (!respuesta.equals("s")) {
                System.out.println("🚫 Registro cancelado.");
                return;
            }
        }

        registrarEstudiante(keyboard);
    }

    private static void registrarEstudiante(Scanner keyboard) {
        // VALIDACIÓN DEL NOMBRE
        String nameStudent;
        boolean nombreEsValido = false;

        while (!nombreEsValido) {
            System.out.print("Ingrese el nombre del estudiante: ");
            nameStudent = keyboard.nextLine().strip();

            if (!validarNombre(nameStudent)) {
                System.out.println("❌ Nombre inválido. No puede estar vacío. Inténtalo de nuevo.");
            } else {
                nombreEstudiante = nameStudent;
                nombreEsValido = true;
            }
        }

        // NOTA 1
        boolean nota1Valida = false;
        while (!nota1Valida) {
            System.out.print("Ingrese la nota 1 (0-100): ");
            String input = keyboard.nextLine().strip();

            if (esNumeroValido(input)) {
                double valor = Double.parseDouble(input);
                if (validarNota(valor)) {
                    nota1 = valor;
                    nota1Valida = true;
                } else {
                    System.out.println("❌ La nota debe estar entre 0 y 100.");
                }
            } else {
                System.out.println("❌ Entrada no válida. Debes escribir un número.");
            }
        }

        // NOTA 2
        boolean nota2Valida = false;
        while (!nota2Valida) {
            System.out.print("Ingrese la nota 2 (0-100): ");
            String input = keyboard.nextLine().strip();

            if (esNumeroValido(input)) {
                double valor = Double.parseDouble(input);
                if (validarNota(valor)) {
                    nota2 = valor;
                    nota2Valida = true;
                } else {
                    System.out.println("❌ La nota debe estar entre 0 y 100.");
                }
            } else {
                System.out.println("❌ Entrada no válida. Debes escribir un número.");
            }
        }

        // NOTA 3
        boolean nota3Valida = false;
        while (!nota3Valida) {
            System.out.print("Ingrese la nota 3 (0-100): ");
            String input = keyboard.nextLine().strip();

            if (esNumeroValido(input)) {
                double valor = Double.parseDouble(input);
                if (validarNota(valor)) {
                    nota3 = valor;
                    nota3Valida = true;
                } else {
                    System.out.println("❌ La nota debe estar entre 0 y 100.");
                }
            } else {
                System.out.println("❌ Entrada no válida. Debes escribir un número.");
            }
        }

        System.out.println("✅ Estudiante registrado correctamente.");
    }

    private static void mostrarInfoEstudiante() {
        if (nombreEstudiante.equals("N/A")) {
            System.out.println("ℹ️ No hay estudiante registrado.");
        } else {
            System.out.println("----- DATOS DEL ESTUDIANTE -----");
            System.out.println("Nombre: " + nombreEstudiante);
            System.out.println("Nota 1: " + nota1);
            System.out.println("Nota 2: " + nota2);
            System.out.println("Nota 3: " + nota3);
        }
    }

    private static void mostrarPromedioYEstado() {
        if (nombreEstudiante.equals("N/A")) {
            System.out.println("ℹ️ No hay estudiante registrado.");
            return;
        }

        double promedio = calcularPromedioEstudiante();
        System.out.printf("Promedio: %.2f\n", promedio);

        if (promedio >= 60) {
            System.out.println("✅ Estado: Aprobado");
        } else {
            System.out.println("❌ Estado: Reprobado");
        }
    }

    private static void limpiarDatos() {
        nombreEstudiante = "N/A";
        nota1 = 0;
        nota2 = 0;
        nota3 = 0;
        System.out.println("🧹 Datos del estudiante eliminados.");
    }

    private static boolean validarNombre(String nombre) {
        return nombre != null && !nombre.strip().isEmpty();
    }

    private static boolean validarNota(double nota) {
        return nota >= 0 && nota <= 100;
    }

    private static boolean esNumeroValido(String texto) {
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
