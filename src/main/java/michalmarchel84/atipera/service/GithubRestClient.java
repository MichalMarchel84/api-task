package michalmarchel84.atipera.service;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import michalmarchel84.atipera.dto.github.GithubBranchDTO;
import michalmarchel84.atipera.dto.github.GithubRepoDTO;

@RegisterRestClient(configKey = "github-api")
public interface GithubRestClient {
    @GET
    @Path("/users/{userName}/repos")
    Uni<List<GithubRepoDTO>> getUserInfo(@PathParam("userName") String userName);

    @GET
    @Path("/repos/{userName}/{repoName}/branches")
    Uni<List<GithubBranchDTO>> getBranches(@PathParam("userName") String userName, @PathParam("repoName") String repoName);
}
