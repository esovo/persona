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

        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .script(script)
                .build();

        user.getBookmarks().add(bookmark);
        userRepository.save(user);
    }

    @Override
    public void removeBookmark(Long userId, Long scriptId) {
        bookmarkRepository.deleteByUserIdAndScriptId(userId, scriptId);
    }

    @Override
    public boolean checkBookmark(Long userId, Long scripId) {
        return bookmarkRepository.existsScriptByUserIdAndScriptId(userId, scripId);
    }
}
