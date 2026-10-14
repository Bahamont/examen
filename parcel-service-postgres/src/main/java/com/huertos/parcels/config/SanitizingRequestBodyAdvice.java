package com.huertos.parcels.config;

import com.huertos.parcels.dto.ParcelRequest;
import com.huertos.parcels.util.InputSanitizer;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

/**
 * Sanitiza el cuerpo de la solicitud antes de que se ejecute Bean Validation.
 */
@ControllerAdvice
public class SanitizingRequestBodyAdvice extends RequestBodyAdviceAdapter {

    @Override
    public boolean supports(
            MethodParameter methodParameter,
            Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return ParcelRequest.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object afterBodyRead(
            Object body,
            HttpInputMessage inputMessage,
            MethodParameter parameter,
            Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType
    ) {
        if (body instanceof ParcelRequest request) {
            request.setName(InputSanitizer.sanitizePlainText(request.getName()));
            request.setOwnerOrLead(InputSanitizer.sanitizePlainText(request.getOwnerOrLead()));
        }
        return body;
    }
}
