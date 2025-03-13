package michalmarchel84.atipera.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import michalmarchel84.atipera.dto.github.GithubRepoDTO;
import michalmarchel84.atipera.dto.response.BranchInfoDTO;
import michalmarchel84.atipera.dto.response.RepositoryInfoDTO;

@ApplicationScoped
public class UserInfoServiceImpl implements UserInfoService{
    @Inject
    @RestClient
    private GithubRestClient githubRestClient;

    public UserInfoServiceImpl(@RestClient GithubRestClient githubRestClient) {
        this.githubRestClient = githubRestClient;
    }

    public Multi<RepositoryInfoDTO> getUserRepositories(String userName) {
        return Multi.createFrom().emitter(emmiter -> {
            githubRestClient.getUserInfo(userName)
                .onFailure().invoke(err -> emmiter.fail(err))
                .subscribe().with(response -> {
                    List<GithubRepoDTO> repos = response.stream().filter(repo -> !repo.isFork()).collect(Collectors.toList());
                    AtomicInteger counter = new AtomicInteger();

                    repos.forEach(repo -> {
                        githubRestClient.getBranches(userName, repo.getName())
                            .subscribe().with(branches -> {
                                List<BranchInfoDTO> branchInfo = branches.stream()
                                    .map(branch -> new BranchInfoDTO(branch.getName(), branch.getCommit().getSha()))
                                    .collect(Collectors.toList());
                                    
                                emmiter.emit(new RepositoryInfoDTO(repo.getName(), repo.getOwner().getLogin(), branchInfo));

                                if (counter.incrementAndGet() == repos.size()) {
                                    emmiter.complete();
                                }
                            });
                    });
                });
        });
    }
}
