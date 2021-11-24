package com.alkemy.java.controller;

import com.alkemy.java.dto.ActivityDTO;
import com.alkemy.java.dto.ActivityRequestDTO;
import com.alkemy.java.repository.ActivityRepository;
import com.alkemy.java.service.ActivityService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityRepository activityRepositoryMock = Mockito.mock(ActivityRepository.class);

    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {
        return new ResponseEntity<>(activityService.createActivity(activityDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityRequestDTO> updateActivity(@Valid @RequestBody ActivityRequestDTO activityRequestDTO, @PathVariable Long id) {
        return new ResponseEntity<>(activityService.updateActivity(activityRequestDTO, id), HttpStatus.OK);
    }
}
