package dbwls.cloudProject.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbwls.cloudProject.member.dto.MemberPostDto;
import dbwls.cloudProject.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(MemberApiController.class)
class MemberApiControllerTest {
    private static final String END_POINT_PATH = "/member";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 DTO 검증 - 입력값 부족 실패")
    void signUpFailWithEmptyParam() throws Exception {
        String targetUrl = END_POINT_PATH + "/sign-up";
        MemberPostDto newMember = new MemberPostDto("yujin","","");

        String requestBody = objectMapper.writeValueAsString(newMember);
        mockMvc.perform(post(targetUrl).contentType("application/json")
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 DTO 검증 - 성공")
    void signUpSuccess() throws Exception {
        String targetUrl = END_POINT_PATH + "/sign-up";
        MemberPostDto newMember = new MemberPostDto("yujin","01012341234","123123456");

        String requestBody = objectMapper.writeValueAsString(newMember);
        mockMvc.perform(post(targetUrl).contentType("application/json")
                .content(requestBody))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}