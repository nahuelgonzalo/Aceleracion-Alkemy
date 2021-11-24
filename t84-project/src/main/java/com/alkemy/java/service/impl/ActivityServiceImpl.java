package com.alkemy.java.service.impl;

import com.alkemy.java.dto.ActivityDTO;
import com.alkemy.java.dto.ActivityRequestDTO;
import com.alkemy.java.exceptions.BadRequestException;
import com.alkemy.java.exceptions.NotFoundException;
import com.alkemy.java.mapper.ActivityMapper;
import com.alkemy.java.model.Activity;
import com.alkemy.java.repository.ActivityRepository;
import com.alkemy.java.service.AWSS3Service;
import com.alkemy.java.service.ActivityService;
import com.alkemy.java.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.util.Locale.getDefault;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private AWSS3Service awss3Service;

    //Metodo que crea una actividad
    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        if (activityDTO.getName() == null || activityDTO.getContent() == null) {
            throw new BadRequestException("Los campos Name y Content no pueden ser nulos");
        }
        Activity activity = activityMapper.toActivity(activityDTO);
        activity.setImage(awss3Service.setImageBase64(activityDTO.getImage()));
        return activityMapper.toDTO(activityRepository.save(activity));
    }

    @Override
    public ActivityRequestDTO updateActivity(ActivityRequestDTO activityRequestDTO, Long id) {
        Activity activity = activityRepository.findByIdAndDeletedFalse(id).orElseThrow(()->new NotFoundException(messageUtil.getMessage("exception.notFound",null,getDefault())));
        activity.setName(activityRequestDTO.getName());
        activity.setContent(activityRequestDTO.getContent());
        activity.setImage(awss3Service.setImageBase64(activityRequestDTO.getImage()));
        return activityMapper.activityToActivityRequestDto(activityRepository.save(activity));
    }
}
