package michalmarchel84.atipera.dto.github;

import lombok.Data;

@Data
public class GithubBranchDTO {
    private String name;
    private GithubCommitDTO commit;
}
