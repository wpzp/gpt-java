package org.example.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.example.entity.Message;

import java.util.List;

/**
 * @author mingyuan 2023/11/14
 */
@Data
public class GptResponse {

    /**
     * 标识此次对话的唯一ID
     */
    private String id;

    /**
     * 指定对象类型，通常为"chat.completion"
     */
    private String object;

    /**
     * 响应生成的时间戳
     */
    private Long created;

    /**
     * 指定使用的模型，通常为"gpt-3.5-turbo"
     */
    private String model;

    /**
     * 关于对话中token的使用情况的统计信息
     */
    private Usage usage;

    /**
     * 多个候选的回复
     */
    private List<Choices> choices;

    @Data
    public static class Choices {

        private Long index;

        private Message message;

        @JSONField(name = "finish_reason")
        private String finishReason;

    }


    @Data
    public static class Usage {

        /**
         * 指定用于输入的 token 数量。即对话历史中用户和系统消息的 token 总和
         */
        @JSONField(name = "prompt_tokens")
        private Long promptTokens;

        /**
         * 指定生成的助手回复的 token 数量
         */
        @JSONField(name = "completion_tokens")
        private Long completionTokens;

        /**
         * 对话中总的 token 数量，即 prompt_tokens 和 completion_tokens 的总和
         */
        @JSONField(name = "total_tokens")
        private Long totalTokens;

    }
}
