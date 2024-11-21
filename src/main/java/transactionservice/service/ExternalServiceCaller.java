package transactionservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalServiceCaller {

    @Autowired
    private RestTemplate restTemplate;

    private static final String OTHER_MICROSERVICE_URL = "http://localhost:8080/api/auth/validate";

    public String callOtherServiceWithPost(String token) {
        // Example data to send in the POST request
        String requestBody = "data to send";

        // Setting headers (optional)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("token", token);

        // Wrap data and headers into HttpEntity
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Sending POST request
        ResponseEntity<String> response = restTemplate.exchange(OTHER_MICROSERVICE_URL, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // Handle error or fallback
            return "Error calling external service";
        }
    }
}
