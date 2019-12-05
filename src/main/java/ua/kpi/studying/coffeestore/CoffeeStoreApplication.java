package ua.kpi.studying.coffeestore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.kpi.studying.coffeestore.domain.beverages.Beverage;
import ua.kpi.studying.coffeestore.domain.coffeefm.BeverageStore;
import ua.kpi.studying.coffeestore.domain.coffeefm.MyBeverageStore;
import ua.kpi.studying.coffeestore.repository.BeverageRepository;

@SpringBootApplication
public class CoffeeStoreApplication {

    private static final Logger log = LoggerFactory.getLogger(CoffeeStoreApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CoffeeStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(BeverageRepository repository) {
        /** BeverageStore myBeverageStore = new MyBeverageStore();
         CurrentConditionsDisplay display = new CurrentConditionsDisplay(myBeverageStore);

         Beverage beverage = myBeverageStore.orderBeverage("Espresso");
         System.out.println("Client ordered a " + beverage.getDescription() + "\n");

         beverage = myBeverageStore.orderBeverage("DarkRoast");
         System.out.println("Client ordered a " + beverage.getDescription() + "\n");

         beverage = myBeverageStore.orderBeverage("HouseBlend");
         System.out.println("Client ordered a " + beverage.getDescription() + "\n");
         */

        BeverageStore myBeverageStore = new MyBeverageStore();
        return args -> {

            // save a couple of beverages
            repository.save(myBeverageStore.orderBeverage("Espresso"));
            repository.save(myBeverageStore.orderBeverage("DarkRoast"));
            repository.save(myBeverageStore.orderBeverage("HouseBlend"));

            // fetch all beverages
            log.info("beverages found with findAll():");
            log.info("-------------------------------");
            for (Beverage beverage : repository.findAll()) {
                log.info(beverage.getDescription());
            }
            log.info("");


        };
    }

}


//    delete from beverage where beverage.beverage is not null;
//    delete from beverage where beverage.beverage is null;

