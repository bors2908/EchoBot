package bors.echobot.service.impl;

import bors.echobot.domain.VkEvent;
import bors.echobot.service.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private final String CONFIRM_TOKEN = "34004707";
    private final Long GROUP_ID = 197280082L;
    private final String CONFIRMATION = "confirmation";
    private final String NEW_MESSAGE = "message_new";

    @Override
    public String prepareResponse(VkEvent event) {
        String type = event.getType();
        switch (type) {
            case CONFIRMATION:
                if (event.getGroup_id().equals(GROUP_ID)) {
                    return CONFIRM_TOKEN;
                } else throw new RuntimeException("Wrong Group_ID");
            case NEW_MESSAGE:
                return event.toString();
            default:
                return "Unsupported event";
        }
    }
}
