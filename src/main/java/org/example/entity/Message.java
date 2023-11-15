package org.example.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author mingyuan 2023/11/14
 */
@Data
@Builder
public class Message {

    /**
     * 角色
     */
    private String role;

    /**
     * 消息
     */
    private String content;
}
