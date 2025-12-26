package com.huntercodexs.sample.component.exception;

import com.huntercodexs.integration.handler.interfaces.GlobalExceptionInterceptorIntegration;
import com.huntercodexs.integration.handler.enumerator.GlobalEnumIntegration;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.huntercodexs.integration.handler.enumerator.GlobalEnumIntegration.CIRCUIT_BREAKER_CALL_NOT_PERMITTED_EXCEPTION_INTERCEPTOR_503;

@Component
public class Impl4 implements GlobalExceptionInterceptorIntegration {
    @Override
    public boolean supports(GlobalEnumIntegration value) {
        return value.equals(CIRCUIT_BREAKER_CALL_NOT_PERMITTED_EXCEPTION_INTERCEPTOR_503);
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
        return "4";
    }

    @Override
    public List<String> errors(Object exception) {
        return List.of("Erro 1", "Erro 2", "Erro 3");
    }
}
