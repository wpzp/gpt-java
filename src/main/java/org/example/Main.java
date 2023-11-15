package org.example;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import org.example.entity.Message;
import org.example.enums.RuleEnums;
import org.example.request.MessageRequest;
import org.example.response.GptResponse;
import org.example.utils.GptClientUtils;

import java.util.*;

/**
 * @author mingyuan 2023/11/15
 */
public class Main {

    private static final String API_KEY = "Bearer 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";
    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions";


    // 缓存历史回话
    private static final List<Message> CACHE_MESSAGE = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner;
        while (true) {
            // 获取控制台输入文本
            scanner = new Scanner(System.in);
            String scannerText = scanner.nextLine();
            // 控制循环
            if (Objects.equals(scannerText, "exit")) {
                break;
            }
            // 生成请求body
            MessageRequest request = getGptRequestBody(scannerText, "gpt-3.5-turbo");
            Map<String, String> headMap = new HashMap<>();
            headMap.put("Authorization", API_KEY);
            GptResponse gptResponse = GptClientUtils.execute(headMap, JSONObject.toJSONString(request), ENDPOINT);
            // 解析GptResponse
            String responseText = parseGptResponse(request, gptResponse);
            System.out.println("gpt: " + responseText);
        }
    }

    private static String parseGptResponse(MessageRequest request, GptResponse gptResponse) {
        if (Objects.isNull(gptResponse) || CollectionUtil.isEmpty(gptResponse.getChoices())) {
            return "无响应...";
        }
        // 获取第一个答案
        GptResponse.Choices choices = gptResponse.getChoices().get(0);
        Message responseMessage = choices.getMessage();
        Message requestMessage = request.getMessages().get(request.getMessages().size() - 1);

        CACHE_MESSAGE.add(requestMessage);
        CACHE_MESSAGE.add(responseMessage);

        return responseMessage.getContent();
    }

    private static MessageRequest getGptRequestBody(String text, String model) {
        MessageRequest request = new MessageRequest();
        request.setModel(model);
        List<Message> messages = new ArrayList<>(CACHE_MESSAGE);
        messages.add(Message.builder().role(RuleEnums.USER.getRule()).content(text).build());
        request.setMessages(messages);
        return request;
    }
}