package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.ParticipantAddReqDTO;
import com.ssafy.project.common.db.entity.common.Participant;
import com.ssafy.project.common.db.entity.common.Script;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.repository.ParticipantRepository;
import com.ssafy.project.common.db.repository.ScriptRepository;
import com.ssafy.project.common.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final ScriptRepository scriptRepository;

    @Override
    public void addParticipant(ParticipantAddReqDTO participantAddReqDTO) {
        Script script = scriptRepository.findById(participantAddReqDTO.getScriptId()).get();
        User user = userRepository.findById(participantAddReqDTO.getUserId()).get();

        Participant participant = Participant.builder()
                .script(script)
                .user(user)
                .participateDate(LocalDateTime.now())
                .build();

        //user에 넣어서 저장하는게 나을 지도... casecade로 하는게 나을지도
        participantRepository.save(participant);
    }
}
