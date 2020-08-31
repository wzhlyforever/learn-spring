package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication   //表明这是spring boot应用
public class TacoCloudApplication {
    //运行此应用
    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

}
