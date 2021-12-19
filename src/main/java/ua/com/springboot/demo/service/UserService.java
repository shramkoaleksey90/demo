package ua.com.springboot.demo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ua.com.springboot.demo.model.UserDto;

@Service
public class UserService {
  private List<UserDto> userDtoList = new ArrayList<>();

  public List<UserDto> getAll() {
    return userDtoList;
  }

  public List<UserDto> getById(long id) {
    return userDtoList.stream()
        .filter(user -> user.getUser_id().equals(id))
        .sorted(Comparator.comparingLong(UserDto::getResult).reversed()
            .thenComparing(Comparator.comparingLong(UserDto::getLevel_id).reversed()))
        .limit(20)
        .collect(Collectors.toUnmodifiableList());
  }

  public List<UserDto> getByLevel(long level) {
    return userDtoList.stream()
        .filter(user -> user.getLevel_id().equals(level))
        .sorted(Comparator.comparingLong(UserDto::getResult).reversed()
            .thenComparing(Comparator.comparingLong(UserDto::getUser_id).reversed()))
        .limit(20)
        .collect(Collectors.toUnmodifiableList());
  }

  public void saveUserDto(UserDto userDto) {
    userDtoList.add(userDto);
  }
}
