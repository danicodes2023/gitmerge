package com.example.gitmerge.controller;

import com.example.gitmerge.service.GitMergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitMergeController {
    @Autowired
    GitMergeService gitMergeService;

    @GetMapping("testgit")
    public String testGitMerge(){
        return("Success");
    }

    @PostMapping("gitmerge")
    public String gitMerge(){
        return gitMergeService.gitMerge();
    }

}
