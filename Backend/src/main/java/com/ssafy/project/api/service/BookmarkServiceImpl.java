package com.ssafy.project.api.service;

import com.ssafy.project.common.db.entity.common.Bookmark;
import com.ssafy.project.common.db.entity.common.Script;
import com.ssafy.project.common.db.entity.common.User;
import com.ssafy.project.common.db.repository.BookmarkRepository;
import com.ssafy.project.common.db.repository.ScriptRepository;
import com.ssafy.project.common.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final ScriptRepository scriptRepository;

    @Override
    public void AddBookmark(Long userId, Long scriptId) {

        User user = userRepository.findById(userId).get();
        Script script = scriptRepository.findById(scriptId).get();

        //뭔가 유저에 생명주기....해서 user list에서 더해주는걸로 하는 걸....
        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .script(script)
                .build();

        script.setBookmarkCnt(script.getBookmarkCnt()+1L);
        scriptRepository.save(script);
        //유저에 넣고 save 하기
    }

    @Override
    public void removeBookmark(Long userId, Long scriptId) {

    }
}
