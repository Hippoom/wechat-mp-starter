# wechat-mp-starter[![Build Status](https://travis-ci.org/Hippoom/wechat-mp-starter.svg?branch=master)](https://travis-ci.org/Hippoom/wechat-mp-starter)
Custom spring boot starter for WeChat MP application



## Latest Release

**0.3.1**

You can download the binary from [Maven Central Repository](http://mvnrepository.com/artifact/com.github.hippoom/wechat-mp-starter):

- Gradle

```groovy
    compile 'com.github.hippoom:wechat-mp-starter:0.3.1'
    testCompile 'com.github.hippoom:wechat-mp-starter-test:0.3.1'
```

- Maven

```xml
    <dependency>
    	<groupId>com.github.hippoom</groupId>
    	<artifactId>wechat-mp-starter</artifactId>
    	<version>0.3.1</version>
    </dependency>
    <dependency>
    	<groupId>com.github.hippoom</groupId>
    	<artifactId>wechat-mp-starter-test</artifactId>
    	<version>0.3.1</version>
      	<scope>test</scope>
    </dependency>
```

You can find release notes [here](https://github.com/Hippoom/wechat-mp-starter/wiki/release-notes).

## Why

> WeChat supports users who wish to register as an official account, which enables them to push feeds to subscribers, interact with subscribers and provide them with services. There are three types of official accounts: service account, subscription account and enterprise account. Once users as individuals or organizations set up a type of account, they cannot change it to another type. By the end of 2014, the number of WeChat official accounts had reached 8 million.[[27\]](https://en.wikipedia.org/wiki/WeChat#cite_note-27) Official accounts of organizations can apply for verified (at cost of 300 RMB), official, public accounts. Official accounts can be used as a platform for services such as hospital pre-registrations,[[28\]](https://en.wikipedia.org/wiki/WeChat#cite_note-28) visa renewal[[29\]](https://en.wikipedia.org/wiki/WeChat#cite_note-29) or credit card service.[[30\]](https://en.wikipedia.org/wiki/WeChat#cite_note-30)       
>
> ​																	quoted from [wikipedia](https://en.wikipedia.org/wiki/WeChat)



WeChat provides [APIs and docs](http://admin.wechat.com/wiki/index.php?title=Getting_Started) to help you develop official account web applications. Although the APIs is easy to integrate with, it is a good idea to leverage the battle tested libraries provided by community, [weixin-java-tools](https://github.com/wechat-group/weixin-java-tools), [weixin4j](https://github.com/foxinmy/weixin4j), just to name a few. You may also want to try our starter to auto configure the components  if you happen to use [spring-boot](https://projects.spring.io/spring-boot/).



## Quick Start

`@EnableWeChatMpApplication` will do the tricks.

```java
import com.github.hippoom.wechat.mp.autoconfigure.annotation.EnableWeChatMpApplication;
import me.chanjar.weixin.mp.api.WxMpUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableWeChatMpApplication
@SpringBootApplication
public class Application {
  
    @Autowired
    private WxMpService wxMpService

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

You can setup your WeChat MP appId and secret in the `application{-profile}.properties`or`application{-profile}.yaml`

```yaml
wechat:
  mp:
    appId: your-app-id
    appSecret: your-app-secret
```

You can find detailed usage [here](https://github.com/Hippoom/wechat-mp-starter/wiki) and a demo application [here](https://github.com/Hippoom/wechat-mp-starter/tree/master/wechat-mp-starter-demo).



## Contributing

Any suggestion and pull request is welcome.



## License

Licensed under MIT License (the "License"); You may obtain a copy of the License in the LICENSE file, or at [here](https://github.com/Hippoom/wechat-mp-starter/blob/master/LICENSE).