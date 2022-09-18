package marketproducts;

import marketapi.ApiProductsListView;
import marketapi.ApiProductsView;

import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.test.context.jdbc.Sql;



import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;

public class MainProductsAppTest extends FunctionalTest{
    @Autowired
    private TestRestTemplate testRestTemplate;

    @DisplayName("Test testCreateNewProduct")
    @Sql(scripts = "db.sql")
    @Test
    void testCreateNewProducts() {
        ApiProductsView actually = new ApiProductsView();

        actually.setId(1L);
        actually.setPrice(100.);
        actually.setTitle("Banana");
        actually.setCategoryTitle("Food");
        actually.setCount(Double.valueOf(0));
        actually.setDescription(null);
        actually.setWeight(Double.valueOf(0));
        actually.setExpiration_date(null);
        actually.setImage(null);
        actually.setManufacturerTitle("ttt");
        actually.setNom(0);

        String expected = getResource("testCreateNewProduct/expected.json");

        testRestTemplate.postForObject("http://localhost:8190/logic-service/api/v1/products",
                actually, ApiProductsView.class);
        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }
    @DisplayName("Test FindAll")
    @Sql(scripts = "db.sql")
    @Test
    void testFindAll() {

        ApiProductsListView products =  createRequest()
                .url("http://localhost:8190/logic-service/api/v1/products")
                .get(ApiProductsListView.class);

        String expected = getResource("testFindAll/expected.json");

        JsonAssert.assertJsonEquals(
                expected,
                products,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );

    }
    @DisplayName("Test FindByCategory")
    @Sql(scripts = "db.sql")
    @Test
    void testFindByCategory() {

       ApiProductsListView products =  createRequest()
                .url("http://localhost:8190/logic-service/api/v1/products/by_category")
               .param("title","Food")
                .get(ApiProductsListView.class);

        String expected = getResource("testFindByCategory/expected.json");

        JsonAssert.assertJsonEquals(
                expected,
                products,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );

    }
    @DisplayName("Test Delete")
    @Sql(scripts = "db.sql")
    @Test
    void testDelete() {
        Long id = 13L;
        String entityUrl = "http://localhost:8190/logic-service/api/v1/products/"+id ;

         createRequest()
                .url(entityUrl)
                .delete();

    }

}
