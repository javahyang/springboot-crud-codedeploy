package com.javahyang.springbootcrudcodedeploy.exception;

public class ClientUndefinedException extends IllegalArgumentException {
    public ClientUndefinedException() {
    }

    public ClientUndefinedException(Long id) {
        super("고객 아이디가 존재하지 않습니다. ID : " + id);
    }
}
