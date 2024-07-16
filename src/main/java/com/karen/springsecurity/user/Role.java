package com.karen.springsecurity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Getter
@AllArgsConstructor
public enum Role {
    CUSTOMER(Collections.singletonList(Permission.READ_ALL_PRODUCTS)),
    ADMIN(Arrays.asList(Permission.READ_ALL_PRODUCTS, Permission.SAVE_ONE_PRODUCT));
    private List<Permission> permissions;
}
