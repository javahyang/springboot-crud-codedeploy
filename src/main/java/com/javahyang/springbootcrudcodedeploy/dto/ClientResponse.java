package com.javahyang.springbootcrudcodedeploy.dto;

import com.javahyang.springbootcrudcodedeploy.domain.Client;

import java.time.LocalDateTime;

public class ClientResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public ClientResponse() {
    }

    public ClientResponse(Long id, String name, String email, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public static ClientResponse of(Client client) {

        return new ClientResponse(client.getId(), client.getName(), client.getEmail(), client.getCreatedDate(), client.getModifiedDate());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
