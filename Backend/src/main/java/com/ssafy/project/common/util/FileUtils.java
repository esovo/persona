package com.ssafy.project.common.util;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
public class FileUtils {

    public final String IMAGE_DIR_PATH = "img/";
    public final String IMAGE_PREFIX = "s_";
    public final String VIDEO_DIR_PATH = "video/";

    public File makeThumbnail(MultipartFile videoFile, String thumbnailUri) throws IOException {

        File thumbnailFile = new File(thumbnailUri);
        OutputStream os = new FileOutputStream(thumbnailFile);

        Thumbnails.of(videoFile.getInputStream())
                .sourceRegion(Positions.CENTER, 100, 200)
                .size(100, 200)
                .toOutputStream(os);

        os.flush();
        os.close();
        return thumbnailFile;
    }

    public String makeUri(MultipartFile file) {
        StringBuilder sb = new StringBuilder();

        // S3에 특수문자 인코딩 이슈가 생길 수 있어 정규식으로 _로 변환
        sb.append(UUID.randomUUID())
          .append("_")
          .append(Objects.requireNonNull(file.getOriginalFilename())
          .replaceAll("[~!@#$%^&*()_+ ]", "_"));

        return sb.toString();
    }

    public String getVideoUri(String uri) { return VIDEO_DIR_PATH + uri; };

    public String getThumbnailUri(String uri) { return IMAGE_DIR_PATH + IMAGE_PREFIX + uri; };

    public int getThumbnailRemoveStartIdx() {return IMAGE_DIR_PATH.length() + IMAGE_PREFIX.length(); };

    public int getVideoRemoveStartIdx() {return VIDEO_DIR_PATH.length(); };
}
