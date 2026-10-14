package com.scheduleapp.Service;

import com.scheduleapp.DTO.Request.CreateScheduleRequest;
import com.scheduleapp.DTO.Request.UpdateScheduleRequest;
import com.scheduleapp.DTO.Response.*;
import com.scheduleapp.Entity.Comment;
import com.scheduleapp.Entity.Schedule;
import com.scheduleapp.Repository.CommentRepository;
import com.scheduleapp.Repository.ScheduleRepository;
import com.scheduleapp.Service.UserErrorHandling.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final Validate validate;

    @Transactional
    public CreateScheduleResponse createScehdule(CreateScheduleRequest request) {
        //입력에 대한 검증
        validate.validateSchedule(request.getTitle(), request.getContents(), request.getAuthor(), request.getPassword(), false);

        //일정 생성
        Schedule schedule = new Schedule(request.getTitle(), request.getContents(),request.getAuthor(),request.getPassword());

        //일정 DB에 추가
        scheduleRepository.save(schedule);

        //Create DTO response 반환
        return new CreateScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(),schedule.getAuthor(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long scheduleId) {
        //일정 조회
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다."));

        //댓글 불러오기
        List<Comment> comments = commentRepository.findByScehduleId(scheduleId);

        //dtos 설정
        List<GetCommentResponse> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            GetCommentResponse dto = new GetCommentResponse(comment.getId(),comment.getContents(),comment.getAuthor(),comment.getSchedule().getId(),comment.getCreatedAt(),comment.getModifiedAt());
            dtos.add(dto);
        }

        //Get DTO response 반환
        return new GetScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getAuthor(),schedule.getCreatedAt(), schedule.getModifiedAt(), dtos);
    }

    @Transactional(readOnly = true)
    public List<GetAllScheduleResponse> getAllSchedules(String author) {
        List<Schedule> schedules;
        //author이 포함되어 있으면 author 기준으로 전체 조회
        if(author != null && !author.isBlank()){
            schedules = scheduleRepository.findByAuthor(author);
        }
        else{   //author이 포함되어 있지 않으면 -> 그냥 전체 조회
            schedules = scheduleRepository.findAll();
        }

        //stream 형식으로 DTO list 반환
        List<GetAllScheduleResponse> list = schedules.stream()
                .map(schedule -> new GetAllScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getAuthor(),schedule.getCreatedAt(), schedule.getModifiedAt()))
                .toList();
        return list;
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        //입력에 대한 검증
        validate.validateSchedule(request.getTitle(), "", request.getAuthor(), request.getPassword(), true);

        //일정 조회
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다."));

        //비밀번호 확인
        if(!schedule.getPassword().equals(request.getPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        //update DTO 반환 + 더티체킹
        schedule.update(request.getTitle(), request.getAuthor());
        return new UpdateScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getAuthor(),schedule.getCreatedAt(), schedule.getModifiedAt());
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        //일정이 존재하는지 조회
        boolean existence = scheduleRepository.existsById(scheduleId);
        if(!existence){
            throw new IllegalStateException("일정이 없습니다.");
        }

        //댓글 먼저 삭제
        commentRepository.deleteBySchedule_Id(scheduleId);

        //존재하면 삭제
        scheduleRepository.deleteById(scheduleId);
    }
}
