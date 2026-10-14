package com.scheduleapp.Service;

import com.scheduleapp.DTO.Request.CreateCommentRequest;
import com.scheduleapp.DTO.Response.CreateCommentResponse;
import com.scheduleapp.DTO.Response.GetCommentResponse;
import com.scheduleapp.Entity.Comment;
import com.scheduleapp.Entity.Schedule;
import com.scheduleapp.Repository.CommentRepository;
import com.scheduleapp.Repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateCommentResponse createComment(Long scheduleId, CreateCommentRequest request) {
        //특정 일정 찾아오기
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        //하나의 일정에는 댓글을 10개까지만 작성할 수 있음
        if(commentRepository.countComment(scheduleId) >= 10){
            throw new IllegalStateException("나의 일정에는 댓글을 10개까지만 작성할 수 있습니다");
        }

        //comment 생성 + DB에 저장
        Comment comment = new Comment(request.getContents(), request.getAuthor(), request.getPassword(), schedule);
        commentRepository.save(comment);

        return new CreateCommentResponse(comment.getId(), comment.getContents(), comment.getAuthor(), comment.getSchedule().getId(), comment.getCreatedAt(), comment.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> getScheduleComments(Long scheduleId) {
        //일정이 존재하는지 조회
        boolean existence = scheduleRepository.existsById(scheduleId);
        if(!existence){
            throw new IllegalStateException("일정이 없습니다.");
        }

        //comment 조회
        List<Comment> comments = commentRepository.findByScehduleId(scheduleId);

        //dto list 형태로 반환
        List<GetCommentResponse> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            GetCommentResponse dto = new GetCommentResponse(comment.getId(),comment.getContents(),comment.getAuthor(),comment.getSchedule().getId(), comment.getCreatedAt(),comment.getModifiedAt());
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> getAllComments() {
        List<Comment> allComments = commentRepository.findAll();
        List<GetCommentResponse> dtos = new ArrayList<>();

        for (Comment comment : allComments) {
            GetCommentResponse dto = new GetCommentResponse(comment.getId(),comment.getContents(),comment.getAuthor(),comment.getSchedule().getId(), comment.getCreatedAt(),comment.getModifiedAt());
            dtos.add(dto);
        }
        return dtos;
    }
}
