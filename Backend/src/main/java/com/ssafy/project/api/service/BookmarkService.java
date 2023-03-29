package com.ssafy.project.api.service;

public interface BookmarkService {

    public void AddBookmark(Long userId, Long scriptId);
    public void removeBookmark(Long userId, Long scriptId);
}
