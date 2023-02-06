package com.example.Waypoint02;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import container.BasicFunctions;
import container.Container;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import java.security.NoSuchAlgorithmException;


public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
      Container container = new Container();
    ObjectMapper objectMapper = new ObjectMapper();
    vertx.createHttpServer().requestHandler(req -> {
        try {
            req.response()
              .putHeader("content-type", "text/plain")
              .end(objectMapper.writeValueAsString(BasicFunctions.excute()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
