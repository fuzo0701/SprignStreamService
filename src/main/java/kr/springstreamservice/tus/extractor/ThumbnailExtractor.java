package kr.springstreamservice.tus.extractor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
public class ThumbnailExtractor {

    private static final String EXTENSION = "png";
    private static final String DEFAULT_IMAGE_PATH = "classpath:images/default-thumbnail.png";

    public static String extract(File source) throws IOException {
        // 썸네일 파일 생성
        File thumbnail = new File(source.getParent(), source.getName().split("\\.")[0] + "." + EXTENSION);

        // 첫 프레임의 데이터
        try {
            FrameGrab frameGrab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(source));

            // 첫 프레임의 데이터
            frameGrab.seekToSecondPrecise(0);

            Picture picture = frameGrab.getNativeFrame();

            // 썸네일 파일에 복사
            BufferedImage bi = AWTUtil.toBufferedImage(picture);
            ImageIO.write(bi, EXTENSION, thumbnail);

        } catch (Exception e) {
            // 실패했을 경우에 기본 이미지를 사용
            File defaultImage = new File(DEFAULT_IMAGE_PATH);

            try {
                FileUtils.copyFile(defaultImage, thumbnail);
            } catch (Exception ex) {
                log.info("Thumbnail Extract Failed => {}", source.getPath(), e);
            }
        }

        return thumbnail.getName();
    }
}
