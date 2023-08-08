import com.tinqin.academy.rest.BffApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BffApplication.class})
@WebAppConfiguration
public class ControllerTest {

    @Test
    public void getItem(){
        // MvcResult mvcResult = mo
    }
}
