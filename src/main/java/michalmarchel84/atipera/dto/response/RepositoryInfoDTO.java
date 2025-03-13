package michalmarchel84.atipera.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RepositoryInfoDTO {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchInfoDTO> branches;
}
