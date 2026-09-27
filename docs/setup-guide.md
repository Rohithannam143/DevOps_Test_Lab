# Setup Guide

## 1. Verify tools
```bash
java -version
mvn -version
git --version
```
The internship project targets Java 17; a newer JDK can compile it with the configured `--release 17` setting.

## 2. Build and test
```bash
mvn clean test
mvn package
ls -lh target/student-feedback-portal.war
```

## 3. Deploy to Tomcat 10.1
```bash
sudo cp target/student-feedback-portal.war /opt/tomcat/webapps/
sudo chown tomcat:tomcat /opt/tomcat/webapps/student-feedback-portal.war
sleep 5
curl -i http://localhost:8080/student-feedback-portal/health
```

## 4. Browser validation
Open the application URL and verify:
- landing page renders
- registration creates a student account
- login creates a session
- feedback submission succeeds
- dashboard lists the submission
- admin login opens the admin console
- admin status/priority update persists
- `/health` reports application/database UP

## 5. MySQL option
Create a database/user, run `db/schema-mysql.sql`, then set `DB_URL`, `DB_USER` and `DB_PASSWORD` before Tomcat starts. The default H2 mode is intended for the zero-cost internship demo.
