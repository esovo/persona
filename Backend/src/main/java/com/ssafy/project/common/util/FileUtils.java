package com.ssafy.project.common.util;

import com.ssafy.project.common.security.exception.CommonApiException;
import com.ssafy.project.common.security.exception.CommonErrorCode;
import lombok.extern.log4j.Log4j2;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
public class FileUtils {

    private final String IMAGE_PREFIX = "s_";
    private final String GRAPH_PREFIX = "g_";
    private final String IMAGE_DIR_PATH = "img/";
    private final String GRAPH_DIR_PATH = "graph/";
    private final String VIDEO_DIR_PATH = "video/";

    public final String IMAGE_PNG_FORMAT = ".PNG";

    @Value("${spring.servlet.multipart.location}")
    private String EC2_DIR_PATH;

    public File makeThumbnail(MultipartFile videoFile, String videoUri, String thumbnailUri) {

        try {
            File thumbnailFile = new File(EC2_DIR_PATH + thumbnailUri);
            File newVideoFile = new File(EC2_DIR_PATH + videoUri);
            log.info(thumbnailFile.getAbsolutePath());
            log.info(newVideoFile.getAbsolutePath());
            newVideoFile.createNewFile();
            videoFile.transferTo(newVideoFile);
            int frameNumber = 0;

            Picture picture = FrameGrab.getFrameFromFile(newVideoFile, frameNumber);

            BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
            ImageIO.write(bufferedImage, IMAGE_PNG_FORMAT, thumbnailFile);
            return thumbnailFile;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new CommonApiException(CommonErrorCode.FILE_NOT_VALID);
        }

//        log.info(LOCAL_DIR_PATH + IMAGE_DIR_PATH + videoFile.getOriginalFilename());
//        log.info(LOCAL_DIR_PATH + thumbnailUri);
//        String[] cmd = new String[]{"ffmpeg\", \"-i\", \"C:/Users/SSAFY/Desktop/KakaoTalk_20230330_105915882.mp4", "-ss", "00:00:01.000", "-vframes", "1", LOCAL_DIR_PATH + thumbnailUri};
//        Process p = Runtime.getRuntime().exec(cmd);
//
//        return new File(thumbnailUri);


//        Thumbnails.of(videoFile.getInputStream())
//                .sourceRegion(Positions.CENTER, 300, 300)
//                .size(300, 300)
//                .toFile(new File (LOCAL_DIR_PATH + thumbnailUri));
    }

    public String makeUri(MultipartFile file) {
        StringBuilder sb = new StringBuilder();

        sb.append(UUID.randomUUID())
          .append("_")
          .append(Objects.requireNonNull(file.getOriginalFilename())
          .replaceAll("[~!@#$%^&*()_+ ]", "_"));

        return sb.toString();
    }

    public String getVideoUri(String uri) { return VIDEO_DIR_PATH + uri; };

    public String getThumbnailUri(String uri) { return IMAGE_DIR_PATH + IMAGE_PREFIX + uri; };

    public String getGraphUri(String uri) { return GRAPH_DIR_PATH + GRAPH_PREFIX + uri; };

    public int getThumbnailRemoveStartIdx() {return IMAGE_DIR_PATH.length() + IMAGE_PREFIX.length(); };

    public int getVideoRemoveStartIdx() {return VIDEO_DIR_PATH.length(); };
}
