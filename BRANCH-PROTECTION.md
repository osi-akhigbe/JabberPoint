# Branch protection & DTAP governance (GitHub)

This document describes how to configure **GitHub branch protection rules** so they match the **DTAP** workflow (Development → Test → Acceptance → Production) and the **DTAP CI/CD Pipeline** workflow in `.github/workflows/dtap-ci-cd.yml`.

> **Where to set this:** Repository → **Settings** → **Branches** → **Add branch protection rule** (or **Add rule**).

After the first successful workflow run on each branch type, the **status check names** below will appear under “Require status checks to pass before merging”. Select the ones that match your jobs.

---

## 1. Protect `main` (Production — **P**)

| Setting | Recommendation |
|--------|------------------|
| **Branch name pattern** | `main` |
| **Require a pull request before merging** | On |
| **Required approvals** | At least **1** (or per course requirement) |
| **Dismiss stale pull request approvals** | On (recommended) |
| **Require status checks to pass** | On |
| **Status checks to require** | `Production (P) - Main branch protection gate` |
| **Require branches to be up to date before merging** | On (recommended) |
| **Include administrators** | On (recommended so rules apply to everyone) |

**Meaning:** Nothing merges to **main** unless the production compile gate passes and (typically) a release PR was reviewed.

---

## 2. Protect `develop` (Development integration — **D**)

| Setting | Recommendation |
|--------|------------------|
| **Branch name pattern** | `develop` |
| **Require a pull request before merging** | On |
| **Require status checks to pass** | On |
| **Status checks to require** | `Development (D) - Build checks` |
| **Require branches to be up to date before merging** | On (recommended) |

**Meaning:** Feature work merges into **develop** only after the development build job succeeds.

---

## 3. Protect `release/*` (Test + Acceptance — **T / A**)

| Setting | Recommendation |
|--------|------------------|
| **Branch name pattern** | `release/*` |
| **Require a pull request before merging** | On |
| **Required approvals** | At least **1** (acceptance / review gate) |
| **Require status checks to pass** | On |
| **Status checks to require** | `Test/Acceptance (T/A) - Release validation` |
| **Require branches to be up to date before merging** | On (recommended) |

**Meaning:** Release branches get stricter checks and an artifact for review before merging to **main**.

---

## 4. Optional: `feature/*` and `hotfix/*`

GitHub does **not** always support wildcards the same way for every setting. Common approaches:

- **Option A:** No protection on `feature/*` — developers use PRs into `develop` only; **D** gate runs on pushes to `feature/*` via the workflow.
- **Option B:** If your org supports it, add rules for `feature/*` and `hotfix/*` mirroring **develop** (PR + **D** gate).

Your workflow already triggers on:

- `develop`, `feature/**`, `release/**`, `hotfix/**`, and `main`.

---

## 5. How this maps to DTAP

| Stage | Branch(es) | Typical flow | CI job |
|-------|------------|--------------|--------|
| **D** Development | `develop`, `feature/*`, `hotfix/*` | Work in feature branches → PR to `develop` | `Development (D) - Build checks` |
| **T / A** Test & Acceptance | `release/*` | Branch from `develop` → test → review → PR to `main` | `Test/Acceptance (T/A) - Release validation` |
| **P** Production | `main` | Released / hand-in code | `Production (P) - Main branch protection gate` |

---

## 6. After you change workflow job names

If you rename jobs in `.github/workflows/dtap-ci-cd.yml`, update the **status check names** in GitHub branch protection to match the new job `name:` values.

---

## 7. Reference

- Workflow file: `.github/workflows/dtap-ci-cd.yml`
- Process description: `WORKFLOW.docx` (Git Flow + DTAP)
