package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.VideoCreateReqDTO;
import com.ssafy.project.common.db.dto.response.VideoDetailResDTO;
import com.ssafy.project.common.db.dto.response.VideoListResDTO;
import com.ssafy.project.common.db.entity.common.Participant;
import com.ssafy.project.common.db.entity.common.Script;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.entity.common.Video;
import com.ssafy.project.common.db.repository.ParticipantRepository;
import com.ssafy.project.common.db.repository.ScriptRepository;
import com.ssafy.project.common.db.repository.UserRepository;
import com.ssafy.project.common.db.repository.VideoRepository;
import com.ssafy.project.common.provider.AuthProvider;
import com.ssafy.project.common.provider.S3Provider;
import com.ssafy.project.common.security.exception.CommonApiException;
import com.ssafy.project.common.security.exception.CommonErrorCode;
import com.ssafy.project.common.security.exception.CustomAuthException;
import com.ssafy.project.common.util.S3Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final S3Provider s3Provider;
    private final VideoRepository videoRepository;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final ScriptRepository scriptRepository;
    private final AuthProvider authProvider;
    private final S3Utils s3Utils;

    @Transactional
    @Override
    public void saveVideo(VideoCreateReqDTO videoCreateReqDTO) {

        Participant participant = participantRepository.findById(videoCreateReqDTO.getParticipantId())
                .orElseThrow(() -> new CustomAuthException("존재하지 않는 참여자 입니다."));

        User user = userRepository.findById(authProvider.getUserIdFromPrincipal())
                .orElseThrow(() -> new CustomAuthException("존재하지 않는 회원입니다."));

        List<String> uris = new ArrayList<>();
        
        uris = s3Utils.upload(videoCreateReqDTO.getVideoFile(), videoCreateReqDTO.getGraphFile());

        videoRepository.save(Video.builder()
                .title(videoCreateReqDTO.getTitle())
                .videoUrl(uris.get(0))
                .thumbnailUrl(uris.get(1))
                .graphUrl(uris.get(2))
                .analysis(videoCreateReqDTO.getAnalysis())
                .user(user)
                .participant(participant)
                .build());
    }

    @Transactional
    @Override
    public void deleteVideo(Long videoId) {

        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new CustomAuthException("존재하지 않는 영상입니다."));

        if (!(video.getUser().getId() == authProvider.getUserIdFromPrincipal())) {
            throw new CustomAuthException("다른 유저의 동영상입니다.");
        }

        s3Provider.delete(video.getVideoUrl(), s3Utils.getVideoRemoveStartIdx());
        s3Provider.delete(video.getThumbnailUrl(), s3Utils.getThumbnailRemoveStartIdx());
        videoRepository.delete(video);
    }

    @Transactional
    @Override
    public Page<VideoListResDTO> findAllVideo(int page) {

        final int SIZE = 10;
        final String SORT = "createdDate";

        Page<Video> videos = videoRepository.findAllByUserId(authProvider.getUserIdFromPrincipal(),
                PageRequest.of(page, SIZE, Sort.by(SORT).descending()));

        return videos.map(video -> VideoListResDTO.builder()
                .id(String.valueOf(video.getId()))
                .title(video.getTitle())
                .thumbnailUrl(video.getThumbnailUrl())
                .analysis(video.getAnalysis())
                .createdDate(video.getCreatedDate().toString())
                .build());
    }

    @Transactional
    @Override
    public VideoDetailResDTO detailVideo(Long videoId) {

        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new CommonApiException(CommonErrorCode.VIDEO_NOT_FOUND));

        if (!(video.getUser().getId() == authProvider.getUserIdFromPrincipal())) {
            throw new CustomAuthException("다른 유저의 동영상입니다.");
        }

        Script script = scriptRepository.findByParticipantsId(video.getParticipant().getId())
                .orElseThrow(() -> new CommonApiException(CommonErrorCode.SCRIPT_NOT_FOUND));

        return VideoDetailResDTO.builder()
                .title(video.getTitle())
                .emotion(script.getEmotion().name())
                .genre(script.getGenre().name())
                .actor(script.getActor())
                .author(script.getAuthor())
                .videoUrl(video.getVideoUrl())
                .graphUrl(video.getGraphUrl())
                .createdDate(video.getCreatedDate().toString())
                .build();
    }
}
