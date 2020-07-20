package bors.echobot.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class VkMessage {
    Integer id;
    Integer date;
    Integer peer_id;
    Integer from_id;
    String text;
    Integer random_id;
    String ref;
    String ref_source;
    Object[] attachments;
    Boolean important;
    Object geo;
    String payload;
    Object keyboard;
    Object[] fwd_messages;
    Object reply_message;
    Object action;
}
