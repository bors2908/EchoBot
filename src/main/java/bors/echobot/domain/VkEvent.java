package bors.echobot.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class VkEvent {
    String type;
    WrapperObject object;
    Integer group_id;
    String secret;
}
