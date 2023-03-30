package com.ssafy.project.api.controller;

import com.ssafy.project.api.service.BookmarkService;
import com.ssafy.project.common.util.Msg;
import com.ssafy.project.common.util.ResponseDTO;
import com.ssafy.project.common.util.provider.AuthProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(tags = {"대본 북마크 API"})
@RequiredArgsConstructor
@RequestMapping(value = "/bookmark")
public class ScriptBookmarkController {

    private final BookmarkService bookmarkService;
    private final AuthProvider authProvider;

    @PostMapping
    public ResponseEntity<ResponseDTO> bookmarkAdd(@RequestParam Long scriptId) {
//        Long userId = authProvider.getUserIdFromPrincipal();
        Long userId = 1L;
        bookmarkService.AddBookmark(userId, scriptId);
       return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_CREATE));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> bookmarkRemove(@RequestParam Long scriptId){
//        Long userId = authProvider.getUserIdFromPrincipal();
        Long userId = 1L;
        bookmarkService.removeBookmark(userId, scriptId);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_DELETE));
    }

    @GetMapping
    @ApiOperation(value = "북마크 여부 확인")
    public ResponseEntity<ResponseDTO> bookmarkCheck(@RequestParam Long scriptId){
//        Long userId = authProvider.getUserIdFromPrincipal();
        Long userId = 1L;
        boolean check = bookmarkService.checkBookmark(userId, scriptId);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, Msg.SUCCESS_READ, check));
    }
}
