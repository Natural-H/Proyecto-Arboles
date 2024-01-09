
package arbol;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner vx = new Scanner(System.in, StandardCharsets.ISO_8859_1);
        Arbol arbol = new Arbol();
        boolean r = false;

        int i;
        while (!r) {
            System.out.println("¿Desea crear un árbol genealógico? S/N");
            r = vx.nextLine().equalsIgnoreCase("S");
            if (r) {
                System.out.print("Ingrese el nombre del primer miembro de la familia: ");
                String primerMiembro = vx.nextLine();
                arbol.agregarFamilia(primerMiembro, "");
            }
        }
        while (r) {
            i = 0;
            while (i < 1 || i > 4) {
                System.out.println("¿Qué desea hacer?");
                System.out.println("1-. Ingresar nuevo miembro");
                System.out.println("2-. Consultar a un miembro");
                System.out.println("3-. Recorrer el árbol");
                System.out.println("4-. Terminar");
                i = vx.nextInt();
                vx.nextLine();
                if (i < 1 || i > 4) {
                    System.out.println("Error...");
                }
            }
            boolean agregarMas = true;
            switch (i) {
                case 1:
                    while (agregarMas) {
                        System.out.print("Ingrese el nombre del nuevo miembro: ");
                        String nombre = vx.nextLine();
                        System.out.print("Ingrese el nombre del padre: ");
                        String nombrePadre = vx.nextLine();
                        nombre = arbol.duplicado(nombre, nombrePadre);
                        arbol.agregarFamilia(nombre, nombrePadre);
                        System.out.print("¿Agregar más miembros? (S/N): ");
                        agregarMas = vx.nextLine().equalsIgnoreCase("S");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del miembro para obtener sus datos ");
                    String nombreBuscar = vx.nextLine();
                    arbol.obtenerDatos(nombreBuscar);

                    break;
                case 3:
                    System.out.println("Árbol genealógico:");
                    System.out.println("\nPreorden:");
                    arbol.PreOrden(arbol.raiz);
                    System.out.print("Fin");
                    System.out.println("\nInorden");
                    arbol.InOrden(arbol.raiz);
                    System.out.print("Fin");
                    System.out.println("\nPostOrden");
                    arbol.PostOrden(arbol.raiz);
                    System.out.print("Fin");
                    System.out.println("\n");
                    break;
                case 4:
                    r = false;
                    break;
            }
        }
        System.out.println("Gracias por plantar un árbol");

    }
}
