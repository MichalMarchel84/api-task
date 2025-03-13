package michalmarchel84.atipera;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;

@QuarkusIntegrationTest
@TestProfile(GithubUserInfoIT.class)
public class GithubUserInfoIT implements QuarkusTestProfile{
    private static ClientAndServer mockServer;

    @Override
    public Map<String, String> getConfigOverrides() { 
        return Map.of("quarkus.rest-client.github-api.url", "http://localhost:1080");
    }
    
    @BeforeAll
    public static void startMockServer() {
        mockServer = startClientAndServer(1080);
    }

    @AfterAll
    public static void stopMockServer() {
        mockServer.stop();
    }

    @Test
    public void testHappyPath() {
        mockServer.when(request().withMethod("GET").withPath("/users/MichalMarchel84/repos"))
        .respond(response().withStatusCode(200).withContentType(MediaType.APPLICATION_JSON).withBody(ResponseHelper.getUserInfoResponse));

        mockServer.when(request().withMethod("GET").withPath("/repos/MichalMarchel84/RemoteControlServer/branches"))
        .respond(response().withStatusCode(200).withContentType(MediaType.APPLICATION_JSON).withBody(ResponseHelper.getBranchesResponse));

        given()
            .pathParam("userName", "MichalMarchel84")
        .when()
            .get("/api/{userName}")
        .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("$", hasItem(allOf(
                hasEntry("repositoryName", "RemoteControlServer"),
                hasEntry("ownerLogin", "MichalMarchel84")
            )))
            .body("[0].branches.size()", is(1))
            .body("[0].branches", hasItem(allOf(
                hasEntry("branchName", "master"),
                hasEntry("lastCommitSha", "3c4c9887a115aa99156418d039cd5db05d2330f8")
            )));
    }
}
