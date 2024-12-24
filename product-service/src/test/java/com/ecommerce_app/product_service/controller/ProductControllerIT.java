package com.ecommerce_app.product_service.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ProductControllerIT {
  @LocalServerPort
  private Integer port;
  @Container
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0");


  @BeforeEach
  void setUp() {
    RestAssured.baseURI = "http://localhost:" + port;
  }

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @Test
  void createProduct() {
    String requestBody = """
        {
          "name":"nebulizador",
          "description":"Nebulizador a pistón",
          "category":"ELECTRONICS",
          "price":50.0,
          "sku":"n61"
        }      \s
       \s""";

    Response response = RestAssured.given()
        .contentType("application/json")
        .body(requestBody)
        .post(RestAssured.baseURI + "/api/products");

    response.then().statusCode(201);
    response.then().body("name", equalTo("nebulizador"));
    response.then().body("description", containsString("Nebulizador"));
    response.then().body("category", equalTo("ELECTRONICS"));
    response.then().body("price", equalTo(50.0F));
    response.then().body("sku", equalTo("n61"));
    response.then().body("id", notNullValue());
  }


}
