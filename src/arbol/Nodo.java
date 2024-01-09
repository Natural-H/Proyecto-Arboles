
package arbol;

class Nodo {
    String nombre;
    Nodo hijo;
    Nodo hermano;
    String padre;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.hijo = null;
        this.hermano = null;
        this.padre = "";
    }
}

