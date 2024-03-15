package za.co.wyzetech.xabisa.core.validators;

import java.util.List;

public interface ObjectValidator<T> {

  public List<ValidationResult> validate(T data);

}
