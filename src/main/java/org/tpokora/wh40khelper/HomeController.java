package org.tpokora.wh40khelper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @RequestMapping("/api/hello")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<>();
        model.put("content", "Welcome to WH40k Helper application");
        return model;
    }
}
