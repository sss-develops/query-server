package project.goorm.queryserver.common.configuration.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import project.goorm.queryserver.business.web.common.paging.Cursor;
import project.goorm.queryserver.business.web.common.paging.CursorPageable;

@Component
public class PagingArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String CURSOR_TARGET = "cursorTarget";
    private static final String PAGE_SIZE = "size";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CursorPageable.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        String target = webRequest.getParameter(CURSOR_TARGET);
        String pageSize = webRequest.getParameter(PAGE_SIZE);


        CursorPageable cursorPageable = parameter.getParameterAnnotation(CursorPageable.class);
        if (target == null) {
            return Cursor.from(
                    getPageSize(pageSize, cursorPageable)
            );
        }
        return Cursor.from(
                parseLong(target),
                getPageSize(pageSize, cursorPageable)
        );
    }

    private Long parseLong(String target) {
        if (target == null) {
            return null;
        }
        try {
            return Long.parseLong(target);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private int getPageSize(
            String pageSize,
            CursorPageable cursorPageable
    ) {
        if (pageSize == null) {
            return cursorPageable.defaultSize();
        }
        try {
            return Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            return cursorPageable.defaultSize();
        }
    }
}
