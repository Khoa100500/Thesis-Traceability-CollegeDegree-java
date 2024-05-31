package com.iupv.demo.User;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserDto(String username) implements Serializable {
}