package egecoskun121.com.crm.services;

import egecoskun121.com.crm.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@EnableScheduling
@EnableAsync
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;

    @Value("mail.sender")
    String senderEmail;


    public void sendEmail(String toEmail,
                          String subject,
                          String body){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(senderEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    @Scheduled(cron = "* * * */30 * *")
    @Async
    public void sendMail(){
        for (User user: userService.getAllUsers()) {
            if(!user.getIsUserHappy()){
                sendEmail(user.getEmail(),"Are you happy with our products?","Hey there," +
                        "We just wanted to say hi and ask if you need any help about our products." +
                        "Just let us know if you need any help ! :)");
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = LocalDateTime.now().format(formatter);
                System.out.println("Mail gonderildi"+ formattedDate);
            }
            user.setIsPopupShowed(false);
        }
    }
}
