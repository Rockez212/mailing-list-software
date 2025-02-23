package com.soft.mailinglist.mapper;

import com.soft.mailinglist.dto.UserDTO;
import com.soft.mailinglist.entity.User;
import lombok.Data;

@Data
public class UserMapper {

    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getUsername(),
                user.getEmail()
        );
    }

}
