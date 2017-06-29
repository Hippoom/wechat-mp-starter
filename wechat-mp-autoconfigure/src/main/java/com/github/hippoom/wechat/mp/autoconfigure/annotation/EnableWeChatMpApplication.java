package com.github.hippoom.wechat.mp.autoconfigure.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.github.hippoom.wechat.mp.autoconfigure.WeChatMpConfiguration;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Enable WeChat MP support.
 *
 * <pre class="code">
 *    import com.github.hippoom.wechat.mp.autoconfigure.annotation.EnableWeChatMpApplication;
 *    import org.springframework.boot.SpringApplication;
 *    import org.springframework.boot.autoconfigure.SpringBootApplication;
 *
 *    &#064;EnableWeChatMpApplication
 *    &#064;SpringBootApplication
 *    public class Application {
 *
 *        public static void main(String[] args) {
 *            SpringApplication.run(Application.class, args);
 *        }
 *    }
 * </pre>
 *
 * @see WeChatMpConfiguration
 * @since 0.1.0
 */
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
