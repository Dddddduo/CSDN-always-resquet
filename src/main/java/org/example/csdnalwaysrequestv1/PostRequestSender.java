package org.example.csdnalwaysrequestv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class PostRequestSender {
    private String url;
    static long ans = 0;

    public PostRequestSender(String url) {
        this.url = url;
    }

    public void sendPostRequest() throws IOException {
        // 创建URL对象
        URL obj = new URL(url);

        // 打开连接
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // 设置请求方法为POST
        con.setRequestMethod("POST");

        // 设置请求头

        con.setRequestProperty("User-Agent", "Java client");
        con.setRequestProperty("Content-Type", "application/json");

        // 可选：设置请求体
        Random random = new Random();
        int randomValue = random.nextInt(10); // 生成一个0到9的随机整数
        String postData = "{\"key1\":\"value1\", \"key2\":\"" + randomValue + "\"}";
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = postData.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // 获取响应
        int responseCode = con.getResponseCode();
        System.out.println("Sending POST request to URL: " + url);
        System.out.println("Response Code: " + responseCode);
        System.out.println("run " + (ans++) + " ...");

        // 读取响应内容
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            // 打印响应内容
//            System.out.println("Response Body: " + response.toString());
        }
    }
}