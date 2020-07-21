package bors.echobot.service.impl;

import bors.echobot.domain.VkEvent;
import bors.echobot.domain.VkMessage;
import bors.echobot.domain.WrapperObject;
import bors.echobot.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class EventServiceImpl implements EventService {

    private final String CONFIRM_TOKEN = "34004707";
    private final Integer GROUP_ID = 197280082;
    private final String ACCESS_TOKEN = "f614c3c06e9131664cfcd546b8f92f02f250d2070eb496629a0ca38c90bc18aa8eba2b1123dc0aa4183f2";

    private final String CONFIRMATION = "confirmation";
    private final String NEW_MESSAGE = "message_new";


    private RestTemplate restTemplate;


    public EventServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
                sendMessage(event);
                return event.toString();
            default:
                throw new RuntimeException("Unsupported event");
        }
    }

    private void sendMessage(VkEvent event) {
        VkMessage message = event.getObject().getMessage();
        String firstname = getFirstname(message);
        String text = firstname + " сказал: " + message.getText();
        restTemplate.getForEntity(buildMessageUrl(text, message.getPeer_id(), event.getGroup_id()), String.class);
    }

    private String getFirstname(VkMessage message) {
        ResponseEntity<WrapperObject> responseEntity = restTemplate.getForEntity(buildUserNameUrl(message.getFrom_id()), WrapperObject.class);
        return responseEntity.getBody().getResponse().get(0).getFirst_name();
    }

    private String buildMessageUrl(String message, Integer peerId, Integer groupId) {
        Random random = new Random();
        StringBuilder urlbuilder = new StringBuilder("https://api.vk.com/method/messages.send?peer_id=");
        urlbuilder
                .append(peerId)
                .append("&random_id=")
                .append(random.nextLong())
                .append("&message=")
                .append(message)
                .append("&group_id=")
                .append(groupId)
                .append("&intent=default")
                .append("&access_token=")
                .append(ACCESS_TOKEN)
                .append("&v=5.120");
        System.out.println(urlbuilder.toString());
        return urlbuilder.toString();
    }

    private String buildUserNameUrl(Integer userId) {
        StringBuilder urlbuilder = new StringBuilder("https://api.vk.com/method/users.get?user_ids=")
                .append(userId)
                .append("&name_case=Nom")
                .append("&access_token=")
                .append(ACCESS_TOKEN)
                .append("&v=5.120");
        return urlbuilder.toString();
    }
}

