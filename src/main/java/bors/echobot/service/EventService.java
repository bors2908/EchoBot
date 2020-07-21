package bors.echobot.service;

import bors.echobot.domain.VkEvent;

public interface EventService {

    public String prepareResponse (VkEvent event);

}
