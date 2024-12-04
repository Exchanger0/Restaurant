package com.restaurant.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.restaurant.model.Employee;
import com.restaurant.model.Summary;
import com.restaurant.service.SummaryService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SummaryController {
    private final SummaryService summaryService;

    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

    @Autowired
    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping("/summary")
    public String getSummaryForm(Model model) {
        LOGGER.info("Received a GET request to url: /summary");

        model.addAttribute("summary", new Summary());

        return "summary/form";
    }

    @PostMapping("/summaries")
    public String createSummary(@Valid @ModelAttribute(name = "summary") Summary summary, BindingResult result) {
        LOGGER.info("Received a POST request to url: /summaries");

        if (result.hasErrors()) {
            LOGGER.info("Give: {}", summary);
            LOGGER.info("Errors: {}", result.getAllErrors());
            return "summary/form";
        }

        summaryService.save(summary);

        LOGGER.info("Save: {}", summary);
        return "redirect:/menu";
    }

    @GetMapping("/summaries")
    @ResponseBody
    public List<Summary> getSummaries() {
        LOGGER.info("Received a GET request to url: /summaries");
        return summaryService.findAll();
    }

    @GetMapping("/summaries/{summaryId}")
    @ResponseBody
    public ResponseEntity<Summary> getSummary(@PathVariable("summaryId") int summaryId) {
        LOGGER.info("Received a GET request to url: /summaries/{}", summaryId);

        Summary summary = summaryService.findById(summaryId);
        if (summary != null)
            return ResponseEntity.ok(summary);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/summaries/{summaryId}")
    public ResponseEntity<String> delete(@PathVariable("summaryId") int summaryId) {
        LOGGER.info("Received a DELETE request to url: /summaries/{}", summaryId);
        summaryService.deleteById(summaryId);
        LOGGER.info("Delete employee with id = {}", summaryId);
        return ResponseEntity.ok("success delete");
    }

}
