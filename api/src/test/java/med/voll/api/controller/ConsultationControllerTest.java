package med.voll.api.controller;

import med.voll.api.domain.consultation.Consultation;
import med.voll.api.domain.consultation.ConsultationDTO;
import med.voll.api.domain.consultation.ConsultationService;
import med.voll.api.domain.consultation.DetailConsultationDTO;
import med.voll.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ConsultationDTO> consultationDTOJacksonTester;

    @Autowired
    private JacksonTester<DetailConsultationDTO> detailConsultationDTOJacksonTester;

    @MockBean //Para simular el consultaService se agrega la notacion de MockBean
    private ConsultationService consultationService;

    @Test
    @DisplayName("This method should return status http 400 when the data are invalid")
    @WithMockUser//Simula un usuario autenticado en este caso para el token
    void saveStatus400() throws Exception {
    //Given //When
    var response=mvc.perform(post("/consultation")).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("This method should return status http 200 when the data are valid")
    @WithMockUser//Simula un usuario autenticado en este caso para el token
    void saveStatus200() throws Exception {
        //Given
        var date= LocalDateTime.now().plusHours(1);
        DetailConsultationDTO detailConsultationDTO= new DetailConsultationDTO(null,2L,5L,date);
        ConsultationDTO consultationDTO= new ConsultationDTO( null,2L,null,5L,date);

        System.out.println(consultationDTO);

        //When

        when(consultationService.save(any())).thenReturn(detailConsultationDTO);

        var response=mvc.perform(post("/consultation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(consultationDTOJacksonTester.write(consultationDTO).getJson())

                )
                .andReturn().getResponse();



        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson=detailConsultationDTOJacksonTester.write(detailConsultationDTO).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }



}