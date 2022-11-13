package egecoskun121.com.crm;

import egecoskun121.com.crm.services.EmailSenderService;
import egecoskun121.com.crm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CrmApplication {

    @Autowired
    private EmailSenderService senderService;
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail(){
        for (String mail: userService.getAllEmails()) {
            senderService.sendEmail(mail,"Are you happy with our products?","Hey there," +
                    "We just wanted to say hi and ask if you need any help about our products." +
                    "Just let us know if you need any help ! :)");

        }

    }

}
