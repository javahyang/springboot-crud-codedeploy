package com.javahyang.springbootcrudcodedeploy.dto;

import com.javahyang.springbootcrudcodedeploy.domain.Client;
import lombok.Getter;

@Getter
public class ClientRequest {
    private String name;
    private String email;

    public ClientRequest() {
    }

    public ClientRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Client toClient() {
        return new Client(name, email);
    }
}
