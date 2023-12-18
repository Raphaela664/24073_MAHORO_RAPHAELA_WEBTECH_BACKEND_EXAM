package com.aua.fexam_backend.controller;

import com.aua.fexam_backend.service.SubmissionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(
        value = "/submission",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class SubmissionController {

    SubmissionService submissionService;

}
