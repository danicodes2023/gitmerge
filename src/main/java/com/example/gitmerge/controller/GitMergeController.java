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
    /*
    @PostMapping("testgitpost")
    //public String testPostGit(){
    public ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> payload) {
            // Access the "name" field from the payload

            Map<String, Object> repository = (Map<String, Object>) payload.get("repository");
            if(repository != null) {
                String repositoryName = (String) repository.get("name");
                if(repositoryName != null) {
                    System.out.println("Received payload from repository: " + repositoryName);
                    String responseMessage = "Received payload from repository: " + repositoryName;
                    return ResponseEntity.ok(responseMessage);
                }
                else{
                    return ResponseEntity.badRequest().body("Repository name missing");
                }
            }
            else{
                return ResponseEntity.badRequest().body("Repository missing");
            }

    }
    
    */

}
