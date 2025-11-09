package com.example.TripAgora.chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final StompAuthInterceptor stompAuthInterceptor;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/connect") // 클라이언트가 웹소켓 연결요청 하기위한  엔드포인트 ex) https://api.canach3.com/connect
                .setAllowedOriginPatterns("*") // 앱 서비스이므로 어디에서든 웹소켓 연결 요청 허용(웹일 경우 따로 cors 설정 필요)
                .withSockJS(); // SockJS 라이브러리 : ws:// 가 아닌 http:// 로 웹소켓 연결요청을 가능하게 함
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 메시지를 발행할 경로 설정
        // ex) stompClient.send(/publish/chat/send/room/{roomId})
        // 클라이언트가 해당 엔드포인트로 요청시 ChatController의 @MessageMapping 어노테이션이 붙은 메서드로 전달됨
        registry.setApplicationDestinationPrefixes("/publish");

        // 클라이언트가 메시지를 수신할 경로 설정
        // ex) stompClient.subscribe(/topic/room/1...)
        registry.enableSimpleBroker("/topic");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // 필터에서 JWT 토큰을 검증하지 않기 때문에 따로 STOMP 연결 시 JWT 인증을 처리할 인터셉터 등록
        // 웹소켓 connect, subscribe, disconnect 요청시 항상 해당 인터셉터를 거치게 됨
        registration.interceptors(stompAuthInterceptor);
    }
}