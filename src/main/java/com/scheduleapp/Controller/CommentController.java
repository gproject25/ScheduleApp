package com.scheduleapp.Controller;

import com.scheduleapp.DTO.Request.CreateCommentRequest;
import com.scheduleapp.DTO.Response.CreateCommentResponse;
import com.scheduleapp.DTO.Response.GetCommentResponse;
import com.scheduleapp.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(@PathVariable Long scheduleId, @RequestBody CreateCommentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(scheduleId, request));
    }

    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<GetCommentResponse>> getScheduleComments(@PathVariable Long scheduleId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getScheduleComments(scheduleId));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<GetCommentResponse>> getAllComments(){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllComments());
    }
}
