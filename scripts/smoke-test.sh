#!/usr/bin/env bash
set -euo pipefail
BASE="${1:-http://localhost:8080/student-feedback-portal}"
echo "[1/4] Health check"
curl -fsS "$BASE/health"; echo
echo "[2/4] Public landing page"
curl -fsSI "$BASE/" | head -n 1
echo "[3/4] Protected admin API without session (must be 403)"
code=$(curl -s -o /dev/null -w '%{http_code}' "$BASE/api/feedback")
test "$code" = "403"
echo "HTTP $code OK"
echo "[4/4] WAR/application endpoint smoke checks passed"
