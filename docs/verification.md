# Verification & Test Matrix

| Check | Method | Expected |
|---|---|---|
| Java syntax | `javac` validation / Maven in internship VM | Compiles |
| Password hashing | JUnit `PasswordUtilTest` | Correct password passes; wrong password fails; salts differ |
| Maven unit tests | `mvn clean test` | PASS |
| WAR packaging | `mvn package` | `target/student-feedback-portal.war` exists |
| Tomcat | `curl -I` application URL | HTTP 200 |
| Health | `curl .../health` | JSON status/database UP |
| Auth | register/login/logout | session created/cleared |
| Authorization | student requests `/api/feedback` admin list | HTTP 403 |
| Feedback | submit valid record | JSON success + database row |
| Student history | `/api/feedback/mine` | only current student's records |
| Admin action | `/api/admin/update` | status/priority persists |
| Rollback | Jenkins failure path | previous WAR restored when available |

### Debug note
The build container used for packaging this preview did not have Maven/network access, so the full Maven dependency resolution and Tomcat runtime were not reproducible in this isolated environment. Java source syntax was nevertheless compiled against local servlet API stubs, JavaScript syntax was checked with Node, and the project structure/configuration was reviewed. The final internship-side verification should run `mvn clean test` and deploy the WAR in the user's existing Tomcat/Jenkins environment.
