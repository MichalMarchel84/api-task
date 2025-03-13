package michalmarchel84.atipera.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RequestExceptionHandler implements ExceptionMapper<RestClientException>{
    @Override
    public Response toResponse(RestClientException err) {
        return Response
            .status(err.getStatus())
            .entity(new RequestExceptionDetails(err.getStatus(), err.getMessage()))
            .build();
    }
}
