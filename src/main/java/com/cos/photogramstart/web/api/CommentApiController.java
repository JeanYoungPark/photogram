package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.handler.ex.CustomVlidationApiException;
import com.cos.photogramstart.service.CommentService;
import com.cos.photogramstart.web.dto.CMResDto;
import com.cos.photogramstart.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<?> commentSave(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Comment comment = commentService.댓글쓰기(commentDto.getContent(), commentDto.getImageId(), principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMResDto<>(1, "댓글쓰기성공", comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<?> commentDelete(@PathVariable long commentId){
        commentService.댓글삭제(commentId);
        return new ResponseEntity<>(new CMResDto<>(1,"댓글삭제성공", null),HttpStatus.OK);
    }

}
