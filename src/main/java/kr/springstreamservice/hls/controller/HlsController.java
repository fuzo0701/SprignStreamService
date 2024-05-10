package kr.springstreamservice.hls.controller;

import kr.springstreamservice.hls.service.HlsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
@RequiredArgsConstructor
public class HlsController {

    private final HlsService hlsService;

    @RequestMapping("/hls/view")
    public String view() {
        return "hls_player";
    }

    @ResponseBody
    @RequestMapping("/hls/{key}/{filename}")
    public ResponseEntity<InputStreamResource> getHlsFile(@PathVariable("key") String key, @PathVariable("filename") String filename) throws FileNotFoundException {
        File file = hlsService.getHlsFile(key, filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/x-mpegURL"))
                .body(resource);
    }
}
