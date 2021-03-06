//package com.example.metauniversity.controller;
//
//import com.amazonaws.services.ec2.model.UserData;
//import com.example.metauniversity.config.MockSecurityFilter;
//import com.example.metauniversity.domain.User.EnrollmentStatus;
//import com.example.metauniversity.domain.User.User;
//import com.example.metauniversity.domain.User.UserTyped;
//import com.example.metauniversity.domain.User.UsersData;
//import com.example.metauniversity.domain.subject.dto.subjectDto;
//import com.example.metauniversity.security.CustomUserDetails;
//import com.example.metauniversity.service.subjectService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hibernate.usertype.UserType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.restdocs.RestDocumentationContextProvider;
//import org.springframework.restdocs.RestDocumentationExtension;
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.example.metauniversity.ApiDocumentUtils.getDocumentResponse;
//import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
//import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
//import static org.springframework.restdocs.request.RequestDocumentation.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@AutoConfigureRestDocs
//@AutoConfigureMockMvc
//@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.config.location="
//        + "classpath:application.yml,"
//        + "classpath:aws.yml")
//public class subjectRestControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @MockBean
//    private subjectService subjectService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private UsersData usersData;
//    private User user;
//
//    @BeforeEach
//    public void setup(RestDocumentationContextProvider restDocumentation) {
//
//        usersData = UsersData.builder()
//                .userName("mockName")
//                .userBirth("mockBirth")
//                .userBirth("mockBirth")
//                .userEmail("mockEmail")
//                .Address("mockAddress")
//                .userDepartment("userDepartment")
//                .userMajor("mockMajor")
//                .userMinor("mockMinor")
//                .userType(UserTyped.STUDENT)
//                .enrollmentStatus(EnrollmentStatus.ATTENDING)
//                .userGrade(1)
//                .isThumbnail(true)
//                .workerSpot("mockSpot")
//                .build();
//
//        user = User.builder()
//                .usersData(usersData)
//                .accountId("mockAccountId")
//                .userPassword("mockPassword")
//                .confirmEmail("mockEmail")
//                .build();
//
//        mvc = MockMvcBuilders.webAppContextSetup(context)
//                .apply(documentationConfiguration(restDocumentation))
//                .apply(springSecurity(new MockSecurityFilter()))
//                .build();
//    }
//
//    @Test
//    @DisplayName(value = "???????????? ??????")
//    public void sugang() throws Exception{
//
//        //given
//        subjectDto.enroll enroll = subjectDto.enroll.builder()
//                .subjectId(1L)
//                .subjectTitle("mockSubjectTitle")
//                .professor("mockProfessor")
//                .subjectPoints(3)
//                .isMajor("??????")
//                .subjectDepaetment("mockDepartment")
//                .subjectGrades(1)
//                .limited(40)
//                .classRoom("301")
//                .day("Mon")
//                .startTime("13:00")
//                .endTime("15:00")
//                .status(true)
//                .build();
//        given(subjectService.EnrollSubject(any(), any())).willReturn(enroll);
//
//        //when
//        ResultActions result = mvc.perform(RestDocumentationRequestBuilders.post("/subject/enroll/{subjectId}", 1L)
//                .principal(new UsernamePasswordAuthenticationToken(CustomUserDetails.create(user), null))
//                .header("Authorization", "?????? ???????????? ??????"));
//
//        //then
//        result
//                .andDo(print())
//                .andDo(document("subject-enroll",
//                        getDocumentResponse(),
//                        requestHeaders(
//                                headerWithName("Authorization").description("?????? ???????????? ??????")
//                        ),
//                        pathParameters(
//                                parameterWithName("subjectId").description("?????? ?????????")
//                        ),
//                        responseFields(
//                            fieldWithPath("subjectId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                            fieldWithPath("subjectTitle").type(JsonFieldType.STRING).description("?????? ?????????"),
//                            fieldWithPath("professor").type(JsonFieldType.STRING).description("?????? ????????????"),
//                            fieldWithPath("subjectPoints").type(JsonFieldType.NUMBER).description("?????? ??????"),
//                            fieldWithPath("isMajor").type(JsonFieldType.STRING).description("?????? ????????????"),
//                            fieldWithPath("subjectDepaetment").type(JsonFieldType.STRING).description("?????? ????????????"),
//                            fieldWithPath("subjectGrades").type(JsonFieldType.NUMBER).description("?????? ????????????"),
//                            fieldWithPath("limited").type(JsonFieldType.NUMBER).description("?????? ????????????"),
//                            fieldWithPath("classRoom").type(JsonFieldType.STRING).description("?????? ?????????"),
//                            fieldWithPath("day").type(JsonFieldType.STRING).description("?????? ????????????"),
//                            fieldWithPath("startTime").type(JsonFieldType.STRING).description("?????? ????????????"),
//                            fieldWithPath("endTime").type(JsonFieldType.STRING).description("?????? ????????????"),
//                            fieldWithPath("status").type(JsonFieldType.BOOLEAN).description("???????????? ??????(true : ??????????????? / false : ???????????????)")
//                        )
//                ));
//    }
//
//    @Test
//    @DisplayName(value = "???????????? ??????")
//    public void sugangCancel() throws Exception {
//        //given
//        subjectDto.cancel cancel = subjectDto.cancel.builder()
//                .subjectTitle("mockSubjectTitle")
//                .subjectId(1L)
//                .status(false)
//                .build();
//        given(subjectService.cancelSubject(any(), any())).willReturn(cancel);
//
//        //when
//        ResultActions result = mvc.perform(RestDocumentationRequestBuilders.post("/subject/cancel/{subjectId}", 1L)
//                .principal(new UsernamePasswordAuthenticationToken(CustomUserDetails.create(user), null))
//                .header("Authorization", "?????? ???????????? ??????"));
//
//        //then
//        result
//                .andDo(print())
//                .andDo(document("subject-cancel",
//                        getDocumentResponse(),
//                        requestHeaders(
//                                headerWithName("Authorization").description("?????? ???????????? ??????")
//                        ),
//                        pathParameters(
//                                parameterWithName("subjectId").description("?????? ?????????")
//                        ),
//                        responseFields(
//                                fieldWithPath("subjectId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                fieldWithPath("subjectTitle").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                fieldWithPath("status").type(JsonFieldType.BOOLEAN).description("???????????? ??????(true : ??????????????? / false : ???????????????)")
//                        )
//                ));
//    }
//
//    @Test
//    @DisplayName(value = "???????????? ?????????")
//    public void sugangFilter() throws Exception {
//        //given
//        List<subjectDto.getList> glist = new ArrayList<>();
//        glist.add(subjectDto.getList.builder()
//                .subjectId(1L)
//                .subjectTitle("mockTitle")
//                .subjectPoints(3)
//                .professor("mockProfessor")
//                .isMajor("all")
//                .subjectDepaetment("mockDepartment")
//                .subjectGrades(1)
//                .limited(40)
//                .classRoom("301")
//                .day("Mon")
//                .startTime("13:00")
//                .endTime("15:00")
//                .build());
//
//        given(subjectService.getAllBySearch(any())).willReturn(glist);
//
//        //when
//        ResultActions result = mvc.perform(RestDocumentationRequestBuilders.get("/subject/list/search"));
//
//        //then
//        result
//                .andDo(print())
//                .andDo(document("subject-filter",
//                        getDocumentResponse(),
//                        requestParameters(
//                                parameterWithName("subjectTitle").optional().description("?????? ????????? : ???????????? ?????? ??????"),
//                                parameterWithName("subjectPoints").optional().description("?????? ?????? : ????????? ????????? ?????? ????????? ?????? ??????"),
//                                parameterWithName("isMajor").optional().description("?????? ?????? : ?????? / ???????????? ????????? ??????")
//                        ),
//                        responseFields(
//                                fieldWithPath("count").type(JsonFieldType.NUMBER).description("???????????? ?????? ??????"),
//                                fieldWithPath("data.[].subjectId").type(JsonFieldType.NUMBER).description("???????????? ?????? ??????"),
//                                fieldWithPath("data.[].subjectTitle").type(JsonFieldType.STRING).description("???????????? ?????? ??????"),
//                                fieldWithPath("data.[].subjectPoints").type(JsonFieldType.NUMBER).description("???????????? ?????? ??????"),
//                                fieldWithPath("data.[].professor").type(JsonFieldType.STRING).description("???????????? ?????? ?????????"),
//                                fieldWithPath("data.[].isMajor").type(JsonFieldType.STRING).description("???????????? ?????? ????????????"),
//                                fieldWithPath("data.[].subjectDepaetment").type(JsonFieldType.STRING).description("???????????? ?????? ????????????"),
//                                fieldWithPath("data.[].subjectGrades").type(JsonFieldType.NUMBER).description("???????????? ?????? ????????????"),
//                                fieldWithPath("data.[].limited").type(JsonFieldType.NUMBER).description("???????????? ?????? ????????????"),
//                                fieldWithPath("data.[].classRoom").type(JsonFieldType.STRING).description("???????????? ?????? ?????????"),
//                                fieldWithPath("data.[].day").type(JsonFieldType.STRING).description("???????????? ?????? ????????????"),
//                                fieldWithPath("data.[].startTime").type(JsonFieldType.STRING).description("???????????? ?????? ??????????????????"),
//                                fieldWithPath("data.[].endTime").type(JsonFieldType.STRING).description("???????????? ?????? ??????????????????")
//                        )
//                ))
//                .andExpect(jsonPath("$.count").value(1))
//                .andExpect(jsonPath("$.data.[0].subjectId").value(1L))
//                .andExpect(jsonPath("$.data.[0].subjectTitle").value("mockTitle"))
//                .andExpect(jsonPath("$.data.[0].subjectPoints").value(3))
//                .andExpect(jsonPath("$.data.[0].professor").value("mockProfessor"))
//                .andExpect(jsonPath("$.data.[0].isMajor").value("all"))
//                .andExpect(jsonPath("$.data.[0].subjectDepaetment").value("mockDepartment"))
//                .andExpect(jsonPath("$.data.[0].subjectGrades").value(1))
//                .andExpect(jsonPath("$.data.[0].limited").value(40))
//                .andExpect(jsonPath("$.data.[0].classRoom").value("301"))
//                .andExpect(jsonPath("$.data.[0].day").value("Mon"))
//                .andExpect(jsonPath("$.data.[0].startTime").value("13:00"))
//                .andExpect(jsonPath("$.data.[0].endTime").value("15:00"));
//    }
//}
