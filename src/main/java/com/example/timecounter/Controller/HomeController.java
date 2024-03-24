package com.example.timecounter.Controller;

import com.example.timecounter.Service.TimeClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private final TimeClockService timeClockService;

    public HomeController(TimeClockService timeClockService) {
        this.timeClockService = timeClockService;
    }

    @GetMapping("/")
    public String index(Model model) {
//        model.addAttribute("elapsedTime", timeClockService.getFormattedElapsedTime());
        return "index";
    }

    @PostMapping("/stopClock")
    public ResponseEntity<String> stopClock(@RequestParam("elapsedTime") long elapsedTime) {
        timeClockService.stopClock(elapsedTime);
        return ResponseEntity.ok("Time stopped successfully.");
    }

}
