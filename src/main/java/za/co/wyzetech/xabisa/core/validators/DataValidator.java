package za.co.wyzetech.xabisa.core.validators;

public interface DataValidator<T> {
  ValidationResult validate(String property, T t, boolean nullable, int min, int max);
}
