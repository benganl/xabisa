package za.co.wyzetech.xabisa.security.exception;

import lombok.Getter;

@Getter
public class SecurityException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public static final Integer USER_NAME_NULL = 4001;
  public static final Integer PASSWORD_NULL = 4002;

  private final String message;
  private final Integer code;

  public SecurityException(String message, Integer code) {
    super(message);
    this.message = message;
    this.code = code;
  }
}
