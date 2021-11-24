package com.alkemy.java.organization;

import com.alkemy.java.dto.OrganizationDTO;
import com.alkemy.java.dto.OrganizationRequestDTO;
import com.alkemy.java.service.OrganizationService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrganizationControllerTest extends AbstractTest{

    private final Logger logger = LoggerFactory.getLogger(OrganizationControllerTest.class);

    @MockBean
    protected OrganizationService organizationService;

    @Override
    @BeforeAll
    public void setUp() {
        logger.info("startup");
        super.setUp();
    }

    @AfterAll
    public void teardown() {
        logger.info("teardown");
    }

    @Test
    public void getOrganizationListTest() throws Exception {
        logger.info("start test get organization list");

        logger.info("preparing test data");
        given(this.organizationService.getAll()).willReturn(listTwoOrganizationDTO());

        logger.info("sending request to controller");
        String uri = "/organization/public";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        logger.info("checking response status");
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        logger.info("checking response content");
        String content = mvcResult.getResponse().getContentAsString();
        OrganizationDTO[] organizationListResponse = super.mapFromJson(content, OrganizationDTO[].class);

        //checking content size
        assertTrue(organizationListResponse.length > 0);
        //checking content equality
        assertEquals(super.mapToJson(listTwoOrganizationDTO()), content);

        logger.info("test get organization list finished");
    }

    @Test
    public void getOrganizationDetailByIdTest() throws Exception {
        logger.info("start test get organization detail by id");

        logger.info("preparing test data");

        given(this.organizationService.findOrganizationDetail(anyLong())).willReturn(initialOrganizationDetailDTO());

        logger.info("sending request to controller");
        String uri = "/organization/public/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        logger.info("checking response status");
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        logger.info("checking response content");
        String content = mvcResult.getResponse().getContentAsString();

        //checking content equality
        assertEquals(super.mapToJson(initialOrganizationDetailDTO()), content);

        logger.info("test get organization detail by id finished");
    }

    @Test
    public void updateOrganizationTest() throws Exception {
        logger.info("start test update organization");

        logger.info("preparing test data");
        given(this.organizationService.update(any(OrganizationRequestDTO.class), anyLong())).willReturn(expectedUpdatedOrganizationDTO());

        String requestJson = super.mapToJson(toUpdateOrganization());

        logger.info("sending request to controller");
        String uri = "/organization/public/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson)).andReturn();

        logger.info("checking response status");
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        logger.info("checking response content");
        String content = mvcResult.getResponse().getContentAsString();

        //checking content equality
        assertEquals(super.mapToJson(expectedUpdatedOrganizationDTO()), content);

        logger.info("test update organization finished");
    }

    @Test
    public void updateOrganizationEmptyNameTest() throws Exception {
        logger.info("start test update organization empty name");

        logger.info("preparing test data");
        given(this.organizationService.update(any(OrganizationRequestDTO.class), anyLong())).willReturn(expectedUpdatedOrganizationDTO());

        String requestJson = super.mapToJson(organizationRequestWithEmptyName());

        logger.info("sending request to controller");
        String uri = "/organization/public/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson)).andReturn();

        logger.info("checking response status");
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);

        logger.info("test update organization empty name finished");
    }

    @Test
    public void updateOrganizationEmptyImageTest() throws Exception {
        logger.info("start test update organization empty image");

        logger.info("preparing test data");
        given(this.organizationService.update(any(OrganizationRequestDTO.class), anyLong())).willReturn(expectedUpdatedOrganizationDTO());

        String requestJson = super.mapToJson(organizationRequestWithEmptyImage());

        logger.info("sending request to controller");
        String uri = "/organization/public/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson)).andReturn();

        logger.info("checking response status");
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);

        logger.info("test update organization empty image finished");
    }

    @Test
    public void updateOrganizationEmptyEmailTest() throws Exception {
        logger.info("start test update organization empty email");

        logger.info("preparing test data");
        given(this.organizationService.update(any(OrganizationRequestDTO.class), anyLong())).willReturn(expectedUpdatedOrganizationDTO());

        String requestJson = super.mapToJson(organizationRequestWithEmptyEmail());

        logger.info("sending request to controller");
        String uri = "/organization/public/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson)).andReturn();

        logger.info("checking response status");
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);

        logger.info("test update organization empty email finished");
    }

    @Test
    public void updateOrganizationInvalidFormatEmailTest() throws Exception {
        logger.info("start test update organization invalid format email");

        logger.info("preparing test data");
        given(this.organizationService.update(any(OrganizationRequestDTO.class), anyLong())).willReturn(expectedUpdatedOrganizationDTO());

        String requestJson = super.mapToJson(organizationRequestWithInvalidFormatEmail());

        logger.info("sending request to controller");
        String uri = "/organization/public/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson)).andReturn();

        logger.info("checking response status");
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);

        logger.info("test update organization invalid format email finished");
    }

    @Test
    public void updateOrganizationEmptyWelcomeTextTest() throws Exception {
        logger.info("start test update organization empty welcome text");

        logger.info("preparing test data");
        given(this.organizationService.update(any(OrganizationRequestDTO.class), anyLong())).willReturn(expectedUpdatedOrganizationDTO());

        String requestJson = super.mapToJson(organizationRequestWithEmptyWelcomeText());

        logger.info("sending request to controller");
        String uri = "/organization/public/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson)).andReturn();

        logger.info("checking response status");
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);

        logger.info("test update organization empty welcome text finished");
    }

    @Test
    public void updateOrganizationEmptyAboutUsTextTest() throws Exception {
        logger.info("start test update organization empty about us text");

        logger.info("preparing test data");
        given(this.organizationService.update(any(OrganizationRequestDTO.class), anyLong())).willReturn(expectedUpdatedOrganizationDTO());

        String requestJson = super.mapToJson(organizationRequestWithEmptyAboutUsText());

        logger.info("sending request to controller");
        String uri = "/organization/public/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson)).andReturn();

        logger.info("checking response status");
        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);

        logger.info("test update organization empty about us text finished");
    }
}
