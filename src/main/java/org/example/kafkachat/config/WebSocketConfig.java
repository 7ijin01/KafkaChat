package org.example.kafkachat.config;

import lombok.RequiredArgsConstructor;
import org.example.kafkachat.chat.interceptor.WebSockerInterceptor;
import org.example.kafkachat.webRTC.handler.WebRTCSignalHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer, WebSocketConfigurer
{
    private final WebSockerInterceptor webSockerInterceptor;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry)
    {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry)
    {
        //이건 구독 경로
        registry.enableSimpleBroker("/topic");
        
        //이건 send때 경로
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSockerInterceptor);
    }


    //WebRTC 설정
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    {
        registry.addHandler(new WebRTCSignalHandler(),"/signal").setAllowedOriginPatterns("*");
    }
}
