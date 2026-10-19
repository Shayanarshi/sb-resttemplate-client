package in.ashokit.runner;

import in.ashokit.model.Product;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyRunner implements ApplicationRunner {
    @Override

    public void run(ApplicationArguments args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/product/{id}";
        ResponseEntity<Product> re = restTemplate.getForEntity(url, Product.class, "4e8554bd-8467-4c09-95c7-8f32b9bc373a");
        Product pe = re.getBody();
        System.out.println("The product details fetched From the server");
        System.out.println("Name : "+ pe.getName());
        System.out.println("Price : "+pe.getPrice());


        System.out.println("=+".repeat(20));



        String url2= "http://localhost:8080/save";

        Product product = new Product();
        product.setName("Mouse");
        product.setPrice(1600.0);

        ResponseEntity<Product> re2 = restTemplate.postForEntity(url2, product, Product.class);
        Product saved = re2.getBody();
        System.out.println("Product details saved to the database.");
        System.out.println("Id : " +saved.getId());
        System.out.println("Name : " +saved.getName());
        System.out.println("Price : " +saved.getPrice());


    }
}
