package michalmarchel84.atipera.exception;

public class RestClientException extends Throwable{
    private int status;
    
    public RestClientException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}
