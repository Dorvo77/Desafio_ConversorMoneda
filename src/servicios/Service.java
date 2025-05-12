package servicios;

import java.util.List;
import java.util.Scanner;

public class Service {
    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    List<String> listaMonedas = List.of("NULL", "USD/ARS", "ARS/USD", "USD/BRL", "BRL/USD", "USD/COP", "COP/USD");


    public void menu(String opcion, ConsultaConversion consulta) {
        try {

            int opcionUsuario = Integer.parseInt(opcion);
            if (opcionUsuario >= 1 && opcionUsuario < listaMonedas.size()) {
                String seleccionMoneda = listaMonedas.get(opcionUsuario);

                if (!seleccionMoneda.equals("NULL")) {
                    String[] partes = seleccionMoneda.split("/");
                    if (partes.length == 2) {
                        String monedaOrigen = partes[0];
                        String monedaDestino = partes[1];
                        System.out.println("Moneda de origen: " + monedaOrigen);
                        System.out.println("Moneda de destino: " + monedaDestino);
                        System.out.println("Ingrese la cantidad de " + monedaOrigen + " que desea convertir a " + monedaDestino + ": ");
                        double cantidad = sc.nextDouble();
                        ExchangeCurrency conversion = consulta.monedaConversion(monedaOrigen, monedaDestino);
                        System.out.println("El resultado de la conversión es: "+ String.format("%.2f",conversion.conversion_rate() * cantidad ));
                        System.out.println();

                    } else {
                        System.out.println("Formato de moneda inesperado: " + seleccionMoneda);
                    }
                } else {
                    System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Opción fuera de rango.");
            }
        }catch (Exception e){
            System.out.println("Ingreso una letra, debe ser un numero: ");
        }

    }

}