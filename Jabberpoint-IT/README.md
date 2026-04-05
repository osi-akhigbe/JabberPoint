# JabberPoint IT

Desktop slide viewer (Java 17, AWT/Swing). Course project with **Observer**, **Singleton** (`StyleManager`), **Strategy** (`Accessor` + `AccessorSelector`), **JUnit 5** tests, and **DTAP** CI.

## Requirements

- **JDK 17+**
- **Apache Maven 3.9+** (for tests, coverage, and packaged JAR — from the **repository root**, where `pom.xml` lives)

## Build, test, coverage

From the **repo root** (`JabberPoint/`, next to `pom.xml`):

```bash
mvn test
```

- Unit tests live under `src/test/java/`.
- JaCoCo HTML report: `target/site/jacoco/index.html` (after `mvn test`).

Create a runnable JAR (no extra dependencies — JDK only):

```bash
mvn package
```

Artifact: `target/jabberpoint-1.6-SNAPSHOT.jar`.

## Run the application

File → Open uses paths such as `test.xml` **relative to the current working directory**. Recommended:

```bash
cd Jabberpoint-IT
java -jar ../target/jabberpoint-1.6-SNAPSHOT.jar
```

Or with a presentation file:

```bash
cd Jabberpoint-IT
java -jar ../target/jabberpoint-1.6-SNAPSHOT.jar test.xml
```

Without arguments, the demo presentation loads.

## Documentation

| Document | Purpose |
|----------|---------|
| `docs/Jabberpoint-ClassDiagram.puml` | PlantUML class diagram (patterns, packages) |
| `docs/SOLID-PRINCIPLES.md` | Rubric-oriented SOLID mapping |
| `../IMPLEMENTATION-STEP-LOG.docx` | Step-by-step what / how / why |
| `../BRANCH-PROTECTION.md` | GitHub rules aligned with DTAP CI |
| `../WORKFLOW.docx` | Git Flow + DTAP process |

## CI / DTAP

GitHub Actions (`.github/workflows/dtap-ci-cd.yml`) runs on `main`, `develop`, `feature/**`, `release/**`, `hotfix/**`. If `pom.xml` exists at the repo root, the gate runs **`mvn test`**; otherwise it falls back to **`javac`** so older branches still compile.

## Deployability

This is a **desktop JAR**, not a web deploy. “Deploy” here means: **CI green**, **`mvn package`** produces an executable JAR, and run instructions above are valid on any machine with Java 17.
