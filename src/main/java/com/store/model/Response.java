package com.store.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

    public ResponseEntity myResponseBadRequest(MessageResponse messageResponse){
        return ResponseEntity.badRequest().body(messageResponse);
    }

    public ResponseEntity myResponseNotFound(MessageResponse messageResponse){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageResponse);
    }

    public ResponseEntity myResponseOK(){
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
