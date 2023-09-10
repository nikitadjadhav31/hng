package com.task1.hng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@SpringBootApplication
public class BackendStageTask1Application {

    public static void main(String[] args) {
        SpringApplication.run(BackendStageTask1Application.class, args);
    }
}

@RestController
class MyController {

    @GetMapping("/api")
    public Map<String, Object> myEndpoint(jakarta.servlet.http.HttpServletRequest request) {

        // Get query parameters using the HttpServletRequest object
        String slackName = request.getParameter("slack_name");
        String track = request.getParameter("track");

        // Check if slack_name and track parameters are provided
        if (slackName == null || track == null) {
            throw new IllegalArgumentException("Both slack_name and track are required");
        }

        // Get the current day of the week
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        String dayOfWeek = new SimpleDateFormat("EEEE").format(calendar.getTime());

        // Get the current UTC time
        String currentTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(calendar.getTime());

        // Construct the response JSON
        Map<String, Object> response = new HashMap<>();
        response.put("slack_name", slackName);
        response.put("current_day", dayOfWeek);
        response.put("utc_time", currentTime);
        response.put("track", track);
        response.put("github_file_url", "https://github.com/nikitadjadhav31/hng/blob/main/BackendStageTask1Application.java");
        response.put("github_repo_url", "https://github.com/nikitadjadhav31/hng");
        response.put("status_code", 200);

        return response;
    }
}
