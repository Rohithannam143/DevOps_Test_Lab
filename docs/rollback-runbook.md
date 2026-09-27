# Rollback Runbook

## Automated rollback
The Jenkins pipeline copies the current WAR to `webapps/rollback/student-feedback-portal.war.prev` before deployment. If the deployment or health stage fails, the `post { failure { ... } }` block restores that WAR.

## Manual rollback
```bash
sudo cp /opt/tomcat/webapps/rollback/student-feedback-portal.war.prev /opt/tomcat/webapps/student-feedback-portal.war
sudo chown tomcat:tomcat /opt/tomcat/webapps/student-feedback-portal.war
```
Then verify:
```bash
curl -fsS http://localhost:8080/student-feedback-portal/health
```

## Failure demonstration required by the project brief
1. Temporarily break a unit test.
2. Run Jenkins and show the Unit Test stage failing before Deploy.
3. Restore the test.
4. Re-run and show the successful pipeline.
5. For a bad deployment, use the archived/previous WAR and verify the health endpoint again.
