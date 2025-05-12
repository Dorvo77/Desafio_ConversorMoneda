import java.util.Scanner;
import servicios.ConsultaConversion;
import servicios.Service;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        String opcion;
        String menu = """
                ********************************************************
                *        Bienvenido/a al conversor de moneda $$        *
                ********************************************************
                
                  1. Dólar estadounidense (USD) a Peso Argentino (ARS)
                  2. Peso Argentino (ARS) a Dólar estadounidense (USD)
                  3. Dólar estadounidense (USD) a Real Brasileno (BRL)
                  4. Real Brasileno (BRL) a Dólar estadounidense (USD)
                  5. Dólar estadounidense (USD) a Peso Colombiano (COP)
                  6. Peso Colombiano (COP) a Dólar estadounidense (USD)
                  7. Salir
                
                 Seleccione la conversión de moneda que desea realizar: 
                """;
        try {
            while (true) {
                System.out.printf(menu);
                opcion = sc.nextLine();
                if (opcion.equals("7")) {
                    System.out.println("Adios!");
                    break;
                }
                ConsultaConversion consulta = new ConsultaConversion();
                Service service = new Service();
                service.menu(opcion, consulta);


            }
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        sc.close();
    }
}
