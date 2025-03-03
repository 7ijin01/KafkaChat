package org.example.kafkachat.chat.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kafkachat.jwt.JWTUtil;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSockerInterceptor implements ChannelInterceptor
{

    private final JWTUtil jwtUtil;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel)
    {
        StompHeaderAccessor accessor=StompHeaderAccessor.wrap(message);
        StompCommand command=accessor.getCommand();
        String token= accessor.getFirstNativeHeader("Authorization");
        log.info("token:{}",token);
        if (token==null)
        {
            log.error("token is null");
            return message;
        }
        token=token.substring(7);

        if(jwtUtil.isExpired(token))
        {
            log.error("token is expired");
            return message;
        }

        if(command.equals(StompCommand.CONNECT))
        {
            String username=jwtUtil.getUserName(token);
            accessor.getSessionAttributes().put("username",username);
            accessor.addNativeHeader("username",username);
            log.info("username:{}",username);
        }
        return message;

    }
}
