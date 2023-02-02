package com.technology.springboot.converter;

import com.technology.springboot.dto.UserDto;
import com.technology.springboot.model.User;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface UserConverter {

  List<UserDto> toDto(final List<User> suggestedFriends);

  UserDto toDto(final User user);

}
