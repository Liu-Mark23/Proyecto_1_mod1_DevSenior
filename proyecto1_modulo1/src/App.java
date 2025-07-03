import java.util.Scanner;

public class App {

    // Variables privadas estáticas
    private static String nombreEstudiante = "N/A";
    private static double nota1 = 0;
    private static double nota2 = 0;
    private static double nota3 = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            mostrarMenu();
            System.out.print("Ingrese su opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar enter

                switch (opcion) {
                    case 1:
                        confirmarYRegistrar(scanner);
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
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Entrada inválida.");
                scanner.nextLine(); // limpiar entrada inválida
            }

            System.out.println(); // Separación
        }

        scanner.close();
    }

    // Menú con nueva opción
    private static void mostrarMenu() {
        System.out.println("----- SISTEMA DE REGISTRO -----");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Mostrar datos del estudiante");
        System.out.println("3. Calcular promedio y estado");
        System.out.println("4. Borrar datos del estudiante");
        System.out.println("0. Salir");
    }

    // Confirmar antes de registrar (si ya hay datos)
    private static void confirmarYRegistrar(Scanner scanner) {
        if (!nombreEstudiante.equals("N/A")) {
            System.out.print("Ya hay un estudiante registrado. ¿Deseas sobrescribirlo? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (!respuesta.equals("s")) {
                System.out.println("Registro cancelado.");
                return;
            }
        }

        mostrarResumen(scanner); // Mostrar resumen antes de confirmar
        System.out.print("¿Deseas confirmar el registro? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();
        if (confirmacion.equals("s")) {
            registrarEstudiante(scanner);
        } else {
            System.out.println("Registro cancelado.");
        }
    }

    // Registro normal del estudiante
    private static void registrarEstudiante(Scanner scanner) {
        System.out.print("Nombre del estudiante: ");
        String entradaNombre = scanner.nextLine();

        while (!validarNombre(entradaNombre)) {
            System.out.println("Nombre inválido.");
            System.out.print("Nombre del estudiante: ");
            entradaNombre = scanner.nextLine();
        }

        nombreEstudiante = entradaNombre;
        nota1 = pedirNota(scanner, 1);
        nota2 = pedirNota(scanner, 2);
        nota3 = pedirNota(scanner, 3);

        System.out.println("Estudiante registrado correctamente.");
    }

    // Mostrar resumen antes de registrar
    private static void mostrarResumen(Scanner scanner) {
        System.out.println("----- INGRESO DE NUEVO ESTUDIANTE -----");
        System.out.println("Este proceso sobrescribirá los datos actuales.");
    }

    // Mostrar datos del estudiante
    private static void mostrarInfoEstudiante() {
        if (nombreEstudiante.equals("N/A")) {
            System.out.println("No hay estudiante registrado.");
        } else {
            System.out.println("----- DATOS DEL ESTUDIANTE -----");
            System.out.println("Nombre: " + nombreEstudiante);
            System.out.println("Nota 1: " + nota1);
            System.out.println("Nota 2: " + nota2);
            System.out.println("Nota 3: " + nota3);
        }
    }

    // Mostrar promedio y si aprueba
    private static void mostrarPromedioYEstado() {
        if (nombreEstudiante.equals("N/A")) {
            System.out.println("No hay estudiante registrado.");
            return;
        }

        double promedio = calcularPromedioEstudiante();
        System.out.printf("Promedio: %.2f\n", promedio);

        if (promedio >= 60) {
            System.out.println("Estado: Aprobado ✅");
        } else {
            System.out.println("Estado: Reprobado ❌");
        }
    }

    // Calcular promedio
    private static double calcularPromedioEstudiante() {
        return (nota1 + nota2 + nota3) / 3;
    }

    // Limpiar todos los datos
    private static void limpiarDatos() {
        nombreEstudiante = "N/A";
        nota1 = 0;
        nota2 = 0;
        nota3 = 0;
        System.out.println("Datos del estudiante eliminados.");
    }

    // Pedir nota válida
    private static double pedirNota(Scanner scanner, int numero) {
        double nota = -1;
        boolean esValida = false;

        while (!esValida) {
            System.out.print("Ingrese la nota " + numero + " (0-100): ");
            if (scanner.hasNextDouble()) {
                nota = scanner.nextDouble();
                scanner.nextLine(); // limpiar enter
                if (validarNota(nota)) {
                    esValida = true;
                } else {
                    System.out.println("La nota debe estar entre 0 y 100.");
                }
            } else {
                System.out.println("Eso no es un número.");
                scanner.nextLine(); // limpiar entrada inválida
            }
        }

        return nota;
    }

    // Validar nombre
    private static boolean validarNombre(String nombre) {
        return nombre != null && !nombre.trim().isEmpty();
    }

    // Validar nota
    private static boolean validarNota(double nota) {
        return nota >= 0 && nota <= 100;
    }
}
