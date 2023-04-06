package project.goorm.queryserver.test.unit.cursor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.MethodParameter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import project.goorm.queryserver.business.web.common.paging.Cursor;
import project.goorm.queryserver.common.configuration.argumentresolver.PagingArgumentResolver;
import project.goorm.queryserver.common.configuration.rdb.DatabaseTestBase;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;


@DisplayName("커서 단위 테스트")
public class CursorUnitTest extends DatabaseTestBase {

    @Autowired
    private PagingArgumentResolver argumentResolver;

    @MockBean
    private MethodParameter parameter;

    @MockBean
    private ModelAndViewContainer mavContainer;

    @MockBean
    private NativeWebRequest webRequest;

    @MockBean
    private WebDataBinderFactory binderFactory;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        webRequest = new ServletWebRequest(request, response);
    }

    @Test
    @DisplayName("Parameter로 커서를 보내주지 않으면 CURSOR_TARGET=0, PAGE_SIZE=10 으로 초기화된다.")
    void when_cursor_request_param_not_exist_cursor_should_be_default() {
        Long expectedNext = 0L;
        int expectedPageSize = 10;
        Object argument = argumentResolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        Cursor cursor = (Cursor) argument;
        assertAll(
                () -> assertEquals(expectedNext, cursor.getNext()),
                () -> assertEquals(expectedPageSize, cursor.getPageSize())
        );
    }

    @Test
    @DisplayName("커서의 CURSOR_TARGET이 음수면 IllegalArgumentException이 발생한다.")
    void when_cursor_request_param_CURSOR_TARGET_is_minus_response_should_throw_IllegalArgumentException() {
        request.setAttribute("cursorTarget", -1L);
        assertThatThrownBy(() -> argumentResolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CURSOR_TARGET의 값이 비정상적입니다.");
    }

    @Test
    @DisplayName("커서의 PAGE_SIZE가 MAX_PAGE_SIZE(10)보다 크면 MAX_PAGE_SIZE(10)가 된다..")
    void when_cursor_request_param_PAGE_SIZE_is_greater_than_MAX_PAGE_SIZE_cursor_pageSize_should_be_MAX_PAGE_SIZE() {
        int expectedPageSize = 10;
        request.setAttribute("cursorTarget", 15L);
        Object argument = argumentResolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        Cursor cursor = (Cursor) argument;
        assertEquals(expectedPageSize, cursor.getPageSize());
    }

    @Test
    @DisplayName("Parameter로 정상 값을 보내줬을 때 정상 작동한다.")
    void when_cursor_request_param_right_cursor_should_work_normally() {
        Long expectedNext = 5L;
        int expectedPageSize = 7;
        request.setAttribute("cursorTarget", 5L);
        request.setAttribute("size", 7);
        Object argument = argumentResolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        Cursor cursor = (Cursor) argument;
        assertAll(
                () -> assertEquals(expectedNext, cursor.getNext()),
                () -> assertEquals(expectedPageSize, cursor.getPageSize())
        );
    }
}
