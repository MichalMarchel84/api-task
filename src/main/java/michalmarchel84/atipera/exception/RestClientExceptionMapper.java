package michalmarchel84.atipera.exception;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RestClientExceptionMapper implements ResponseExceptionMapper<RestClientException>{
    @Override
    public RestClientException toThrowable(Response response) {
        return new RestClientException(response.getStatus(), response.readEntity(RestClientExceptionDetails.class).getMessage());
    }
}
