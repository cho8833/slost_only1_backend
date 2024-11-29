package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatApi {
    private final ChatService chatService;

    @PostMapping("/ask-dolbom")
    public Response<?> askDolbom(@RequestParam Long dolbomId) {
        chatService.askDolbom(dolbomId);
        return Response.SUCCESS;
    }
}
