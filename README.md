# FeedbackHub — Student Experience Intelligence Platform

A professional Student Feedback System implemented as a Java 17 Maven WAR application and aligned to the internship's required DevOps flow: Git → Jenkins → Maven build/test/package → WAR archive → Tomcat deployment → health verification → rollback.

## Product scope
- Student registration/login/logout with session authentication
- PBKDF2-HMAC-SHA256 password hashing using the Java standard library
- Student dashboard and feedback history
- Structured feedback: category, title, rating, detailed message, anonymous option
- Admin operations console with live metrics, category pulse and feedback action center
- Status lifecycle: SUBMITTED → UNDER_REVIEW → ACTION_IN_PROGRESS → RESOLVED → CLOSED
- Priority and action updates
- Persistent database: H2 file database by default for zero-config demo; MySQL supported via environment variables
- Health endpoint for Jenkins verification
- WAR packaging and Jenkins rollback path
- No paid APIs or subscription services required

## Demo account
The application creates a local demo administrator on first startup:
- Email: `admin@feedbackhub.local`
- Password: `Admin@123`

Change/remove this demo credential before any real deployment.

## Run with the internship environment
```bash
mvn clean test
mvn package
sudo cp target/student-feedback-portal.war /opt/tomcat/webapps/
sudo chown tomcat:tomcat /opt/tomcat/webapps/student-feedback-portal.war
curl -i http://localhost:8080/student-feedback-portal/health
```

Open: `http://localhost:8080/student-feedback-portal/`

## MySQL mode
Set these environment variables before Tomcat starts:
```bash
export DB_URL='jdbc:mysql://localhost:3306/feedbackhub?useSSL=false&serverTimezone=UTC'
export DB_USER='feedbackhub'
export DB_PASSWORD='your-password'
```
Then create the database/tables using `db/schema-mysql.sql`.

If these variables are not set, the app uses an H2 file database under the Tomcat working directory. This keeps the project runnable without paid services and without a separate database server.

## DevOps evidence
The project intentionally keeps the required delivery stages visible in `Jenkinsfile`: checkout, Maven compile, unit test, WAR packaging, artifact archiving, Tomcat deployment and curl health check. The failure path restores the previous WAR when one is available.

See `docs/setup-guide.md`, `docs/architecture.md`, `docs/rollback-runbook.md`, and `docs/verification.md`.


## Optional Authentication + Volunteer Mode
Authentication is optional for student feedback submission. Guests can submit feedback without creating an account; signed-in students can additionally use their dashboard/history. A dedicated Volunteer form collects first name, surname, mobile number, email, gender, current study area, volunteer description, availability and skills/interests. Volunteer applications are stored in the database and can be reviewed/marked by administrators.
