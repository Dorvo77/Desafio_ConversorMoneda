package com.aluracurso.apiBook.principal;

import com.aluracurso.apiBook.model.Datos;
import com.aluracurso.apiBook.model.DatosLibros;
import com.aluracurso.apiBook.service.ConsumoAPI;
import com.aluracurso.apiBook.service.ConvertedDates;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvertedDates conversor = new ConvertedDates();
    private Scanner sc = new Scanner(System.in);
    public void muestraMenu() {

        var json = consumoAPI.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(json);
        System.out.println(datos);

        //Top libros mas descargados
        System.out.println("Libros mas descargados");

        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numerodescargas).reversed())
                .limit(10)
                .forEach(l -> System.out.println(l.titulo() + " " + l.numerodescargas()));

        // Buscar libro por nombre
        System.out.println("Buscar libro por nombre: ");
        var titulo = sc.nextLine();
        var libro = datos.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .findFirst();
        if ((libro.isEmpty())) {
            System.out.println("No se encontro el libro");

        } else {
            System.out.println(libro);
        }
        // buscar un libro pero modificando la URL
        System.out.println("INGRESE EL NOMBRE DEL LIBRO A BUSCAR: ");
        var libroBuscar = sc.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + libroBuscar.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(libroBuscar.toLowerCase()))
                .findFirst();
        if (libroBuscado.isPresent()){
            System.out.println("LIBRO ENCONTRADO!!!!");
            System.out.println(libroBuscado.get());
        }else{
            System.out.println("No se encontro el libro");
        }

        //Trabajando con estadisticacs
        DoubleSummaryStatistics est = datos.resultados().stream()
                .filter(l -> l.numerodescargas() > 0)
                .collect(Collectors.summarizingDouble(DatosLibros::numerodescargas));
        System.out.println("Promedio de descargas: " + est.getAverage());
        System.out.println("Minimo de descargas: " + est.getMin());
        System.out.println("Maximo de descargas: " + est.getMax());
        System.out.println("Total de descargas: " + est.getSum());

    }
}
