package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.VideoCreateReqDTO;
import com.ssafy.project.common.db.dto.request.VideoDeleteReqDTO;
import com.ssafy.project.common.db.dto.response.VideoListResDTO;
import com.ssafy.project.common.db.entity.common.Participant;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.entity.common.Video;
import com.ssafy.project.common.db.repository.ParticipantRepository;
import com.ssafy.project.common.db.repository.UserRepository;
import com.ssafy.project.common.db.repository.VideoRepository;
import com.ssafy.project.common.provider.AuthProvider;
import com.ssafy.project.common.provider.S3Provider;
import com.ssafy.project.common.security.exception.BadRequestException;
import com.ssafy.project.common.security.exception.CustomAuthException;
import com.ssafy.project.common.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService{

    private final S3Provider s3Provider;
    private final VideoRepository videoRepository;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final AuthProvider authProvider;
    private final FileUtils fileUtils;

    @Transactional
    @Override
    public void saveVideo(VideoCreateReqDTO videoCreateReqDTO) throws IOException {

        Participant participant = participantRepository.findById(videoCreateReqDTO.getParticipantId())
                .orElseThrow(() -> new BadRequestException("존재하지 않는 참여자 입니다."));

        User user = userRepository.findById(authProvider.getUserIdFromPrincipal())
                .orElseThrow(() -> new CustomAuthException("존재하지 않는 회원입니다."));

            MultipartFile videoFile = videoCreateReqDTO.getFile();
            String baseUri = fileUtils.makeUri(videoFile);
            String videoUri = fileUtils.getVideoUri(baseUri);
            String thumbnailUri = fileUtils.getThumbnailUri(baseUri);
            File thumbnailFile = fileUtils.makeThumbnail(videoFile, thumbnailUri);


            videoRepository.save(Video.builder()
                            .title(videoCreateReqDTO.getTitle())
                            .videoUrl(s3Provider.uploadMultipartFile(videoFile, videoUri))
                            .thumbnailUrl(s3Provider.uploadFile(thumbnailFile, thumbnailUri))
                            .analysis(videoCreateReqDTO.getAnalysis())
                            .user(user)
                            .participant(participant)
                            .build());
    }

    @Transactional
    @Override
    public void deleteVideo(VideoDeleteReqDTO videoDeleteReqDTO) {

        Video video = videoRepository.findByParticipantId(videoDeleteReqDTO.getParticipantId())
                .orElseThrow(() -> new BadRequestException("존재하지 않는 영상입니다."));

        s3Provider.delete(video.getVideoUrl(), fileUtils.getVideoRemoveStartIdx());
        s3Provider.delete(video.getThumbnailUrl(), fileUtils.getThumbnailRemoveStartIdx());
        videoRepository.delete(video);
    }

    @Transactional
    public Page<VideoListResDTO> findAllVideo(int page) {

        final int SIZE = 10;
        final String SORT = "createdDate";

        Page<Video> videos = videoRepository.findAllByUserId(authProvider.getUserIdFromPrincipal(),
                PageRequest.of(page, SIZE, Sort.by(SORT).descending()));

        return videos.map(video -> VideoListResDTO.builder()
                .id(video.getId())
                .title(video.getTitle())
                .thumbnailUrl(video.getThumbnailUrl())
                .analysis(video.getAnalysis())
                .createdDate(video.getCreatedDate())
                .build());
    }
}
