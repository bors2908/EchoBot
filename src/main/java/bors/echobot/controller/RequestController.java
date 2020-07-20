package bors.echobot.controller;

import bors.echobot.domain.VkEvent;
import bors.echobot.domain.VkResponse;
import bors.echobot.service.EventService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/echobot")
public class RequestController {

    private EventService eventService;

    public RequestController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/send")
    public VkResponse getVisitorsHistory(@RequestBody VkEvent event) {
        VkResponse response = eventService.prepareResponse(event);
        System.out.println(response);
        return response;
    }
}
