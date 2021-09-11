package com.javahyang.springbootcrudcodedeploy.ui;

import com.javahyang.springbootcrudcodedeploy.application.ClientService;
import com.javahyang.springbootcrudcodedeploy.dto.ClientRequest;
import com.javahyang.springbootcrudcodedeploy.dto.ClientResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // 전체 고객 목록 조회
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientResponse>> getClients() {

        return ResponseEntity.ok().body(clientService.getClients());
    }

    // 고객 조회
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponse> getClient(@PathVariable Long id) {

        return ResponseEntity.ok().body(clientService.getClinet(id));
    }

    // 고객 등록
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveClient(@RequestBody ClientRequest clientRequest) throws URISyntaxException {
        ClientResponse response = clientService.saveClient(clientRequest);

        return ResponseEntity.created(new URI("/clients/" + response.getId())).body(response);
    }

    // 고객 수정
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody ClientRequest clientRequest) {

        clientService.update(id, clientRequest);

        return ResponseEntity.ok().body(clientService.getClinet(id));
    }

    // 고객 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {

        clientService.deleteClient(id);

        return ResponseEntity.noContent().build();
    }
}
