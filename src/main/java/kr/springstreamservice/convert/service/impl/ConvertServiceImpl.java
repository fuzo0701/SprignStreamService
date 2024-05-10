package kr.springstreamservice.convert.service.impl;

import kr.springstreamservice.convert.service.ConvertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFmpegUtils;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.progress.Progress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConvertServiceImpl implements ConvertService {
    private final FFmpeg fFmpeg;
    private final FFprobe fFprobe;

    @Value("${tus.save.path}")
    private String savedPath;

    @Value("${tus.output.path.hls}")
    private String hlsOutputPath;

    @Value("${tus.output.path.mp4}")
    private String mp4OutputPath;

    @Override
    public void convertToHls(String date, String filename) {
        String path = savedPath + "/" + date + "/" + filename;
        File output = new File(hlsOutputPath + "/" + filename.split("\\.")[0]);

        if (!output.exists()) {
            output.mkdirs();
        }

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(path) // 입력 소스
                .overrideOutputFiles(true)
                .addOutput(output.getAbsolutePath() + "/master.m3u8") // 출력 위치
                .setFormat("hls")
                .addExtraArgs("-hls_time", "10") // 10초
                .addExtraArgs("-hls_list_size", "0")
                .addExtraArgs("-hls_segment_filename", output.getAbsolutePath() + "/master_%08d.ts") // 청크 파일 이름
                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
                .done();

        run(builder);
    }

    private void run(FFmpegBuilder builder) {
        FFmpegExecutor executor = new FFmpegExecutor(fFmpeg, fFprobe);
        executor.createJob(builder).run();
//        executor
//                .createJob(builder, progress -> {
//                    log.info("progress ==> {}", progress);
//                    if (progress.status.equals(Progress.Status.END)) {
//                        log.info("================================= JOB FINISHED =================================");
//                    }
//                })
//                .run();
    }
}
