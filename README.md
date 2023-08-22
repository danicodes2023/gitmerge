**Git Bridge**

Git Bridge is a POC Spring Boot application that demonstrates the functionality of copying code from a specified Bitbucket repository to another specified GitHub repository. This repository provides an API service that facilitates the code copying process. This project showcases a simplified implementation.

Configuration

To configure the application, you need to set up the required environment variables
Bitbucket Configuration:

•	BITBUCKET_REPO_URL: URL of the Bitbucket repository to copy from.

•	BITBUCKET_USERNAME: Your Bitbucket username.

•	BITBUCKET_TOKEN: Access token or password for Bitbucket authentication.
GitHub Configuration:

•	GITHUB_REPO_URL: URL of the GitHub repository to copy to.
•	GITHUB_USERNAME: Your GitHub username.
•	GITHUB_TOKEN: Personal access token for GitHub authentication.
HTTP Proxy Configuration (if applicable):

•	HTTP_PROXYHOST: Hostname of the HTTP proxy server.
•	HTTP_PROXYPORT: Port of the HTTP proxy server.
•	HTTP_PROXYUSER: Username for HTTP proxy authentication.
•	HTTP_PROXYPASSWORD: Password for HTTP proxy authentication.

API Endpoint

•	Endpoint: POST /gitcopy/api/copyrepo
•	This endpoint triggers the service to copy code from the specified Bitbucket repository to the GitHub repository.
