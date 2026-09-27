# Architecture

## Application flow
Developer → Git → Jenkins → Maven → JUnit → WAR → Tomcat → `/health` → browser

## Runtime layers
1. **Presentation:** responsive HTML/CSS/JavaScript pages.
2. **Servlet API:** authentication, feedback, admin actions and health endpoints.
3. **Security:** session authentication, role checks, HttpOnly session cookie, PBKDF2 password hashes.
4. **Persistence:** JDBC against H2 by default or MySQL when `DB_URL` is configured.
5. **Operations:** Jenkins declarative pipeline, artifact archive, deployment verification and rollback.

## Main pages
- `index.html` — public product landing page
- `login.html` — student/admin authentication
- `register.html` — student registration
- `dashboard.html` — student feedback history and lifecycle
- `feedback.html` — structured submission form
- `success.html` — confirmation
- `admin.html` — operations dashboard and action center

## Database model
`users` stores identity, role and academic profile fields.
`feedback` stores the feedback lifecycle, rating, category, priority and action note.

The application creates the schema automatically for the configured database so the internship demo can be started quickly.
