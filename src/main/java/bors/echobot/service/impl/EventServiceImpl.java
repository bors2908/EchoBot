package bors.echobot.service.impl;

import bors.echobot.domain.VkEvent;
import bors.echobot.domain.VkResponse;
import bors.echobot.service.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public VkResponse prepareResponse(VkEvent event) {
        return new VkResponse(event.getType(),event.getObject(),event.getGroup_id(),event.getSecret());
    }
}
