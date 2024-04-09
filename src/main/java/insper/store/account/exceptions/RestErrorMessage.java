package insper.store.account.exceptions;

import org.apache.hc.core5.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter 
@Setter
public class RestErrorMessage {
    private Integer status;
    private String message;
    
}
