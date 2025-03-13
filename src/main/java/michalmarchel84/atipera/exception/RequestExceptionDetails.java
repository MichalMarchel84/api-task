package michalmarchel84.atipera.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestExceptionDetails {
    private int status;
    private String message;
}
