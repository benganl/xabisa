package za.co.wyzetech.xabisa.common;

import java.io.Serializable;
import lombok.Data;

@Data
public class Location implements Serializable {
  private static final long serialVersionUID = 1L;

  private Float latitude;
  private Float longitude;
}
