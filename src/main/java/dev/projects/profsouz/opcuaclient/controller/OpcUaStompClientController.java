package dev.projects.profsouz.opcuaclient.controller;

import dev.projects.profsouz.opcuaclient.domain.request.WsMetaInfoRequestDTO;
import dev.projects.profsouz.opcuaclient.domain.response.WsMetaInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.UUID;

import static dev.projects.profsouz.opcuaclient.utils.OpcUaWsMetaInfoDTOMapper.mapWsMetaInfoToResponseDTO;
import static dev.projects.profsouz.opcuaclient.utils.OpcUaWsStompCheckUtil.checkWsHostAndPort;
import static dev.projects.profsouz.opcuaclient.utils.OpcUaWsStompCheckUtil.createWsURLFromHostAndPort;

@Slf4j
@Controller
@RequestMapping(
        produces = "application/json",
        consumes = "application/json",
        value = "/opc-ua-ws-api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OpcUaStompClientController implements StompSessionHandler {
    /*@Autowired
    SocketProperties socketProperties; // TODO: Either implement this properties Bean or use hard-coded values
     */

    StompSession stompSession = null;

    /**
     * Map of subscriptions.

    @Getter
    Map<String, StompSession.Subscription> subscriptions = new HashMap<>();*/

    @PutMapping(value = "/connectToASNeGServer")
    public ResponseEntity<WsMetaInfoResponseDTO> connectToASNeGServer(@RequestBody WsMetaInfoRequestDTO requestDTO) throws Exception {
        checkWsHostAndPort(requestDTO.getWsHost(), requestDTO.getWsPort());

        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        String url = createWsURLFromHostAndPort(requestDTO.getWsHost(), requestDTO.getWsPort());

        stompSession = stompClient.connect(url, this).get();

        WsMetaInfoResponseDTO responseDTO = mapWsMetaInfoToResponseDTO(UUID.randomUUID(),
                stompSession.isConnected(),
                requestDTO.getWsHost(),
                requestDTO.getWsPort());

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping(value = "/disconnectFromASNeGServer")
    public ResponseEntity<WsMetaInfoResponseDTO> disconnectFromASNeGServer(@RequestBody WsMetaInfoRequestDTO requestDTO) throws Exception {
        Objects.requireNonNull(stompSession, "Unacceptable stompSession. It's the null.");

        WsMetaInfoResponseDTO responseDTO = mapWsMetaInfoToResponseDTO(UUID.randomUUID(),
                stompSession.isConnected(),
                requestDTO.getWsHost(),
                requestDTO.getWsPort());

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/checkConnectionToASNeGServer")
    public ResponseEntity<Boolean> isConnected() throws Exception {
        if (stompSession == null) {
            throw new NullPointerException("Unacceptable stompSession. It's the null.");
        }

        return ResponseEntity.ok(stompSession.isConnected());
    }

    /**
     * Subscribes to the feed of the room.
     *
     * @param roomId The id of the room to subscribe for.
     */
    public void subscribe(String roomId) {
        // TODO: Check subscriptions if already exist and is subscribed.
        log.info("Subscribing to room: {}", roomId);
        StompSession.Subscription subscription = stompSession.subscribe("/channel/" + roomId, this);
        //subscriptions.put(roomId, subscription);
    }

    public boolean isSubscribed(String roomId) {
       /* return subscriptions
                .containsKey(roomId);*/

        return false;
    }

    public void unsubscribe(String roomId) {
        /*subscriptions
                .get(roomId))
                .unsubscribe();
        subscriptions.remove(roomId);*/
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("Connection to STOMP server established.\n" +
                "Session: {}\n" +
                "Headers: {}", session, connectedHeaders);
        // TODO: Here you can subscribe to a list of rooms which you want to consume messages from after a connection was established.
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.info("Got an exception while handling a frame.\n" +
                "Command: {}\n" +
                "Headers: {}\n" +
                "Payload: {}\n" +
                "{}", command, headers, payload, exception);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.error("Retrieved a transport error: {}", session);
        exception.printStackTrace();
        /*if (!session.isConnected()) {
            subscriptions.clear();
           // connect(); TODO: Connect to a new server.`
        }*/
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
       // return StompMessage.class;
        return null;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Got a new message {}", payload);
        //StompMessage stompMessage = (StompMessage) payload; TODO: Handle possible ClassCastExceptions or different payload classes.
        // TODO: Consume the message, handle possible ClassCastExceptions or different payload classes.
    }

    /**
     * Unsubscribe and close connection before destroying this instance (e.g. on application shutdown).
     */
    /*@PreDestroy
    void onShutDown() {
        for (String key : subscriptions.keySet()) {
            subscriptions.get(key).unsubscribe();
        }

        if (stompSession != null) {
            stompSession.disconnect();
        }
    }*/
}
