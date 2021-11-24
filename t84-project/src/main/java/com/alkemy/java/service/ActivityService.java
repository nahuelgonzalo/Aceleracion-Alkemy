package com.alkemy.java.service;

import com.alkemy.java.dto.ActivityDTO;
import com.alkemy.java.dto.ActivityRequestDTO;

public interface ActivityService {

    ActivityDTO createActivity(ActivityDTO activityDTO);
    ActivityRequestDTO updateActivity(ActivityRequestDTO activityRequestDTO, Long id);
}
