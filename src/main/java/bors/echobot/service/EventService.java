package bors.echobot.service;

import bors.echobot.domain.VkEvent;

public interface EventService {

    public String respond(VkEvent event);

}
