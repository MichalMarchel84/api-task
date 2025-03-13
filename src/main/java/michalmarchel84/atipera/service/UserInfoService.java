package michalmarchel84.atipera.service;

import io.smallrye.mutiny.Multi;
import michalmarchel84.atipera.dto.response.RepositoryInfoDTO;

public interface UserInfoService {
    public Multi<RepositoryInfoDTO> getUserRepositories(String userName);
}
