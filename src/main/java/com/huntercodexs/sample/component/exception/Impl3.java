package com.huntercodexs.sample.component.exception;

import com.huntercodexs.integration.handler.interfaces.GlobalExceptionInterceptorIntegration;
import com.huntercodexs.integration.handler.enumerator.GlobalEnumIntegration;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.huntercodexs.integration.handler.enumerator.GlobalEnumIntegration.RUNTIME_EXCEPTION_INTERCEPTOR_500;

@Component
public class Impl3 implements GlobalExceptionInterceptorIntegration {
    @Override
    public boolean supports(GlobalEnumIntegration value) {
        return value.equals(RUNTIME_EXCEPTION_INTERCEPTOR_500);
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
        return "3";
    }

    @Override
    public List<String> errors(Object exception) {
        System.out.println(exception);
        return List.of("Erro 1", "Erro 2");
    }
}
