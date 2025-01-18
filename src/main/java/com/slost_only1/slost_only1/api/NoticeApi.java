package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notice")
public class NoticeApi {

    @GetMapping("/announcement")
    public Response<NoticeRes> getAnnouncementUrl() {
        return new Response<>(new NoticeRes("https://yellow-beginner-483.notion.site/13ffda39711380f6b122e068a57ad15c?pvs=4"));
    }

    @GetMapping("/faq")
    public Response<NoticeRes> getFAQUrl() {
        return new Response<>(new NoticeRes("https://yellow-beginner-483.notion.site/13ffda3971138090b9d3d77b29af09d8?pvs=4"));
    }

    @GetMapping("/terms")
    public Response<NoticeRes> getPolicyUrl() {
        return new Response<>(new NoticeRes("https://yellow-beginner-483.notion.site/154fda39711380ae9354c0d81312c2bd?pvs=4"));
    }

    @GetMapping("/privacy")
    public Response<NoticeRes> getPrivacyUrl() {
        return new Response<>(new NoticeRes("https://yellow-beginner-483.notion.site/13ffda39711380728923fd11310af8c9?pvs=4"));
    }

    public record NoticeRes(String url) {
    }
}
