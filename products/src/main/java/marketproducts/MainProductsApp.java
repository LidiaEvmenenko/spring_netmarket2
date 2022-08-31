package marketproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MainProductsApp {
    public static void main(String[] args) {
        SpringApplication.run(MainProductsApp.class, args);
    }
}
