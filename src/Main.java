import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scam = new Scanner(System.in);
    static ArrayList<Persona> personas = new ArrayList<Persona>();

    static CRUDProcess crud = new CRUDProcess();
    static int opc = 0;
    static boolean salir = false;

    public static void main(String[] args) {
        crud.readDatabase(personas);
        do {
            System.out.println("====================================");
            System.out.println("1. Crear persona.");
            System.out.println("2. Leer personas.");
            System.out.println("3. Actualizar persona.");
            System.out.println("4. Eliminar persona.");
            System.out.println("5. Salir.");
            System.out.println("====================================");
            System.out.println("Ingresa una opcion: ");
            opc = scam.nextInt();

            switch (opc) {
                case 1:
                    crud.create(personas);
                    break;
                case 2:
                    crud.read(personas);
                    break;
                case 3:
                    crud.update(personas);
                    break;
                case 4:
                    crud.delete(personas);
                    break;
                case 5:
                    salir = true;
                    crud.save(personas);
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (!salir);

    }
}