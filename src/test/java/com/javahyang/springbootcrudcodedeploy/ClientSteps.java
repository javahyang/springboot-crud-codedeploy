package com.javahyang.springbootcrudcodedeploy;

import com.javahyang.springbootcrudcodedeploy.dto.ClientRequest;
import com.javahyang.springbootcrudcodedeploy.dto.ClientResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientSteps {
    protected static ExtractableResponse<Response> 고객_등록_요청(ClientRequest request) {

        return RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/clients")
                .then().log().all()
                .extract();
    }

    protected static void 고객_등록됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    protected static Long 고객_등록되어_있음(ClientRequest request) {

        return 고객_등록_요청(request).as(ClientResponse.class).getId();
    }

    protected static ExtractableResponse<Response> 전체_고객_목록_조회_요청() {

        return RestAssured
                .given().log().all()
                .when()
                .get("/clients")
                .then().log().all()
                .extract();
    }

    protected static void 전체_고객_목록_조회_응답됨(ExtractableResponse<Response> response) {
        고객_목록_응답됨(response);
    }

    protected static void 고객_목록_응답됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    protected static List<Long> 고객_목록_포함됨(ExtractableResponse<Response> response) {

        return response.jsonPath().getList(".", ClientResponse.class)
                .stream()
                .map(ClientResponse::getId)
                .collect(Collectors.toList());
    }

    protected static ExtractableResponse<Response> 고객_정보_조회_요청(long id) {

        return RestAssured
                .given().log().all()
                .when()
                .get("/clients/{id}", id)
                .then().log().all()
                .extract();

    }

    protected static ExtractableResponse<Response> 고객_정보_삭제_요청(Long id) {

        return RestAssured
                .given().log().all()
                .when()
                .delete("/clients/{id}", id)
                .then().log().all()
                .extract();
    }

    protected static void 고객_정보_삭제됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    protected static ExtractableResponse<Response> 고객_정보_수정_요청(Long id, ClientRequest updateRequest) {

        return RestAssured
                .given().log().all()
                .body(updateRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put("/clients/{id}", id)
                .then().log().all()
                .extract();
    }

    protected static ClientResponse getClientResponse(ExtractableResponse<Response> response) {

        return response.jsonPath().getObject(".", ClientResponse.class);
    }

    protected static void 고객_정보_수정됨(Long id, ClientRequest updateRequest) {
        ExtractableResponse<Response> response = 고객_정보_조회_요청(id);
        ClientResponse clientResponse = getClientResponse(response);

        assertThat(clientResponse.getId()).isEqualTo(id);
        assertThat(clientResponse.getName()).isEqualTo(updateRequest.getName());
        assertThat(clientResponse.getEmail()).isEqualTo(updateRequest.getEmail());
    }
}
