package org.example.request;

import lombok.Data;
import org.example.entity.Message;

import java.util.List;

/**
 * @author mingyuan 2023/11/14
 */
@Data
public class MessageRequest {

    /**
     * 请求模型
     */
    private String model;

    /**
     * 请求体
     */
    List<Message> messages;
}
