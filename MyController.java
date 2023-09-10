package com.task1.hng;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@RestController
public class MyController {

    @GetMapping("/api")
    public Map<String, Object> myEndpoint(
            @RequestParam(name = "slack_name") String slackName,
            @RequestParam(name = "track") String track) {

        // Check if slack_name and track parameters are provided
        if (slackName == null || track == null) {
            throw new IllegalArgumentException("Both slack_name and track are required");
        }

        // Get the current day of the week
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        String dayOfWeek = new SimpleDateFormat("EEEE").format(calendar.getTime());

        // Get the current UTC time
        String currentTime = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());

        // Construct the GitHub URLs based on your project
        String githubRepoUrl = "https://github.com/your_username/your_repository";
        String githubFileUrl = githubRepoUrl + "/path/to/your/file";

        // Create the response JSON
        Map<String, Object> response = new HashMap<>();
        response.put("slack_name", slackName);
        response.put("day_of_week", dayOfWeek);
        response.put("current_utc_time", currentTime);
        response.put("track", track);
        response.put("github_file_url", githubFileUrl);
        response.put("github_repo_url", githubRepoUrl);
        response.put("status_code", 200);

        return response;
    }
}

