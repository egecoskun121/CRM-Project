package egecoskun121.com.crm;

import egecoskun121.com.crm.model.entity.User;
import egecoskun121.com.crm.services.EmailSenderService;
import egecoskun121.com.crm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class CrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

}
