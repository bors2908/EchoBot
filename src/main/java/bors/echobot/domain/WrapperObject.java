package bors.echobot.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class WrapperObject {
    VkMessage message;
    List<VkNameResponse> response;
}

