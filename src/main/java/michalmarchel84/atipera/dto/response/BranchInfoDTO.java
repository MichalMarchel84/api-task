package michalmarchel84.atipera.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BranchInfoDTO {
    private String branchName;
    private String lastCommitSha;
}
