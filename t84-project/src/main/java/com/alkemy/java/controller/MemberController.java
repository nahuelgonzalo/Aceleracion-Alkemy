package com.alkemy.java.controller;

import com.alkemy.java.dto.MemberRequestDTO;
import com.alkemy.java.dto.MemberResponseDTO;
import com.alkemy.java.dto.PageDTO;
import com.alkemy.java.service.MemberService;
import com.alkemy.java.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static java.util.Locale.getDefault;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MessageUtil messageUtil;

    private static final String ALL_USERS_OPERATION_NOTE = "Accessible for all users";

    @ApiOperation(value = "Return paginated members", notes = ALL_USERS_OPERATION_NOTE)
    @GetMapping
    public PageDTO<MemberResponseDTO> getAllMembers(@ApiParam("Page number to return")@RequestParam (defaultValue = "1") Integer page) {
        return memberService.findAll(page);
    }

    @ApiOperation(value = "Created new member", notes = ALL_USERS_OPERATION_NOTE)
    @PostMapping
    public  ResponseEntity<MemberResponseDTO> saveMember(@ApiParam("Member that needs to be added")@RequestBody MemberRequestDTO memberRequestDTO){
        return new ResponseEntity<>(memberService.save(memberRequestDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deleted member for id", notes = ALL_USERS_OPERATION_NOTE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@ApiParam("Member ID you want to delete")@PathVariable Long id){
        memberService.deleteById(id);
        return new ResponseEntity<>(messageUtil.getMessage("successful.delete",null, getDefault()), HttpStatus.OK);
    }

    @ApiOperation(value = "Update member for id", notes = ALL_USERS_OPERATION_NOTE)
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> updateMember(@RequestBody @Valid MemberRequestDTO memberRequestDTO,@ApiParam("Member ID you want to update") @PathVariable Long id ){
        return new ResponseEntity<>(memberService.updateMember(memberRequestDTO,id), HttpStatus.OK);
    }

}
