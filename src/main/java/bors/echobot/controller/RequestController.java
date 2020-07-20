package bors.echobot.controller;

import bors.echobot.domain.VkEvent;
import bors.echobot.service.EventService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/echobot")
public class RequestController {

    private EventService eventService;

    public RequestController(EventService eventService) {
        this.eventService = eventService;
    }


    @PostMapping("/send")
    public String getVisitorsHistory(@RequestBody VkEvent event) {
        String response = eventService.prepareResponse(event);
        System.out.println(response);
        return response;
    }
}
