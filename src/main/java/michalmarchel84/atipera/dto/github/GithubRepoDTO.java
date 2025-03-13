package michalmarchel84.atipera.dto.github;

import lombok.Data;

@Data
public class GithubRepoDTO {
    private String name;
    private GithubRepoOwnerDTO owner;
    private boolean fork;
}
