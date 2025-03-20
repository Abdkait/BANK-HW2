package bank;

import bank.services.CliMenuService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class BankApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BankApplication.class, args);
        CliMenuService cliMenu = context.getBean(CliMenuService.class);
        cliMenu.setStream(System.in);
        cliMenu.start();
    }
}