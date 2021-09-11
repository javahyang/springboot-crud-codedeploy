package com.javahyang.springbootcrudcodedeploy;

import com.javahyang.springbootcrudcodedeploy.dto.ClientRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.javahyang.springbootcrudcodedeploy.ClientSteps.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("고객 정보 기능")
public class ClientAcceptanceTest extends AcceptanceTest {

    @DisplayName("고객을 등록한다.")
    @Test
    void createClient() {
        // given
        ClientRequest request = new ClientRequest("javahyang1", "javahyang1@gmail.com");

        // when
        final ExtractableResponse<Response> response = 고객_등록_요청(request);

        // then
        고객_등록됨(response);
    }

    @DisplayName("전체 고객 목록을 조회한다.")
    @Test
    void getClients() {
        // given
        List<Long> clientResponseIds = new ArrayList();
        clientResponseIds.add(고객_등록되어_있음(new ClientRequest("javahyang1", "javahyang1@gmail.com")));
        clientResponseIds.add(고객_등록되어_있음(new ClientRequest("javahyang2", "javahyang2@gmail.com")));
        clientResponseIds.add(고객_등록되어_있음(new ClientRequest("javahyang3", "javahyang3@gmail.com")));

        // when
        final ExtractableResponse<Response> response = 전체_고객_목록_조회_요청();

        // then
        전체_고객_목록_조회_응답됨(response);
        List<Long> responseIds = 고객_목록_포함됨(response);
        assertThat(responseIds).containsAll(clientResponseIds);
    }

    @DisplayName("고객을 조회한다.")
    @Test
    void getClient() {
        // given
        ClientRequest request = new ClientRequest("javahyang1", "javahyang1@gmail.com");
        final Long id = 고객_등록되어_있음(request);

        // when
        ExtractableResponse<Response> response = 고객_정보_조회_요청(id);

        // then
        고객_목록_응답됨(response);
    }

    @DisplayName("고객 정보를 수정한다.")
    @Test
    void updateClient() {
        // given
        ClientRequest request = new ClientRequest("javahyang1", "javahyang1@gmail.com");
        final Long id = 고객_등록되어_있음(request);

        // when
        ClientRequest updateRequest = new ClientRequest("javahyang2", "javahyang2@gmail.com");
        final ExtractableResponse<Response> updateResponse = 고객_정보_수정_요청(id, updateRequest);

        // then
        고객_목록_응답됨(updateResponse);
        고객_정보_수정됨(id, updateRequest);

    }

    @DisplayName("고객 정보를 삭제한다.")
    @Test
    void deleteClient() {
        // given
        ClientRequest request = new ClientRequest("javahyang1", "javahyang1@gmail.com");
        final Long id = 고객_등록되어_있음(request);

        // when
        ExtractableResponse<Response> response = 고객_정보_삭제_요청(id);

        // then
        고객_정보_삭제됨(response);
    }
}
