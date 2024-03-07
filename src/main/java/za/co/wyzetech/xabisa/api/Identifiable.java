package za.co.wyzetech.xabisa.api;

import java.io.Serializable;

public interface Identifiable extends Serializable {
  <I extends Serializable> I getId();
}