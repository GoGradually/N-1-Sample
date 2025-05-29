package com.example.nplusone.practice.controller;

import com.example.nplusone.practice.dto.ParentDto;
import com.example.nplusone.practice.service.PracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PracticeController {
    private final PracticeService practiceService;

    @GetMapping("/no")
    public List<ParentDto> noFetch() {
        return practiceService.getParentsByNormal().stream()
                .map(ParentDto::new)
                .toList();
    }
    @GetMapping("/fetch")
    public List<ParentDto> fetch() {
        return practiceService.getParentsByFetch().stream()
                .map(ParentDto::new)
                .toList();
    }

    @PostMapping("/post/parent")
    public void postParent() {
        practiceService.makeParent();
    }
    @PostMapping("/post/child")
    public void postChild() {
        practiceService.makeChild();
    }
}
