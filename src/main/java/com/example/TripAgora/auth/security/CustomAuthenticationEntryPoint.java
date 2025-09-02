package com.example.TripAgora.auth.security;

import com.example.TripAgora.common.code.AuthErrorCode;
import com.example.TripAgora.common.code.ResponseCode;
import com.example.TripAgora.common.response.ApiResponse;
import com.example.TripAgora.common.response.ApiResponseWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ResponseCode code = AuthErrorCode.UNAUTHORIZED_ACCESS;
        ApiResponseWriter.write(response, code.getStatusValue(), ApiResponse.fail(code));
    }
}