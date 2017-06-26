package org.restbucks.wechat.bff;

import com.github.hippoom.wechat.mp.autoconfigure.annotation.EnableWeChatMpApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableWeChatMpApplication
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
