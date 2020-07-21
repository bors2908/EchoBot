package bors.echobot.service.impl;

import bors.echobot.domain.VkEvent;
import bors.echobot.domain.VkMessage;
import bors.echobot.domain.WrapperObject;
import bors.echobot.service.EventService;
import bors.echobot.service.UriBuilders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class EventServiceImpl implements EventService {

    @Value("${vk.group-id}")
    private Integer GROUP_ID;
    @Value("${vk.confirmation-token}")
    private String CONFIRM_TOKEN;

    private final String CONFIRMATION = "confirmation";
    private final String NEW_MESSAGE = "message_new";


    private RestTemplate restTemplate;
    private UriBuilders uriBuilders;

    public EventServiceImpl(RestTemplate restTemplate, UriBuilders uriBuilders) {
        this.restTemplate = restTemplate;
        this.uriBuilders = uriBuilders;
    }

    @Override
    public void respond(VkEvent event) {
        String type = event.getType();
        switch (type) {
            case CONFIRMATION:
                if (event.getGroup_id().equals(GROUP_ID)) {
                    return;
                } else throw new RuntimeException("Wrong Group_ID");
            case NEW_MESSAGE:
                sendMessage(event);
                return;
            default:
                throw new RuntimeException("Unsupported event");
        }
    }

    private void sendMessage(VkEvent event) {
        VkMessage message = event.getObject().getMessage();
        String firstname = getFirstname(message);
        String text = firstname + " сказал: " + message.getText();
        String URI = uriBuilders.buildMessageUri(text, message.getPeer_id(), event.getGroup_id());
        restTemplate.getForEntity(URI, String.class);
    }

    private String getFirstname(VkMessage message) {
        String URI = uriBuilders.buildUserNameUri(message.getFrom_id());
        ResponseEntity<WrapperObject> responseEntity = restTemplate.getForEntity(URI, WrapperObject.class);
        return Objects.requireNonNull(responseEntity.getBody()).getResponse().get(0).getFirst_name();
    }
}

