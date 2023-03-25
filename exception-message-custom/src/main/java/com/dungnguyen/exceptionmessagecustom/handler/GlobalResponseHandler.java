//package com.dungnguyen.exceptionmessagecustom.handler;
//
//import java.util.ArrayList;
//
//import org.springdoc.webmvc.ui.SwaggerConfigResource;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//import io.swagger.v3.core.util.Json;
//
//@ControllerAdvice
//public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
//
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return true;
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
//            ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        if (methodParameter.getContainingClass().isAnnotationPresent(RestController.class)) {
//            if (!methodParameter.getMethod().isAnnotationPresent(IgnoreResponseBinding.class)) {
//                if (o instanceof RestBaseResponse || o instanceof String || o instanceof Json || (o instanceof ArrayList
//                        && !((ArrayList) o).isEmpty() && ((ArrayList) o).get(0) instanceof SwaggerConfigResource)) {
//                    return o;
//                }
//                if ((!(o instanceof RestErrorResponse)) && (!(o instanceof RestSuccessResponse))) {
//                    return new RestSuccessResponse(o, true);
//                }
//            }
//        }
//        // TODO: message
//        if (o instanceof RestErrorResponse) {
//            return o;
//        }
//        return new RestErrorResponse(500, "");
//    }
//
//}
