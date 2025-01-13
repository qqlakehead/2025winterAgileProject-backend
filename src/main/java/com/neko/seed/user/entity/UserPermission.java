package com.neko.seed.user.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Usage                :
 * Project name         : seed
 * Author               :
 * Mail                 : @chinaexpressair.com
 * Date                 : 2025-01-11 14:05
 * Version              : 1.0
 * Modification history :
 * Date          Author          Version          Description
 * ---------------------------------------------------------------
 * 2025-01-11                    1.0             新建
 *
 * @Copyright(C)2008-2020: 华夏航空股份有限公司 All right Reserved.
 */
@Data
@Accessors(chain = true)
public class UserPermission {
    private String username;
    private List<Permission> list;
    private List<PermissionTree> tree;
    @Data
    @Accessors(chain = true)
    public static class Permission{
        private List<Permission> children;

        private String alias;
    }

    @Data
    @Accessors(chain = true)
    public static class PermissionTree{
        private List<PermissionTree> children;

        private String alias;
    }
}
