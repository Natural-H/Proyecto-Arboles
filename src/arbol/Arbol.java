package arbol;

     class Arbol {
    Nodo raiz;
    static Nodo gst;
    public static int i, x;
    public static boolean y=true;
    public static String hermanos="";
    public static String hijos;
    Arbol() {
        this.raiz = null;
    }
    
    void agregarFamilia(String nombre, String nombrePadre) {
        if(y){
        if (raiz == null) {
            raiz = new Nodo(nombre);
            raiz.padre="Desconocido";
        } else {
            AgFamilia(raiz, nombre, nombrePadre);
        }
        }else System.out.println("No se encontró al padre");
    }
    
    void AgFamilia(Nodo nodo, String nombre, String nombrePadre) {
        if (nodo == null) {
            return;
        }
        if (nodo.nombre.equals(nombrePadre)) {
            if (nodo.hijo == null) {
                nodo.hijo = new Nodo(nombre);
                AsPadre(nodo.hijo, nombrePadre);
            } else {
                agregarHermano(nodo.hijo, nombre, nombrePadre);
            }
        } else {
            AgFamilia(nodo.hijo, nombre, nombrePadre);
            AgFamilia(nodo.hermano, nombre, nombrePadre);
        }
    }
    
    void agregarHermano(Nodo nodo, String nombre, String NombrePadre) {
        while (nodo.hermano != null) {
            nodo = nodo.hermano;
        }
        nodo.hermano = new Nodo(nombre);
        AsPadre(nodo.hermano, NombrePadre);
    }
    
    void PreOrden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.nombre + " --> ");
            PreOrden(nodo.hijo);
            PreOrden(nodo.hermano);
        }
    }
    public void InOrden(Nodo nodo) {
        if (nodo != null) {
            InOrden(nodo.hijo);
            System.out.print(nodo.nombre + " --> ");
            InOrden(nodo.hermano);
        }
    }


    public void PostOrden(Nodo nodo) {
        if (nodo != null) {
            PostOrden(nodo.hijo);
            PostOrden(nodo.hermano);
            System.out.print(nodo.nombre + " --> ");
        }
    }
 
    void padreExiste(Nodo nodo, String NombrePadre){
        if(nodo!=null){
            if(nodo.nombre.equals(NombrePadre)){
            y=true;
            return;}
            padreExiste(nodo.hijo, NombrePadre);
            padreExiste(nodo.hermano, NombrePadre);
        }
    
    }
    public String duplicado(String nombre, String nombrePadre){
        y=false;
        padreExiste(raiz, nombrePadre);
        if(y){
        i=0;
        x=0;
        String nombreRep=nombre;
        Repduplicado(raiz, nombre);
        if (i!=0){
            while(i!=x){
                x=i;
                nombre=nombreRep+"."+i;
                Repduplicado(raiz, nombre);
            }          
            System.out.println("Se encontraron 1 o más miembros con el mismo nombre, por lo que se agregó un número("+i+") a su nombre para diferenciarlo");
            System.out.println("El nuevo nombre es "+nombre);
            return nombre;
        }
    }
        return nombre;
    }
    
    void Repduplicado(Nodo nodo, String nombre){
        if (nodo!=null){
            if(nombre.equals(nodo.nombre)){
            i=i+1;
            }
            
            Repduplicado(nodo.hijo, nombre);
            Repduplicado(nodo.hermano, nombre);}
        
    }
    
    void AsPadre(Nodo nodo, String NombrePadre){
        nodo.padre=NombrePadre;
    }

    void obtenerDatos(String nombre) {
        Resultado resultado = obtenerDatos2(raiz, nombre, 0);
        
        if (resultado != null) {
            System.out.println("Padre de " + nombre + ": " + resultado.padre);
            System.out.println("Hermano(s) de " + nombre + ": " + resultado.hermano);
            System.out.println("Hijo(s) de " + nombre + ": " + resultado.hijo);
            System.out.println("Nivel de " + nombre + ": " + resultado.nivel);
            hermanos="";
            hijos=""; 
        } else {
            System.out.println("Miembro no encontrado");
        }
    }


    private Resultado obtenerDatos2(Nodo nodo, String nombre, int nivel) {
        if (nodo == null) {
            return null;
        }

        if (nodo.nombre.equals(nombre)) {
            if(!nombre.equals(raiz.nombre)){
            ObtenerHermanos(raiz, nodo.padre, nodo.nombre);}else hermanos="No tiene hermanos";
            ObtenerHijos(nodo);
            return new Resultado(nodo.padre, nivel, hijos, hermanos);
        }

        Resultado resultadoHijo = obtenerDatos2(nodo.hijo, nombre, nivel + 1);
        if (resultadoHijo != null) {
            return resultadoHijo;
        }

        return obtenerDatos2(nodo.hermano, nombre, nivel+1);
    }
    
    public void padreObtenerhermano(Nodo nodo, String NombrePadre) {
        if (!nodo.nombre.equals(NombrePadre)) {
            if(nodo.hijo!=null){
            padreObtenerhermano(nodo.hijo, NombrePadre);}
            if(nodo.hermano!=null){
            padreObtenerhermano(nodo.hermano, NombrePadre);}
        }
        if(nodo.nombre.equals(NombrePadre)){
            gst=nodo;
        }
    }
    
    void ObtenerHermanos(Nodo nodo, String NombrePadre, String Nombre) {
        
        padreObtenerhermano(nodo, NombrePadre);
        nodo=gst;
        gst=null;
        nodo=nodo.hijo;
        if(nodo.hermano!=null){
            if(!nodo.nombre.equals(Nombre)){
                hermanos=nodo.nombre;}
        while (nodo.hermano != null) {
            nodo = nodo.hermano;
            if(!nodo.nombre.equals(Nombre)){
                hermanos=hermanos+" - "+nodo.nombre;}
        }   
        }else hermanos="No tiene hermanos";
    }
    void ObtenerHijos(Nodo nodo){
        if (nodo.hijo!=null){
                nodo=nodo.hijo;
                hijos=nodo.nombre;
            while (nodo.hermano != null) {
                nodo = nodo.hermano;
                hijos=hijos+" - "+nodo.nombre;
            }   
        }else hijos="No tiene hijos";
    }
    private static class Resultado {
        String padre;
        int nivel;
        String hijo;
        String hermano;
        Resultado(String padre, int nivel, String hijo, String hermano) {
            this.padre = padre;
            this.nivel = nivel;
            this.hijo= hijo;
            this.hermano= hermano;
        }
    }
}
