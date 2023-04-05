package project.goorm.queryserver.test.unit.cursor;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import project.goorm.queryserver.business.web.common.paging.Cursor;
import project.goorm.queryserver.business.web.common.paging.CursorPageable;
import project.goorm.queryserver.common.configuration.argumentresolver.PagingArgumentResolver;

public class CursorTestArgumentResolver extends PagingArgumentResolver {

    private static final String CURSOR_TARGET = "cursorTarget";
    private static final String PAGE_SIZE = "size";

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        String target = webRequest.getParameter(CURSOR_TARGET);
        String pageSize = webRequest.getParameter(PAGE_SIZE);
        System.out.println(target);
        System.out.println(pageSize);
        return Cursor.from(
                parseLong(target),
                getPageSize(pageSize, 10)
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
            int cursorPageable
    ) {
        if (pageSize == null) {
            return cursorPageable;
        }
        try {
            return Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            return cursorPageable;
        }
    }
}
