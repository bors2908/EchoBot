package bors.echobot.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class VkNameResponse {
    Integer id;
    String first_name;
    String last_name;
    Boolean is_closed;
    Boolean can_access_closed;
}
