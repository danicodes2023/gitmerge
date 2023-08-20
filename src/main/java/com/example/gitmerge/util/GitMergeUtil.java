package com.example.gitmerge.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class GitMergeUtil {

    @Value("${test}")
    private String test;//Test with environment vars
    @Value("${test2}")
    private String test2;//Test with environment vars
    @Value("${bitbucket.repo.url}")
    private String bitbucketRepoUrl;
    @Value("${github.repo.url}")
    private String githubRepoUrl;
    @Value("${bitbucket.username}")
    private String bitbucketUsername;
    @Value("${bitbucket.token}")
    private String bitbucketToken;
    @Value("${github.username}")
    private String githubUsername;
    @Value("${github.token}")
    private String githubToken;

    public void repoSync() {
        log.info("bitbucketRepoUrl from props: "+bitbucketRepoUrl);
        log.info("BITBUCKET_REPO_URL: {}", bitbucketRepoUrl);
        log.info("Test from props : "+test);
        log.info("test2 from env : "+test2);
        File localPath = null;
        Git git = null;
        try {
            localPath = new File(".\\src\\main\\resources\\tempCloneDir");
            if (localPath.exists()) {
                try {
                    FileUtils.deleteDirectory(localPath);
                    log.info("11Local directory deleted.");
                } catch (Exception e) {
                    //log.error("Failed to delete local directory.", e);
                    throw new Exception("11Failed to delete local directory.", e);
                }
            }
            // Clone the Bitbucket repository
            CloneCommand cloneCommand = Git.cloneRepository()
                    .setURI(bitbucketRepoUrl)
                    .setDirectory(localPath)
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(bitbucketUsername, bitbucketToken));
            //.setCredentialsProvider(new UsernamePasswordCredentialsProvider(BITBUCKET_USERNAME, BITBUCKET_PASSWORD));
            git = cloneCommand.call();

            // Add Github repository as a remote
            git.remoteAdd()
                    .setName("github")
                    .setUri(new URIish(githubRepoUrl))
                    .call();

            // Fetch all references from Bitbucket repository
            git.fetch()
                    .setRemote("origin")
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(bitbucketUsername, bitbucketToken))
                    .call();

            // Push all references to Github repository
            PushCommand pushCommand = git.push();
            pushCommand.setRemote("github")
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(githubUsername, githubToken))
                    .setForce(true)
                    .setPushAll()
                    .call();

            log.info("Code with commit history successfully copied from Bitbucket to Github.");
        } catch (Exception e) {
            log.error("Exception caught during git merge", e);
        } finally {
            if (git != null) {
                git.close();
            }
            if (localPath != null && localPath.exists()) {
                try {
                    FileUtils.deleteDirectory(localPath);
                    log.info("22Local directory deleted.");
                } catch (Exception e) {
                    log.error("22Failed to delete local directory.", e);
                }
            }
        }
    }
}
