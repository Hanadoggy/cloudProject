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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
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
        MemberPostDto newMember = new MemberPostDto("yujin", "", "");

        String requestBody = objectMapper.writeValueAsString(newMember);
        mockMvc.perform(post(END_POINT_PATH+"/sign-up")
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("회원가입 DTO 검증 - 전화번호 길이 부족 실패")
    void signUpFailWithWrongPhoneLength() throws Exception {
        MemberPostDto newMember = new MemberPostDto("yujin", "010123123", "123123123");

        String requestBody = objectMapper.writeValueAsString(newMember);
        mockMvc.perform(post(END_POINT_PATH+"/sign-up")
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("회원가입 DTO 검증 - 비밀번호 길이 부족 실패")
    void signUpFailWithLessPasswordLength() throws Exception {
        MemberPostDto newMember = new MemberPostDto("yujin", "01012341234", "1234");

        String requestBody = objectMapper.writeValueAsString(newMember);
        mockMvc.perform(post(END_POINT_PATH+"/sign-up")
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("회원가입 DTO 검증 - 성공")
    void signUpSuccess() throws Exception {
        MemberPostDto newMember = new MemberPostDto("yujin", "01012341234", "123123456");

        String requestBody = objectMapper.writeValueAsString(newMember);
        mockMvc.perform(post(END_POINT_PATH+"/sign-up")
                        .contentType("application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("닉네임 중복 확인 - 성공")
    void checkDuplicatedNicknameSuccess() throws Exception {
        // given
        String targetUrl = END_POINT_PATH + "/{nickname}";
        String parameter = "yujin";

        // when
        when(memberService.checkNicknameIsDuplicated(parameter)).thenReturn(ResponseEntity.noContent().build());

        // then
        mockMvc.perform(post(targetUrl, parameter))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("닉네임 중복 확인 - 중복 존재, 실패")
    void checkDuplicatedNicknameFail() throws Exception {
        // given
        String targetUrl = END_POINT_PATH + "/{nickname}";
        String parameter = "yujin";

        // when
        when(memberService.checkNicknameIsDuplicated(parameter)).thenReturn(ResponseEntity.badRequest().build());

        // then
        mockMvc.perform(post(targetUrl, parameter))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}