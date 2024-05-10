package kr.springstreamservice.hls.controller;

import kr.springstreamservice.hls.service.HlsServiceV2;
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
public class HlsControllerV2 {

    private final HlsServiceV2 hlsServiceV2;

    // master.m3u8 경로
    @ResponseBody
    @RequestMapping("/v2/hls/{key}/{filename}")
    public ResponseEntity<InputStreamResource> getMaster(
            @PathVariable String key,
            @PathVariable String filename
    ) throws FileNotFoundException {
        File file = hlsServiceV2.getHlsFileV2(key, filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/x-mpegURL"))
                .body(resource);
    }

    // 각 화질별 ts, m3u8 경로
    @ResponseBody
    @RequestMapping("/v2/hls/{key}/{resolution}/{filename}")
    public ResponseEntity<InputStreamResource> getPlaylist(
            @PathVariable String key,
            @PathVariable String resolution,
            @PathVariable String filename
    ) throws FileNotFoundException {
        File file = hlsServiceV2.getHlsFileV2(key, resolution, filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/x-mpegURL"))
                .body(resource);
    }
}
