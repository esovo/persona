package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.SpeechAddReqDTO;
import com.ssafy.project.common.db.entity.common.Participant;
import com.ssafy.project.common.db.entity.common.Speech;
import com.ssafy.project.common.db.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpeechServiceImpl implements SpeechService {

    private final ParticipantRepository participantRepository;

    @Override
    public void addSpeech(SpeechAddReqDTO speechAddReqDTO) {
        Participant participant = participantRepository.findById(speechAddReqDTO.getParticipantId()).get();

        Speech speech = Speech.builder()
                .participant(participant)
                .speechRecognition(speechAddReqDTO.getSpeechRecognition())
                .build();

        participant.getSpeeches().add(speech);
        participantRepository.save(participant);
    }
}
