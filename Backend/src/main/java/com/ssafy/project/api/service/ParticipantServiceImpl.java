package com.ssafy.project.api.service;

import com.ssafy.project.common.db.dto.request.ParticipantAddReqDTO;
import com.ssafy.project.common.db.entity.common.Participant;
import com.ssafy.project.common.db.entity.common.Script;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.repository.ScriptRepository;
import com.ssafy.project.common.db.repository.UserRepository;
import com.ssafy.project.common.provider.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantServiceImpl implements ParticipantService {

    private final UserRepository userRepository;
    private final ScriptRepository scriptRepository;
    private final AuthProvider authProvider;

    @Override
    public void addParticipant(ParticipantAddReqDTO participantAddReqDTO) {
        Script script = scriptRepository.findById(participantAddReqDTO.getScriptId()).get();
        User user = userRepository.findById(authProvider.getUserIdFromPrincipal()).get();

        Participant participant = Participant.builder()
                .script(script)
                .user(user)
                .participateDate(LocalDateTime.now())
                .build();
        user.getParticipants().add(participant);
        userRepository.save(user);
    }
}
