package bors.echobot.service;

import bors.echobot.domain.VkEvent;
import bors.echobot.domain.VkResponse;

public interface EventService {

    public VkResponse prepareResponse (VkEvent event);

}
