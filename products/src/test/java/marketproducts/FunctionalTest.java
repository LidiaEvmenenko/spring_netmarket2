package marketproducts;

import marketapi.ApiProductsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})

public class FunctionalTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public RequestBuilder createRequest() {
        return new RequestBuilder();
    }

    public class RequestBuilder {

        private final Map<String, Object> params;
        private final Map<Long, Object> paramsLong;

        private String url;

        public RequestBuilder() {
            params = new HashMap<>();
            paramsLong = new HashMap<>();
        }

        public RequestBuilder url(String url) {
            this.url = url;
            return this;
        }

        public RequestBuilder param(String parameter, Object value) {
            params.put(parameter, value);
            return this;
        }


        public<T> T get(Class<T> cls) {
            return testRestTemplate.getForObject(url, cls, params);
        }

        public <T> T get(ParameterizedTypeReference<T> typeReference) {
            return testRestTemplate.exchange(url, HttpMethod.GET, null, typeReference, params)
                    .getBody();
        }
        public <T> T post(@RequestBody T request,ParameterizedTypeReference<T> typeReference ) {
            return (T) testRestTemplate.exchange(url, HttpMethod.POST, (HttpEntity<?>) request, typeReference, params);

        }

        public <T> T post(Class<T> cls, Object obj) {
            return testRestTemplate.postForObject(url, obj, cls, params);
        }

        public void delete() {
            System.out.println(url);
             testRestTemplate.delete(url);
        }

    }

    public String getResource(String name) {
        String className = getClass().getSimpleName();
        String directory = className + FileSystems.getDefault().getSeparator() + name;
        try (InputStream inputStream = getClass().getResourceAsStream(directory)){
            assert inputStream != null;
            byte[] bytes = inputStream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
