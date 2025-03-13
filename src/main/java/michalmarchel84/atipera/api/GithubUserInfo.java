package michalmarchel84.atipera.api;

import org.jboss.resteasy.reactive.RestPath;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import michalmarchel84.atipera.dto.response.RepositoryInfoDTO;
import michalmarchel84.atipera.service.UserInfoService;

@Path("/api")
public class GithubUserInfo {
    @Inject
    private UserInfoService userInfoService;

    public GithubUserInfo(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GET
    @Path("/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<RepositoryInfoDTO> getUserInfo(@RestPath String userName) {
        return userInfoService.getUserRepositories(userName);
    }
}
