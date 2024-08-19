package com.recrutaibackend.controller;

import com.recrutaibackend.dto.MemberRequest;
import com.recrutaibackend.dto.MemberResponse;
import com.recrutaibackend.mappers.MemberMapper;
import com.recrutaibackend.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService service;
    private final MemberMapper memberMapper;

    MemberController(MemberService service, MemberMapper memberMapper) {
        this.service = service;
        this.memberMapper = memberMapper;
    }

    @PostMapping
    ResponseEntity<MemberResponse> create(@RequestBody @Valid MemberRequest request) {
        var member = memberMapper.mapToResponse(service.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }
}
