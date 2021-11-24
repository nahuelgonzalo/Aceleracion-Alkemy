package com.alkemy.java.organization;

import com.alkemy.java.exceptions.BadRequestException;
import com.alkemy.java.exceptions.NotFoundException;
import com.alkemy.java.mapper.OrganizationMapper;
import com.alkemy.java.model.Organization;
import com.alkemy.java.repository.OrganizationRepository;
import com.alkemy.java.repository.SlideRepository;
import com.alkemy.java.service.OrganizationService;
import com.alkemy.java.util.MessageUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static com.alkemy.java.organization.OrganizationTestUtil.*;

/**
 * @author Erik Argel
 */
@Profile("test")
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class OrganizationServiceTest {

    private final Logger logger = LoggerFactory.getLogger(OrganizationControllerTest.class);

    @MockBean
    private OrganizationRepository organizationRepository;

    @MockBean
    private SlideRepository slideRepository;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MessageUtil messageUtil;

    /*
    /* This test checks what happens when all organizations are requested,
    /* and the organization repository returns a list with two organizations.
    */
    @Test
    public void getAllOrganizationTest() {
        logger.info("start test get all organization");

        logger.info("preparing test data");
        given(this.organizationRepository.findAll()).willReturn(listTwoOrganization());

        logger.info("checking response");
        //checking size equality
        assertEquals(2, this.organizationService.getAll().size());

        //checking equality
        assertEquals(asJsonString(organizationMapper.toDto(initialOrganization())), asJsonString(this.organizationService.getAll().get(0)));
        assertEquals(asJsonString(organizationMapper.toDto(secondOrganization())), asJsonString(this.organizationService.getAll().get(1)));

        logger.info("test get all organization finished");
    }

    /*
    /* This test checks what happens when all organizations are requested,
    /* and the organization repository returns a empty list.
    */
    @Test
    public void getAllOrganizationsIsEmptyTest() {
        logger.info("start test get all organization is empty");

        logger.info("preparing test data");
        given(this.organizationRepository.findAll()).willReturn(listEmptyOrganization());

        logger.info("checking response");
        //checking size equality
        assertEquals(0, this.organizationService.getAll().size());

        logger.info("test get all organization is empty finished");
    }

    /*
    /* This test checks what happens when all the details of an organization specified by id are requested
    /* and the organization repository finds the requested organization.
    */
    @Test
    public void findOrganizationDetailTest() {
       logger.info("start test find organization detail");

       logger.info("preparing test data");
       given(this.organizationRepository.findByIdAndDeletedFalse(anyLong())).willReturn(optionalOrganization(initialOrganization()));
       given(this.slideRepository.findByOrganizationIdOrderByOrderAsc(anyLong())).willReturn(listSlides());

       logger.info("checking response");
       //checking equality
       assertEquals(asJsonString(initialOrganizationDetailDTO()), asJsonString(this.organizationService.findOrganizationDetail(INITIAL_ID)));

       logger.info("test find organization detail finished");
    }

    /*
    /* This test checks what happens when all the details of an organization specified by id are requested
    /* and the organization repository cannot find the requested organization.
    */
    @Test
    public void findOrganizationDetailNotFoundTest() {
        logger.info("start test find organization detail not found");

        logger.info("preparing test data");
        given(this.organizationRepository.findByIdAndDeletedFalse(anyLong())).willReturn(Optional.empty());
        given(this.slideRepository.findByOrganizationIdOrderByOrderAsc(anyLong())).willReturn(listSlides());

        logger.info("checking response");
        //checking exception
        Exception exception = assertThrows(NotFoundException.class, () -> {
            this.organizationService.findOrganizationDetail(INITIAL_ID);
        });

        //checking exception message
        String expectedMessage = messageUtil.getMessage(
                "exception.notFound",
                new Long[]{INITIAL_ID},
                Locale.getDefault());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        logger.info("test find organization detail not found finished");
    }

    /*
    /* This test checks what happens when the update is requested from an organization specified by id
    /* and the organization repository finds the requested organization and is updated.
    */
    @Test
    public void updateOrganizationTest() {
        logger.info("start test update organization");

        logger.info("preparing test data");
        given(this.organizationRepository.findByIdAndDeletedFalse(anyLong())).willReturn(optionalOrganization(initialOrganization()));
        given(this.organizationRepository.save(any(Organization.class))).willReturn(updatedOrganization());

        logger.info("checking response");
        //checking equality
        assertEquals(asJsonString(expectedUpdatedOrganizationDTO()), asJsonString(this.organizationService.update(toUpdateOrganization(),INITIAL_ID)));

        logger.info("test update organization finished");
    }

    /*
    /* This test checks what happens when the update is requested from an organization specified by id
    /* and the organization repository cannot find the requested organization.
    */
    @Test
    public void updateOrganizationNotFoundTest() {
        logger.info("start test update organization not found");

        logger.info("preparing test data");
        given(this.organizationRepository.findByIdAndDeletedFalse(anyLong())).willReturn(Optional.empty());

        logger.info("checking response");

        //checking exception
        Exception exception = assertThrows(BadRequestException.class, () -> {
            this.organizationService.update(toUpdateOrganization(), INITIAL_ID);
        });

        //checking exception message
        String expectedMessage = messageUtil.getMessage(
                "exception.notFound",
                new Long[]{INITIAL_ID},
                Locale.getDefault());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


        logger.info("test update organization not found finished");
    }

    /*
    /* This test checks what happens when an organization specified by id is requested
    /* and the organization repository finds the requested organization.
    */
    @Test
    public void findByIdTest() {
        logger.info("start test find by id");

        logger.info("preparing test data");
        given(this.organizationRepository.findById(anyLong())).willReturn(optionalOrganization(initialOrganization()));

        logger.info("checking response");

        //checking equality
        assertEquals(asJsonString(initialOrganization()), asJsonString(this.organizationService.findById(INITIAL_ID)));

        logger.info("test find by id finished");
    }

    /*
    /* This test checks what happens when an organization specified by id is requested
    /* and the organization repository cannot find the requested organization.
    */
    @Test
    public void findByIdNotFoundTest() {
        logger.info("start test find by id not found");

        logger.info("preparing test data");
        given(this.organizationRepository.findById(anyLong())).willReturn(Optional.empty());

        logger.info("checking response");
        //checking exception
        Exception exception = assertThrows(BadRequestException.class, () -> {
            this.organizationService.findById(INITIAL_ID);
        });

        //checking exception message
        String expectedMessage = messageUtil.getMessage(
                "exception.notFound.organization",
                new Long[]{INITIAL_ID},
                Locale.getDefault());
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        logger.info("test find by id not found finished");
    }


}
