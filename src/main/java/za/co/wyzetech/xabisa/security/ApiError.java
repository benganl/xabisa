package za.co.wyzetech.xabisa.security;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import lombok.Data;
import za.co.wyzetech.xabisa.core.validators.ValidationResult;

@Data
public class ApiError implements Serializable {
  private static final long serialVersionUID = 1L;
  private HttpStatus status;
  private String message;
  private List<ValidationResult> validationErrors; // list of ValidationResults

  public ApiError(HttpStatus status, String message, List<ValidationResult> validationErrors) {
    this.status = status;
    this.message = message;
    this.validationErrors = validationErrors;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ApiError [");
    if (status != null) {
      builder.append("status=");
      builder.append(status);
      builder.append(", ");
    }
    if (message != null) {
      builder.append("message=");
      builder.append(message);
      builder.append(", ");
    }
    if (validationErrors != null && validationErrors.size() > 0) {
      StringBuilder errorBuilder = new StringBuilder();
      for(ValidationResult result : validationErrors) {
        final Map<String, String> errorMap = result.getErrors(); // Map here
        errorMap.keySet().forEach((a) -> errorBuilder.append(errorMap.get(a).toString()));
      }
      builder.append("validationErrors=[[[[[[");
      builder.append(errorBuilder.toString());
      builder.append("]]]]]]");
    }
    builder.append("]");
    return builder.toString();
  }
}
