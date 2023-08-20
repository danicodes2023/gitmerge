package com.example.gitmerge.service;

import com.example.gitmerge.util.GitMergeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GitMergeService {
   /* @Value("${test}")
    private String test;*/
    @Autowired
    private GitMergeUtil gitMergeUtil;

    public String gitMerge() {
        log.info("Test log");
        /*log.info("Test from props : "+test);*/
        gitMergeUtil.repoSync();
        return "Success";
    }
}
