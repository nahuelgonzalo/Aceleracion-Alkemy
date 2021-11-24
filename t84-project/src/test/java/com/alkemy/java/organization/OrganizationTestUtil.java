package com.alkemy.java.organization;

import com.alkemy.java.dto.OrganizationDTO;
import com.alkemy.java.dto.OrganizationDetailDTO;
import com.alkemy.java.dto.OrganizationRequestDTO;
import com.alkemy.java.dto.SlideDTO;
import com.alkemy.java.model.Organization;
import com.alkemy.java.model.Slide;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.auth.In;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Erik Argel
 */
@SpringBootTest
public class OrganizationTestUtil {
    public static final long INITIAL_ID = 1L;
    public static final String INITIAL_NAME = "Organization 1";
    public static final String INITIAL_IMAGE = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAABHNCSVQICAgIfAhkiAAAAAtJREFUCJlj+A8EAAn7A/3jVfKcAAAAAElFTkSuQmCC";
    public static final String INITIAL_ADDRESS = "Address Organization 1";
    public static final Integer INITIAL_PHONE = 1122223333;
    public static final String INITIAL_FACEBOOK_URL = "www.facebook.com/ong1";
    public static final String INITIAL_LINKEDIN_URL = "www.linkedin.com/in/ong1";
    public static final String INITIAL_INSTAGRAM_URL = "instagram.com/ong1";
    public static final String INITIAL_EMAIL = "organization1@gmail.com";
    public static final String INITIAL_WELCOME_TEXT = "Welcome TO Organization 1";
    public static final String INITIAL_ABOUT_US_TEXT = "About Organization 1";
    public static final Boolean INITIAL_DELETED = Boolean.FALSE;
    public static final Date INITIAL_CREATE_AT = new Date(2021,05,05);

    public static final long SECOND_ID = 2L;
    public static final String SECOND_NAME = "Organization 2";
    public static final String SECOND_IMAGE = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAABHNCSVQICAgIfAhkiAAAAA1JREFUCJljYGBg+A8AAQQBAH2yyN8AAAAASUVORK5CYII=";
    public static final String SECOND_ADDRESS = "Address Organization 2";
    public static final Integer SECOND_PHONE = 1177771111;
    public static final String SECOND_FACEBOOK_URL = "www.facebook.com/ong2";
    public static final String SECOND_LINKEDIN_URL = "www.linkedin.com/in/ong2";
    public static final String SECOND_INSTAGRAM_URL = "instagram.com/ong2";
    public static final String SECOND_EMAIL = "organization2@gmail.com";
    public static final String SECOND_WELCOME_TEXT = "Welcome To Organization 2";
    public static final String SECOND_ABOUT_US_TEXT = "About Organization 2";
    public static final Boolean SECOND_DELETED = Boolean.FALSE;
    public static final Date SECOND_CREATE_AT = new Date(2021,07,05);

    public static final String UPDATED_NAME = "Organization Updated";
    public static final String UPDATED_IMAGE = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAABHNCSVQICAgIfAhkiAAAAA1JREFUCJljYGBg+A8AAQQBAH2yyN8AAAAASUVORK5CYII=";
    public static final String UPDATED_ADDRESS = "Address Organization updated";
    public static final Integer UPDATED_PHONE = 1188884444;
    public static final String UPDATED_FACEBOOK_URL = "www.facebook.com/ongUpdated";
    public static final String UPDATED_LINKEDIN_URL = "www.linkedin.com/in/ongUpdated";
    public static final String UPDATED_INSTAGRAM_URL = "instagram.com/ongUpdated";
    public static final String UPDATED_EMAIL = "organization_updated@gmail.com";
    public static final String UPDATED_WELCOME_TEXT = "Welcome To Organization updated";
    public static final String UPDATED_ABOUT_US_TEXT = "About Organization updated";
    public static final LocalDateTime UPDATED_MODIFIED_AT = LocalDateTime.of(3000, 12, 12, 12, 12, 12);

    public static final String EMPTY_NAME = "";
    public static final String EMPTY_IMAGE = "";
    public static final String EMPTY_EMAIL = "";
    public static final String INVALID_EMAIL = "ong.@.@awirlpqws";
    public static final String EMPTY_WELCOME_TEXT = "";
    public static final String EMPTY_ABOUT_US_TEXT = "";

    public static final long INITIAL_SLIDE_ID = 1L;
    public static final String INITIAL_SLIDE_TEXT = "Text Slide1";
    public static final String INITIAL_SLIDE_IMAGE = "ImageSlide1.png";
    public static final Integer INITIAL_SLIDE_ORDER = 1;

    //returns an organization with initial values
    public static Organization initialOrganization(){
        Organization organization = new Organization();
        organization.setId(INITIAL_ID);
        organization.setName(INITIAL_NAME);
        organization.setImage(INITIAL_IMAGE);
        organization.setAddress(INITIAL_ADDRESS);
        organization.setPhone(INITIAL_PHONE);
        organization.setFacebookUrl(INITIAL_FACEBOOK_URL);
        organization.setLinkedinUrl(INITIAL_LINKEDIN_URL);
        organization.setInstagramUrl(INITIAL_INSTAGRAM_URL);
        organization.setEmail(INITIAL_EMAIL);
        organization.setWelcomeText(INITIAL_WELCOME_TEXT);
        organization.setAboutUsText(INITIAL_ABOUT_US_TEXT);
        organization.setDeleted(INITIAL_DELETED);
        organization.setCreateAt(INITIAL_CREATE_AT);
        return organization;
    }

    //returns an organization with secondary values
    public static Organization secondOrganization(){
        Organization organization = new Organization();
        organization.setId(SECOND_ID);
        organization.setName(SECOND_NAME);
        organization.setImage(SECOND_IMAGE);
        organization.setAddress(SECOND_ADDRESS);
        organization.setPhone(SECOND_PHONE);
        organization.setFacebookUrl(SECOND_FACEBOOK_URL);
        organization.setLinkedinUrl(SECOND_LINKEDIN_URL);
        organization.setInstagramUrl(SECOND_INSTAGRAM_URL);
        organization.setEmail(SECOND_EMAIL);
        organization.setWelcomeText(SECOND_WELCOME_TEXT);
        organization.setAboutUsText(SECOND_ABOUT_US_TEXT);
        organization.setDeleted(SECOND_DELETED);
        organization.setCreateAt(SECOND_CREATE_AT);
        return organization;
    }

    //returns an Optional of the organization passed by parameter
    public static Optional<Organization> optionalOrganization(Organization organization){
        return Optional.of(organization);
    }

    /*
    /*returns an OrganizationRequestDTO
    /*that simulates a request to update an organization
    */
    public static OrganizationRequestDTO toUpdateOrganization(){
        OrganizationRequestDTO organizationRequestDTO = new OrganizationRequestDTO();
        organizationRequestDTO.setName(UPDATED_NAME);
        organizationRequestDTO.setImage(UPDATED_IMAGE);
        organizationRequestDTO.setPhone(UPDATED_PHONE);
        organizationRequestDTO.setAddress(UPDATED_ADDRESS);
        organizationRequestDTO.setEmail(UPDATED_EMAIL);
        organizationRequestDTO.setWelcomeText(UPDATED_WELCOME_TEXT);
        organizationRequestDTO.setAboutUsText(UPDATED_ABOUT_US_TEXT);
        organizationRequestDTO.setFacebookUrl(UPDATED_FACEBOOK_URL);
        organizationRequestDTO.setLinkedinUrl(UPDATED_LINKEDIN_URL);
        organizationRequestDTO.setInstagramUrl(UPDATED_INSTAGRAM_URL);
        return organizationRequestDTO;
    }

    /*
    /*returns an updated organization.
    /*This simulates the organization returned by
    /*the save method of the organization repository.
    */
    public static Organization updatedOrganization(){
        Organization organization = new Organization();
        organization.setId(INITIAL_ID);
        organization.setName(UPDATED_NAME);
        organization.setImage(UPDATED_IMAGE);
        organization.setAddress(UPDATED_ADDRESS);
        organization.setPhone(UPDATED_PHONE);
        organization.setFacebookUrl(UPDATED_FACEBOOK_URL);
        organization.setLinkedinUrl(UPDATED_LINKEDIN_URL);
        organization.setInstagramUrl(UPDATED_INSTAGRAM_URL);
        organization.setEmail(UPDATED_EMAIL);
        organization.setWelcomeText(UPDATED_WELCOME_TEXT);
        organization.setAboutUsText(UPDATED_ABOUT_US_TEXT);
        organization.setDeleted(INITIAL_DELETED);
        organization.setCreateAt(INITIAL_CREATE_AT);
        return organization;
    }
    /*
    /*returns an OrganizationDTO
    /*which represents the expected result when
    /*modifying the organization
    */
    public static OrganizationDTO expectedUpdatedOrganizationDTO(){
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setName(UPDATED_NAME);
        organizationDTO.setImage(UPDATED_IMAGE);
        organizationDTO.setPhone(UPDATED_PHONE);
        organizationDTO.setAddress(UPDATED_ADDRESS);
        organizationDTO.setFacebookUrl(UPDATED_FACEBOOK_URL);
        organizationDTO.setLinkedinUrl(UPDATED_LINKEDIN_URL);
        organizationDTO.setInstagramUrl(UPDATED_INSTAGRAM_URL);
        return organizationDTO;
    }

    //returns a list that has two organizations
    public static List<Organization> listTwoOrganization(){
        List<Organization> organizations = new ArrayList<>();
        organizations.add(initialOrganization());
        organizations.add(secondOrganization());
        return organizations;
    }

    //returns an empty organization list
    public static List<Organization> listEmptyOrganization(){
        return new ArrayList<Organization>();
    }

    //returns a list that has two OrganizationDTOs
    public static List<OrganizationDTO> listTwoOrganizationDTO(){
        List<OrganizationDTO> organizations = new ArrayList<>();
        OrganizationDTO organizationDTO1 = new OrganizationDTO();
        organizationDTO1.setName(INITIAL_NAME);
        organizationDTO1.setImage(INITIAL_IMAGE);
        organizationDTO1.setPhone(INITIAL_PHONE);
        organizationDTO1.setAddress(INITIAL_ADDRESS);
        organizationDTO1.setFacebookUrl(INITIAL_FACEBOOK_URL);
        organizationDTO1.setLinkedinUrl(INITIAL_LINKEDIN_URL);
        organizationDTO1.setInstagramUrl(INITIAL_INSTAGRAM_URL);

        OrganizationDTO organizationDTO2 = new OrganizationDTO();
        organizationDTO2.setName(SECOND_NAME);
        organizationDTO2.setImage(SECOND_IMAGE);
        organizationDTO2.setPhone(SECOND_PHONE);
        organizationDTO2.setAddress(SECOND_ADDRESS);
        organizationDTO2.setFacebookUrl(SECOND_FACEBOOK_URL);
        organizationDTO2.setLinkedinUrl(SECOND_LINKEDIN_URL);
        organizationDTO2.setInstagramUrl(SECOND_INSTAGRAM_URL);

        organizations.add(organizationDTO1);
        organizations.add(organizationDTO2);

        return organizations;
    }

    //returns an empty organizationDTO list
    public static List<OrganizationDTO> listEmptyOrganizationDTO(){
        return new ArrayList<OrganizationDTO>();
    }

    /*
    /*returns an OrganizationRequestDTO
    /*that simulates a request to update an organization
    /*with the empty name
    */
    public static OrganizationRequestDTO organizationRequestWithEmptyName(){
        OrganizationRequestDTO organizationRequestDTO = toUpdateOrganization();
        organizationRequestDTO.setName(EMPTY_NAME);
        return organizationRequestDTO;
    }

    /*
    /*returns an OrganizationRequestDTO
    /*that simulates a request to update an organization
    /*with the empty image
    */
    public static OrganizationRequestDTO organizationRequestWithEmptyImage(){
        OrganizationRequestDTO organizationRequestDTO = toUpdateOrganization();
        organizationRequestDTO.setImage(EMPTY_IMAGE);
        return organizationRequestDTO;
    }

    /*
    /*returns an OrganizationRequestDTO
    /*that simulates a request to update an organization
    /*with the empty email
    */
    public static OrganizationRequestDTO organizationRequestWithEmptyEmail(){
        OrganizationRequestDTO organizationRequestDTO = toUpdateOrganization();
        organizationRequestDTO.setEmail(EMPTY_EMAIL);
        return organizationRequestDTO;
    }

    /*
    /*returns an OrganizationRequestDTO
    /*that simulates a request to update an organization
    /*with an invalid email format
    */
    public static OrganizationRequestDTO organizationRequestWithInvalidFormatEmail(){
        OrganizationRequestDTO organizationRequestDTO = toUpdateOrganization();
        organizationRequestDTO.setEmail(INVALID_EMAIL);
        return organizationRequestDTO;
    }

    /*
    /*returns an OrganizationRequestDTO
    /*that simulates a request to update an organization
    /*with the empty welcome text
    */
    public static OrganizationRequestDTO organizationRequestWithEmptyWelcomeText(){
        OrganizationRequestDTO organizationRequestDTO = toUpdateOrganization();
        organizationRequestDTO.setWelcomeText(EMPTY_WELCOME_TEXT);
        return organizationRequestDTO;
    }

    /*
    /*returns an OrganizationRequestDTO
    /*that simulates a request to update an organization
    /*with the empty about us text
    */
    public static OrganizationRequestDTO organizationRequestWithEmptyAboutUsText(){
        OrganizationRequestDTO organizationRequestDTO = toUpdateOrganization();
        organizationRequestDTO.setAboutUsText(EMPTY_ABOUT_US_TEXT);
        return organizationRequestDTO;
    }

    //returns an organizationDetailDTO with initial values
    public static OrganizationDetailDTO initialOrganizationDetailDTO(){
        OrganizationDetailDTO organizationDetailDTO = new OrganizationDetailDTO();
        organizationDetailDTO.setName(INITIAL_NAME);
        organizationDetailDTO.setImage(INITIAL_IMAGE);
        organizationDetailDTO.setPhone(INITIAL_PHONE);
        organizationDetailDTO.setAddress(INITIAL_ADDRESS);
        organizationDetailDTO.setFacebookUrl(INITIAL_FACEBOOK_URL);
        organizationDetailDTO.setLinkedinUrl(INITIAL_LINKEDIN_URL);
        organizationDetailDTO.setInstagramUrl(INITIAL_INSTAGRAM_URL);

        List<SlideDTO> slides = new ArrayList<>();
        SlideDTO slide1 = new SlideDTO();
        slide1.setId(INITIAL_SLIDE_ID);
        slide1.setOrder(INITIAL_SLIDE_ORDER);
        slide1.setText(INITIAL_SLIDE_TEXT);
        slide1.setImageUrl(INITIAL_SLIDE_IMAGE);

        slides.add(slide1);

        organizationDetailDTO.setSlides(slides);

        return organizationDetailDTO;
    }

    /*
    /*returns an slides list.
    /*This simulates the slides list returned by
    /*the slide repository.
    */
    public static List<Slide> listSlides(){
        List<Slide> slides = new ArrayList<>();
        Slide slide1 = new Slide();
        slide1.setId(INITIAL_SLIDE_ID);
        slide1.setOrder(INITIAL_SLIDE_ORDER);
        slide1.setText(INITIAL_SLIDE_TEXT);
        slide1.setImageUrl(INITIAL_SLIDE_IMAGE);
        slides.add(slide1);

        return slides;
    }

    //convert an object to a json
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
