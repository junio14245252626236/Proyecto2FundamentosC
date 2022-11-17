import java.io.FileWriter;
import java.io.PrintWriter;

public class Tanque{
      // arreglo 2x2 para guardar todo tipo de tanques
    public static Tanque tanques[][] = new Tanque[2][2];
    // Atributos generales 
    private int Salud;
    private String nom;
      
 // constructor 

    public Tanque(int salud, String nom){
        this.Salud=salud;
        this.nom=nom;
    }

    public int getSalud() {
        return this.Salud;
    }

    public String getnom() {
        return this.nom;
    }

    public void setSalud(int salud) {
        this.Salud = salud;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    // Crear y agregar tanques de forma aleatoria

    public static void agregarTanque(Tanque tanque){
        boolean Verificacion = false;
        for(int x=0;x<2;x++){
            if(Verificacion){
                break;
            }else{
                for(int y=0;y<2;y++){
                    if(tanques[x][y]==null){
                        tanques[x][y] = tanque;
                        Verificacion=true;
                        break;
                    }
                }
            }
        }
    }

    public static void generarTanques(){
        int cantidadCreados = (int) (Math.random()*4+1);
        int tipoCreado;
        for(int i=1;i<=cantidadCreados;i++){
            tipoCreado = (int) (Math.random()*2+1);
            if(tipoCreado==1){
                agregarTanque(new TanqueNormal());
            }else if(tipoCreado==2){
                agregarTanque(new TanqueAlien());
            }
        }
    }
 
    // Infomacion para mostrar tablero
    // retorna la cantidad de tanque instanciados

    public static int tanquesEnLista(){
        int numEntrega = 0;
        for(int x=0;x<2;x++){
            for(int y=0;y<2;y++){
                if(!(tanques[x][y]==null)){
                    numEntrega ++;
                }
            }
        }
        return numEntrega;
    }

     public String nomTanque(){
        return this.nom + "-" + this.Salud;
    }
    // tablero del juego

    public static String tableroTankWars(){
        int numeroTanque = tanquesEnLista();
        String tablero = "";
        if(numeroTanque==1){
            tablero = "-------------\n"+
                    "|" + tanques[0][0].nomTanque() +"|" +"     "+ "|\n"+
                    "-------------\n"+
                    "|" +"     "+"|" +"     "+ "|";
        }else if(numeroTanque==2){
            tablero = "-------------\n"+
                    "|" + tanques[0][0].nomTanque() +"|" + tanques[0][1].nomTanque()+ "|\n"+
                    "-------------\n"+
                    "|" +"     "+"|" +"     "+ "|";
        }else if(numeroTanque ==3){
            tablero = "-------------\n"+
                    "|" + tanques[0][0].nomTanque() +"|" + tanques[0][1].nomTanque()+ "|\n"+
                    "-------------\n"+
                    "|" +tanques[1][0].nomTanque() +"|" +"     "+ "|";
        }else{
            tablero = "-------------\n"+
                    "|" + tanques[0][0].nomTanque() +"|" + tanques[0][1].nomTanque()+ "|\n"+
                    "-------------\n"+
                    "|" +tanques[1][0].nomTanque() +"|" +tanques[1][1].nomTanque()+ "|";
        }
        return tablero;
    }

    // metodo para dispara a un tanque especifico
    // obvia aquellos tanque muertos y las posiciones donde no hay tanques 


    public static void dispararBala(int posicionx, int posiciony){
        if(posicionx >=2 || posiciony >=2){
            System.out.println("\nLa posicionicion digitada no existe\n");
        }else if(tanques[posicionx][posiciony]==null){
            System.out.println("En esta posicionicion no exite un tanque");
        }else if(tanques[posicionx][posiciony].getSalud()>0){
            tanques[posicionx][posiciony].setSalud(tanques[posicionx][posiciony].getSalud()-5); 
        }else if(tanques[posicionx][posiciony].getSalud()<=0){
            System.out.println("El tanque ya esta muerto");
        }
    }

    // metodo que vuelve 0 la vida de un tanque de forma aleatoria

    public static void bombaAtomica(){
        int posicionx;
        int posiciony;
        while(true){
            posicionx = (int) (Math.random()*2);
            posiciony = (int) (Math.random()*2);
            if(tanques[posicionx][posiciony]==null){
                continue;
            }else if(tanques[posicionx][posiciony].getSalud()>0){
                tanques[posicionx][posiciony].setSalud(0);
                break;
            }
        }
    } 

  /*
     * hace que el tanque con menos vida se le duplique esta
     * omite las posiciones sin tanque y los tanque muertos
     */

  
    public static void tanqueMutante(){
        int menor[] = new int[tanquesEnLista()];
        int num = 0;
      
        for(int x=0;x<2;x++){
            for(int y=0;y<2;y++){
                if(!(tanques[x][y]==null) && tanques[x][y].getSalud()>0){
                    menor[num]= tanques[x][y].getSalud();
                    num++;
                }
            }
        }
        // Se busca en esa lista cual es el menor entre la lista de datos de sangre

       
        int menor2 = menor[0];
        for(int i=1;i < num;i++){
            if(menor[i]<menor2){
                menor2 = menor[i];
            }
        }
        // Se define cual es el tanque que tiene esa sangre, cuando se encuenta se duplica su sangre y sale del ciclo

        boolean Verificacion = false;
        for(int x=0;x<2;x++){
            if(Verificacion){
                break;
            }
            for(int y=0;y<2;y++){
                if(!(tanques[x][y]==null) && tanques[x][y].getSalud()==menor2){
                    tanques[x][y].setSalud(tanques[x][y].getSalud()*2);
                    Verificacion = true;
                    break;
                }
            }
        }
    }

    public static String mensajeAbuela(){
        return "Eavemaria purisima";
    }
    // guarda los datos de la sangre en un archivo de tipo txt en un momento determinado

    public static void guardarSangreTXT(){
        try {
            PrintWriter archivo = new PrintWriter(new FileWriter("sangre.txt"));
            
            for(int x = 0;x<2;x++){
                for(int y = 0;y<2;y++){
                    if(!(tanques[x][y]==null)){
                        archivo.println(tanques[x][y].getnom() + " " + tanques[x][y].getSalud());
                    }
                }
            }
            archivo.close();
        } catch (Exception e) {
            System.out.println("Error al guardar en el archivo" + e);
        }
    }

    // retorna un String con la sangre en un momento determinado 

 
    public static String leerSangre(){
        String salida = "";
        for(int x = 0;x<2;x++){
            for(int y = 0;y<2;y++){
                if(!(tanques[x][y]==null)){
                   salida += tanques[x][y].getnom() + " " + tanques[x][y].getSalud() ;
                }
            }
        }

        return salida;
    }

       // retorna un numero con la cantidad de tanque muertos

    public static int muertos(){
        int muertos = 0;
        for(int x = 0;x<2;x++){
            for(int y = 0;y<2;y++){
                if(!(Tanque.tanques[x][y]==null) && Tanque.tanques[x][y].getSalud()<=0){
                    muertos++;
                }
            }
        }

        return muertos;
    }


}