package com.alkemy.java.mapper;

import com.alkemy.java.dto.ActivityDTO;
import com.alkemy.java.dto.ActivityRequestDTO;
import com.alkemy.java.model.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    public ActivityRequestDTO activityToActivityRequestDto(Activity activity);
    public Activity activityRequestDtoToActivity(ActivityRequestDTO activityRequestDto);
    public Activity toActivity (ActivityDTO activityDTO);
    public ActivityDTO toDTO(Activity activity);
}
