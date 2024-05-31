package com.iupv.demo.report;

import java.io.Serializable;

/**
 * DTO for {@link com.iupv.demo.User.User}
 */
public record UserDto(String userFullname) implements Serializable {
}