package ua.com.springboot.demo.Controllers;

import java.rmi.ServerException;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.springboot.demo.exception.RecordNotFoundException;
import ua.com.springboot.demo.model.UserDto;
import ua.com.springboot.demo.service.UserService;

@Slf4j
@RestController
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping(path = "setinfo",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity create(@NotNull @RequestBody UserDto newUser) throws ServerException {
    if (newUser == null) {
      throw new ServerException("Server error");
    } else {
      log.info("Saved: {}", newUser);
      userService.saveUserDto(newUser);
      return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
  }

  @GetMapping("users")
  public ResponseEntity<List<UserDto>> getAll() {
    log.info("All entries: {}", userService.getAll());
    return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
  }

  @GetMapping("userinfo/{id}")
  public ResponseEntity getById(@PathVariable final long id) {
    List<UserDto> user = userService.getById(id);
    if (!user.isEmpty()) {
      log.info("Get userinfo/{id}: {}", user);
      return new ResponseEntity(user, HttpStatus.OK);
    } else {
      throw new RecordNotFoundException();
    }
  }

  @GetMapping("levelinfo/{level}")
  public ResponseEntity getByLevel(@PathVariable final long level) {
    List<UserDto> user = userService.getByLevel(level);
    if (!user.isEmpty()) {
      log.info("Get levelinfo/{level}: {}", user);
      return new ResponseEntity(user, HttpStatus.OK);
    } else {
      throw new RecordNotFoundException();
    }
  }
}
