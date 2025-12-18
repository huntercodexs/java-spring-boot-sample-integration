package com.huntercodexs.sample.component.exception;

import com.huntercodexs.integration.core.interfaces.GlobalExceptionInterceptorIntegration;
import com.huntercodexs.integration.handler.enumerator.GlobalEnumIntegration;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.huntercodexs.integration.handler.enumerator.GlobalEnumIntegration.HTTP_MESSAGE_NOT_READABLE_EXCEPTION_INTERCEPTOR_400;

@Component
public class Impl2 implements GlobalExceptionInterceptorIntegration {
    @Override
    public boolean supports(GlobalEnumIntegration value) {
        return value.equals(HTTP_MESSAGE_NOT_READABLE_EXCEPTION_INTERCEPTOR_400);
    }

    @Override
    public String message() {
        return "Mensagem";
    }

    @Override
    public String trackerId() {
        return "8329083290";
    }

    @Override
    public String code() {
        return "2";
    }

    @Override
    public List<String> errors(Object exception) {
        return List.of("Erro 1", "Erro 2");
    }
}
