package bors.echobot.service.impl;

import bors.echobot.domain.VkEvent;
import bors.echobot.domain.VkMessage;
import bors.echobot.service.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private final String CONFIRM_TOKEN = "34004707";
    private final Integer GROUP_ID = 197280082;
    private final String CONFIRMATION = "confirmation";
    private final String NEW_MESSAGE = "message_new";

    private VkRemoteService vkRemoteService;

    public EventServiceImpl(VkRemoteService vkRemoteService) {
        this.vkRemoteService = vkRemoteService;
    }

    @Override
    public String prepareResponse(VkEvent event) {
        String type = event.getType();
        switch (type) {
            case CONFIRMATION:
                if (event.getGroup_id().equals(GROUP_ID)) {
                    return CONFIRM_TOKEN;
                } else throw new RuntimeException("Wrong Group_ID");
            case NEW_MESSAGE:
                String text = event.getObject().getMessage().getText();
                vkRemoteService.sendMessage(event, text);
                return event.toString();
            default:
                return "Unsupported event";
        }
    }
}
