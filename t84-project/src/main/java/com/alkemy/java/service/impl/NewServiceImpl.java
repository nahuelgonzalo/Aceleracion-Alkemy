package com.alkemy.java.service.impl;

import com.alkemy.java.dto.CommentResponseDTO;
import com.alkemy.java.dto.NewRequestDTO;
import com.alkemy.java.dto.NewResponseDTO;
import com.alkemy.java.dto.PageDTO;
import com.alkemy.java.exceptions.BadRequestException;
import com.alkemy.java.exceptions.NotFoundException;
import com.alkemy.java.mapper.CommentMapper;
import com.alkemy.java.mapper.NewMapper;
import com.alkemy.java.model.Category;
import com.alkemy.java.model.News;
import com.alkemy.java.repository.CategoryRepository;
import com.alkemy.java.repository.CommentRepository;
import com.alkemy.java.repository.NewsRepository;
import com.alkemy.java.service.AWSS3Service;
import com.alkemy.java.service.NewService;
import com.alkemy.java.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.alkemy.java.util.MessageUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Locale.getDefault;

@Service
public class NewServiceImpl implements NewService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private NewMapper newMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private PageUtil pageUtil;

    @Autowired
    private AWSS3Service awss3Service;

    @Override
    public NewResponseDTO findById(Long id) {
        News n = newsRepository.findById(id).orElseThrow(() ->
                new NotFoundException(messageUtil.getMessage("exception.notFound", null, getDefault()))
        );
        return newMapper.toDto(n);
    }

    @Override
    public PageDTO<NewResponseDTO> findAll(Integer page) {
        if (page <= 0)
            throw new IllegalArgumentException(messageUtil.getMessage("exception.pageDTO",null, getDefault()));
        Page<News> entity = newsRepository.findAllByDeletedFalse(pageUtil.pageRequest(page));
        if (entity.getTotalPages() != 0 && page > entity.getTotalPages())
            throw new IllegalArgumentException(messageUtil.getMessage("exception.pageDTO.getTotal", new Object[]{entity.getTotalPages()},getDefault()));
        List<NewResponseDTO> newDto = newMapper.toNewDTOList(entity.getContent());
        PageDTO<NewResponseDTO> pageDTO = new PageDTO<>();
        pageDTO.setContent(newDto);
        pageDTO.setTotalPages(entity.getTotalPages());
        if (entity.hasNext()) {
            pageDTO.setNextUrl(pageUtil.getNextUrl(page));
        }
        if (entity.hasPrevious()) {
            pageDTO.setPreviousUrl(pageUtil.getPreviousUrl(page));
        }
        return pageDTO;
    }

    @Override
    @Transactional
    public void deleteNewsById(Long id) {
        if (!newsRepository.existsById(id))
            throw new NotFoundException(messageUtil
                    .getMessage("exception.notFound.new", new Long[]{id}, getDefault()));
        newsRepository.deleteNewsById(id);
    }

    @Override
    public NewResponseDTO save(NewRequestDTO newDTO) {
        News news;
        //if it can't be mapped throw a BadRequest
        try {
            news = newMapper.toNews(newDTO);
            news.setImage(awss3Service.setImageBase64(newDTO.getImage()));
        } catch (NotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }
        newsRepository.save(news);
        return newMapper.toDto(news);
    }

    @Override
    public NewResponseDTO updateNew(NewRequestDTO newDTO, Long id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException(messageUtil
                .getMessage("exception.notFound.new", new Long[]{id}, getDefault())));
        //if it can't be update throw a BadRequest
        try {
            newMapper.updateFromDTO(newDTO, news);
            news.setImage(awss3Service.setImageBase64(newDTO.getImage()));
        } catch (NotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }
        return newMapper.toDto(newsRepository.save(news));
    }

    @Override
    public List<CommentResponseDTO> listComment(Long id) {
        return commentMapper.listToDto(commentRepository.findCommentsByNewsId(id));
    }
}