package bo.edu.ucb.mabuserregistry.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class HelloApi {
    @RequestMapping(path = "/hello")
    public String hello() {
        return "Hello from MAB User Registry";
    }
}
