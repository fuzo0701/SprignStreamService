package kr.springstreamservice.convert.controller;

import kr.springstreamservice.convert.service.ConvertService;
import kr.springstreamservice.convert.service.ConvertService2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CovertController {

    private final ConvertService convertService;
    private final ConvertService2 convertService2;



    @ResponseBody
    @PostMapping("/convert/hls/{data}/{filename}")
    public String convertToHls(@PathVariable String data, @PathVariable String filename) {
        convertService.convertToHls(data, filename);
        return "success";
    }

    @PostMapping("/v2/convert/hls/{date}/{filename}")
    public String convertToHls2(
            @PathVariable String date,
            @PathVariable String filename
    ) {
        convertService2.convertToHls(date, filename);
        return "success";
    }
}
