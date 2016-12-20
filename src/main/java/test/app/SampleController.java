package test.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import test.app.pojo.User;

@Controller
@RequestMapping("/admin")
public class SampleController {
    @Autowired
    public User adminUser;

    @RequestMapping(method= RequestMethod.GET)
    public String ping() {
        return "Hello, world";
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/adminUser")
    @ResponseBody
    public User getAdmin() {
        return adminUser;
    }
}