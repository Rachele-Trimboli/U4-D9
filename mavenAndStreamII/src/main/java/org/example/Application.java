package org.example;

import com.github.javafaker.Faker;
import entities.Customer;
import entities.Order;
import entities.Product;

import java.sql.SQLClientInfoException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {


        //PRODOTTI
        Product product1 = new Product("Il piccolo Principe", "Books", 12.99);
        Product product2 = new Product("Harry Potter Saga", "Books", 122);
        Product product3 = new Product("Scarpe", "Boys", 150);
        Product product4 = new Product("Pigiama", "Boys", 10.90);
        Product product5 = new Product("Coperta", "Baby", 10.50);
        Product product6 = new Product("Passeggino", "Baby", 250);
        Product product7 = new Product("Giocattolo", "Baby", 11);
        Product product8 = new Product("Pannolini", "Baby", 19.50);

        //LISTA PRODOTTI
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);
        productList.add(product7);
        productList.add(product8);



        //CUSTOMER
        Faker faker = new Faker(Locale.ITALY);
        Customer customer1 = new Customer(faker.funnyName().name(), 2);
        Customer customer2 = new Customer(faker.funnyName().name(), 3);
        Customer customer3 = new Customer(faker.funnyName().name(), 1);
        Customer customer4 = new Customer(faker.funnyName().name(),2);
        Customer customer5 = new Customer(faker.funnyName().name(),2);
        Customer customer6 = new Customer(faker.funnyName().name(),2);



        //PRODOTTI PER BAMBINI
        List<Product> prodottiPerBambini = new ArrayList<>();
        prodottiPerBambini.add(product5);
        prodottiPerBambini.add(product6);

        //ORDINI
        Order ordineBambino = new Order("In preparazione", LocalDate.now().minusDays(1), LocalDate.now(), prodottiPerBambini, customer1);
        Order ordine2 = new Order("In preparazione", LocalDate.of(2021, 3, 3), LocalDate.now(), productList, customer2);
        Order ordine3 = new Order("In preparazione", LocalDate.of(2021, 3, 3), LocalDate.now(), productList, customer3);
        Order ordine4 = new Order("In preparazione", LocalDate.of(2021, 3, 3), LocalDate.now(), productList, customer4);
        Order ordine5 = new Order("In preparazione", LocalDate.of(2021, 3, 3), LocalDate.now(), productList, customer5);
        Order ordine6 = new Order("In preparazione", LocalDate.of(2021, 3, 3), LocalDate.now(), productList, customer6);
        List<Order> ordini = new ArrayList<>();
        ordini.add(ordineBambino);
        ordini.add(ordine2);
        ordini.add(ordine3);
        ordini.add(ordine4);
        ordini.add(ordine5);
        ordini.add(ordine6);

        System.out.println();
        System.out.println("------Esercizio 1---------");
        Map<String, List<Order>> ordiniPerCliente = ordini.stream().collect(Collectors.groupingBy(ordine -> ordine.getCustomer().getName()));
        ordiniPerCliente.forEach((customer,ordine) -> System.out.println("Cliente " + customer + " ordine: " + ordine));

        System.out.println();
        System.out.println("------Esercizio 2---------");

        Map<String, Double> mediaPrezzoPerCliente = ordini.stream()
                .collect(Collectors.groupingBy(
                        ordine -> ordine.getCustomer().getName(),
                        Collectors.summingDouble(ordine -> ordine.getProducts().stream()
                                .mapToDouble(prodotto -> prodotto.getPrice()).sum()
                        )
                ));

        mediaPrezzoPerCliente.forEach((cliente, prezzo) -> System.out.println(cliente + " la sua spesa media " + prezzo));

        System.out.println();
        System.out.println("------Esercizio 3---------");

        double prezzoPiùAlto = productList.stream().mapToDouble(prodotto->prodotto.getPrice()).max().getAsDouble();
        System.out.println("Prezzo più alto: " + prezzoPiùAlto);

        System.out.println();
        System.out.println("------Esercizio 4---------");

        double mediaPrezzi = ordini.stream().map(prodotto->prodotto.getProducts().stream().mapToDouble(prod->prod.getPrice()).average().getAsDouble()).count();
        System.out.println(mediaPrezzi);

        System.out.println();
        System.out.println("------Esercizio 5---------");

        







    }
}
