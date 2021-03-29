package com.geek.security.springboot.model;

import lombok.Data;

/**
 * @author geek
 */
@Data
public class PermissionDto {

    private String id;
    private String code;
    private String description;
    private String url;

}
