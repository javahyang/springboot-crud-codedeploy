package com.javahyang.springbootcrudcodedeploy.application;

import com.javahyang.springbootcrudcodedeploy.domain.Client;
import com.javahyang.springbootcrudcodedeploy.domain.ClientRepository;
import com.javahyang.springbootcrudcodedeploy.dto.ClientRequest;
import com.javahyang.springbootcrudcodedeploy.dto.ClientResponse;
import com.javahyang.springbootcrudcodedeploy.exception.ClientUndefinedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // 전체 고객 목록 조회
    @Transactional(readOnly = true)
    public List<ClientResponse> getClients() {
        List<Client> clients = clientRepository.findAll();

        return clients.stream()
                .map(ClientResponse::of)
                .collect(Collectors.toList());
    }

    // 고객 조회
    @Transactional(readOnly = true)
    public ClientResponse getClinet(Long id) {
        final Client clinet = findById(id);

        return ClientResponse.of(clinet);
    }

    // 고객 등록
    public ClientResponse saveClient(ClientRequest request) {

        return ClientResponse.of(clientRepository.save(request.toClient()));
    }

    // 고객 수정
    public void update(Long id, ClientRequest clientRequest) {
        final Client client = findById(id);

        client.update(clientRequest.toClient());
    }

    // 고객 삭제
    public void deleteClient(Long id) {
        final Client clinet = findById(id);

        clientRepository.deleteById(clinet.getId());
    }

    @Transactional(readOnly = true)
    public Client findById(Long id) {

        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientUndefinedException(id));
    }
}
