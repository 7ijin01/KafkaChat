package org.example.kafkachat.webRTC.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class WebRTCSignalHandler extends TextWebSocketHandler
{
    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception
    {
        log.info("web RTC 연결됨:",session.getId());
        sessions.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
    {
        log.info("수신된 메시지: " + message.getPayload());
        for (WebSocketSession wsSession : sessions.values()) {
            if (!wsSession.getId().equals(session.getId())) {
                wsSession.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("WebRTC WebSocket 연결 종료: " + session.getId());
        sessions.remove(session.getId());
    }
}
