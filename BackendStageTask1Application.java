package com.task1.hng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class BackendStageTask1Application {

    public static void main(String[] args) {
        SpringApplication.run(BackendStageTask1Application.class, args);
    }
}

@RestController
class MyController {

    @GetMapping("/api")
    public Map<String, Object> myEndpoint(
            @RequestParam(name = "slack_name") String slackName,
            @RequestParam(name = "track") String track) {

        // Check if slack_name and track parameters are provided
        if (slackName == null || track == null) {
            throw new IllegalArgumentException("Both slack_name and track are required");
        }

     // Get the current day of the week
        String currentDay = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("EEEE"));

        // Get the current UTC time in ISO 8601 format
        String currentTime = ZonedDateTime.now().toInstant().toString();
        // Construct the GitHub URLs based on your project
        String githubRepoUrl = "https://github.com/nikitadjadhav31/hng";
        String githubFileUrl = githubRepoUrl + "/blob/main/BackendStageTask1Application.java";

        // Create the response JSON
        Map<String, Object> response = new HashMap<>();
        response.put("slack_name", slackName);
        response.put("current_day", currentDay);
        response.put("utc_time", currentTime);
        response.put("track", track);
        response.put("github_file_url", githubFileUrl);
        response.put("github_repo_url", githubRepoUrl);
        response.put("status_code", 200);

        return response;
    }
}
