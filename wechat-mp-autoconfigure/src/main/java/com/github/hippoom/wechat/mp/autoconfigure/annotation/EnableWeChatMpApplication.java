package com.github.hippoom.wechat.mp.autoconfigure.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.github.hippoom.wechat.mp.autoconfigure.WeChatMpConfiguration;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Retention(RUNTIME)
@Target(
    {
        TYPE
    }
)
@Documented
@Import(WeChatMpConfiguration.class)
@Configuration
public @interface EnableWeChatMpApplication {

}
