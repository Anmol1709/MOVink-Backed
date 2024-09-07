
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin
public class UserController {

    @GetMapping("/put")
    public void getcharts() {

        System.out.println("ho gaya");

    }

}