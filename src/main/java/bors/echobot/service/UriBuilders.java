package bors.echobot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UriBuilders {

    @Value("${vk.access-token}")
    private String ACCESS_TOKEN;

    public String buildMessageUri(String message, Integer peerId, Integer groupId) {
        Random random = new Random();
        StringBuilder uribuilder = new StringBuilder("https://api.vk.com/method/messages.send?peer_id=");
        uribuilder
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
        System.out.println(uribuilder.toString());
        return uribuilder.toString();
    }

    public String buildUserNameUri(Integer userId) {
        StringBuilder uribuilder = new StringBuilder("https://api.vk.com/method/users.get?user_ids=")
                .append(userId)
                .append("&name_case=Nom")
                .append("&access_token=")
                .append(ACCESS_TOKEN)
                .append("&v=5.120");
        return uribuilder.toString();
    }
}
