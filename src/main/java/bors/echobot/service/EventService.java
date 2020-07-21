package bors.echobot.service;

import bors.echobot.domain.VkEvent;

public interface EventService {

    public void respond(VkEvent event);

}
