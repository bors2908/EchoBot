package bors.echobot.service.impl;

import bors.echobot.domain.VkEvent;
import bors.echobot.domain.VkMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class VkRemoteService {

    private RestTemplate restTemplate;

    private final String ACCESS_TOKEN = "f614c3c06e9131664cfcd546b8f92f02f250d2070eb496629a0ca38c90bc18aa8eba2b1123dc0aa4183f2";

    public VkRemoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String buildUrl (String message, Integer userId, Integer groupId){
        Random random = new Random();
        StringBuilder urlbuilder = new StringBuilder("https://api.vk.com/method/messages.send?user_id=");
        urlbuilder
                .append(userId)
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

    public void sendMessage (VkEvent event, String text)
    {
        VkMessage message = event.getObject().getMessage();
        restTemplate.getForEntity(buildUrl(text, message.getPeer_id(), event.getGroup_id()), String.class);
    }
}
