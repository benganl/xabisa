package za.co.wyzetech.xabisa.core.validators;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ValidationResult implements Serializable {
  private static final long serialVersionUID = 1L;

  private final Map<String, String> errorList = new ConcurrentHashMap<String, String>();

  public ValidationResult() {
    super();
  }

  public Boolean hasErrors() {
    return !errorList.isEmpty();
  }

  public Map<String, String> getErrors() {
    final var copyErrorList = new HashMap<String, String>();

    errorList.keySet().forEach((key) -> {
      copyErrorList.put(key, errorList.get(key));
    });
    return copyErrorList;
  }

  public void add(String propertyName, String errorDescription) {
    errorList.put(propertyName, errorDescription);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ValidationResult [errorList=");
    errorList.keySet().forEach(key -> builder.append("{").append(key)
        .append(String.format(" {{ %s }} ", errorList.get(key))).append("}"));
    builder.append("]");
    return builder.toString();
  }
}
