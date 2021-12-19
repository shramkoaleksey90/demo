package ua.com.springboot.demo.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class UserDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long user_id;
  private Long level_id;
  private Long result;
}
