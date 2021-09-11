package com.javahyang.springbootcrudcodedeploy.domain;

import com.javahyang.springbootcrudcodedeploy.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "client")
public class Client extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void update(Client clinet) {
        this.name = clinet.getName();
        this.email = clinet.getEmail();
    }
}
