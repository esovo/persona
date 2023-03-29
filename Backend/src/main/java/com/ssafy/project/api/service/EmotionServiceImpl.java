package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.EmotionAddReqDTO;
import com.ssafy.project.common.db.entity.common.Emotion;
import com.ssafy.project.common.db.entity.common.Participant;
import com.ssafy.project.common.db.repository.EmotionRepository;
import com.ssafy.project.common.db.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmotionServiceImpl implements EmotionService {

    private final EmotionRepository emotionRepository;
    private final ParticipantRepository participantRepository;

    @Override
    public void addEmotion(EmotionAddReqDTO emotionAddReqDTO) {
        Participant participant = participantRepository.getById(emotionAddReqDTO.getParticipantId());
        Emotion emotion = Emotion.builder()
                .participant(participant)
                .time(emotionAddReqDTO.getTime())
                .pleasure(emotionAddReqDTO.getPleasure())
                .embarrassed(emotionAddReqDTO.getEmbarrassed())
                .anger(emotionAddReqDTO.getAnger())
                .anxiety(emotionAddReqDTO.getAnxiety())
                .hurt(emotionAddReqDTO.getHurt())
                .sad(emotionAddReqDTO.getSad())
                .neutrality(emotionAddReqDTO.getNeutrality())
                .build();

        participant.getEmotions().add(emotion);
        participantRepository.save(participant);
    }
}
