package org.restbucks.wechat.bff.time;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("time")
@Component
@Data
public class Clock {

    private String zoneId = "Asia/Shanghai";

    public ZonedDateTime now() {
        return ZonedDateTime.now(zoneId());
    }

    private ZoneId zoneId() {
        return ZoneId.of(zoneId);
    }
}
