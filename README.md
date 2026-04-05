# JabberPoint

Student project repository: analysis documents, **DTAP** GitHub Actions workflow, and the **Jabberpoint-IT** application.

## Quick links

- **Application** (build, run, tests): [Jabberpoint-IT/README.md](Jabberpoint-IT/README.md)
- **Implementation log** (steps, patterns): `IMPLEMENTATION-STEP-LOG.docx` (also `IMPLEMENTATION-STEP-LOG..docx` if present)
- **Branch protection & DTAP**: [BRANCH-PROTECTION.md](BRANCH-PROTECTION.md)
- **CI/CD workflow**: [.github/workflows/dtap-ci-cd.yml](.github/workflows/dtap-ci-cd.yml)

## Build (from repo root)

```bash
mvn test    # compile + unit tests + JaCoCo (requires pom.xml on your branch)
mvn package # runnable JAR: target/jabberpoint-1.6-SNAPSHOT.jar
```

Branches without `pom.xml` still compile in CI via `javac` only.
