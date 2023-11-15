package org.example.enums;

import lombok.Getter;

/**
 * @author mingyuan 2023/11/14
 */
@Getter
public enum RuleEnums {

    // 用于系统级别的消息，例如定义对话的行为或设置模型的参数。系统消息通常在对话的开始或中间出现，以指导模型的行为
    SYSTEM("system"),
    // 表示用户的消息，即用户输入的对话历史。这是模型根据其进行回复的部分。
    USER("user"),
    // 表示助手（模型）生成的消息，即模型对用户请求或对话历史的回应。
    ASSISTANT("assistant"),
    ;


    private final String rule;

    RuleEnums(String rule) {
        this.rule = rule;
    }
}
