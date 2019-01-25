package com.blibli.ojekonline.controller;

import com.blibli.ojekonline.model.Member;
import com.blibli.ojekonline.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.blibli.ojekonline.controller.API_PATH.API_MEMBER;
import static com.blibli.ojekonline.controller.API_PATH.API_MEMBER_ALL;
import static com.blibli.ojekonline.controller.API_PATH.API_MEMBER_ID;

@RestController
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping(API_MEMBER_ALL)
    public ResponseEntity getAllMember() {
        List<Member> value = memberService.getMemberList();
        BaseResponse<List<Member>> response = new BaseResponse<>();
        response.setData(value);
        response.setCode(HttpStatus.OK.toString());
        response.setStatus("OK");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(API_MEMBER_ID)
    public ResponseEntity getMember(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        try {
            Member member = memberService.getMemberById(id);
            response.setCode(HttpStatus.OK.toString());
            response.setData(member);
            response.setStatus("OK");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setData(null);
            response.setStatus("Fail");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping(API_MEMBER)
    public ResponseEntity insertMember(@RequestBody Member member) {
        Member returned = memberService.saveMember(member);
        if (returned != null) {
            String responseMessage = "success!";
            BaseResponse response = new BaseResponse();
            response.setData(responseMessage);
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.NOT_FOUND.toString());
        response.setStatus("Fail");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PutMapping(API_MEMBER)
    public ResponseEntity updateMember(@RequestBody Member member) {
        Member returned = memberService.saveMember(member);
        BaseResponse response = new BaseResponse();
        if (returned != null) {
            response.setData("Update success!");
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.setData(null);
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setStatus("Fail");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping(API_MEMBER_ID)
    public ResponseEntity deleteMember(@PathVariable int id) {
        String returnMessage = memberService.deleteMemberById(id);
        BaseResponse response = new BaseResponse();
        if(returnMessage != null) {
            response.setData(returnMessage);
            response.setCode(HttpStatus.OK.toString());
            response.setStatus("OK");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else{
            response.setCode(HttpStatus.NOT_FOUND.toString());
            response.setStatus("Fail");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
