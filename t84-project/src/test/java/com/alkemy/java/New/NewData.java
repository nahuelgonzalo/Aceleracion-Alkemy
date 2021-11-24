package com.alkemy.java.New;

import com.alkemy.java.dto.CategoryResponseDTO;
import com.alkemy.java.dto.NewRequestDTO;
import com.alkemy.java.dto.NewResponseDTO;
import com.alkemy.java.dto.PageDTO;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class NewData {

    public static final long INITIAL_ID = 1L;
    public static final String INITIAL_NAME = "New";
    public static final String INITIAL_CONTENT = "Content";
    public static final String INITIAL_IMAGE = "Image.png";
    public static final CategoryResponseDTO categoryResponse = new CategoryResponseDTO();

    public static final String UPDATED_NAME = "Updated New";
    public static final String UPDATED_CONTENT = "Updated content";
    public static final String UPDATED_IMAGE = "UpdatedImage.png";

    public static final int INITIAL_TOTALPAGES = 1;
    public static final long NONEXISTENT_ID = 402859203L;

    public static NewResponseDTO initialNewDTO() {
        CategoryResponseDTO category = new CategoryResponseDTO();
        NewResponseDTO initialNewDTO = new NewResponseDTO();
        initialNewDTO.setId(INITIAL_ID);
        initialNewDTO.setName(INITIAL_NAME);
        initialNewDTO.setContent(INITIAL_CONTENT);
        initialNewDTO.setImage(INITIAL_IMAGE);
        initialNewDTO.setCategory(category);
        return initialNewDTO;
    }

    public static NewRequestDTO initialNewResquestDTO() {
        NewRequestDTO initialNewDTO = new NewRequestDTO();
        initialNewDTO.setName(INITIAL_NAME);
        initialNewDTO.setContent(INITIAL_CONTENT);
        initialNewDTO.setImage(INITIAL_IMAGE);
        initialNewDTO.setIdCategory(INITIAL_ID);
        return initialNewDTO;
    }

    public static PageDTO<NewResponseDTO> newPageDTO() {
        NewResponseDTO page = initialNewDTO();
        PageDTO<NewResponseDTO> pageDTO = new PageDTO<>();
        pageDTO.setContent(Collections.singletonList(page));
        pageDTO.setTotalPages(INITIAL_TOTALPAGES);
        return pageDTO;
    }

    public static NewResponseDTO updatedNewDTO() {
        CategoryResponseDTO category = new CategoryResponseDTO();

        NewResponseDTO newResponseDTO = new NewResponseDTO();
        newResponseDTO.setName(UPDATED_NAME);
        newResponseDTO.setContent(UPDATED_CONTENT);
        newResponseDTO.setImage(UPDATED_IMAGE);
        newResponseDTO.setCategory(category);

        return newResponseDTO;
    }
}
