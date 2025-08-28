# Running Qodana Locally

## Summary

* [Prerequisites](#prerequisites)
* [Execution via Docker](#method-1-execution-via-docker-recommended)
* [Execution via CLI](#method-2-via-cli-without-docker)
* [Tips](#useful-tips)
* [Interpreting the Results](#interpreting-the-results)
* [IDE Integration](#ide-integration)
* [Cleanup](#cleanup)

## Prerequisites

* Docker installed and running
* Configured Java/Spring Boot project
* 4GB+ RAM available (for large projects)

## Method 1: Execution via Docker (Recommended)

### Basic Step-by-Step

1. **Run the scanner** on your project:

```bash
docker run --rm -it \
  -v $(pwd)/qodana.yaml:/data/project/qodana.yaml \
  -p 8080:8080 \
  jetbrains/qodana-jvm
```

2. **Access the report**:

    * Open in your browser: `http://localhost:8080`

### Advanced Options

* **Save report in JSON**:

```bash
docker run --rm -it \
  -v $(pwd)/qodana.yaml:/data/project/qodana.yaml \
  jetbrains/qodana-jvm --save-report
```

* **Ignore specific issues**:

```bash
docker run --rm -it \
  -v $(pwd)/qodana.yaml:/data/project/qodana.yaml \
  jetbrains/qodana-jvm --fail-threshold=high
```

## Method 2: Via CLI (Without Docker)

1. **Install Qodana CLI**:

```bash
curl -fsSL https://raw.githubusercontent.com/JetBrains/qodana-cli/main/install.sh | sh
```

2. **Run the analysis**:

```bash
qodana scan --show-report
```

## Useful Tips

* **For Maven projects**: Add `-e MAVEN_CONFIG=/data/project/.m2` for dependency caching
* **Not enough memory?** Add `-e QODANA_MEM=4096` to increase to 4GB
* **Analyze only modified files**:

```bash
qodana diff --baseline=origin/main
```

* Reports are saved by default in `.qodana/results`.

## Interpreting the Results

* **Critical issues**: Marked in red (security vulnerabilities, serious bugs)
* **Suggestions**: Marked in blue (code improvements)
* **Statistics**: Found in `qodana/results/results.json`

## IDE Integration

1. **Import the results into IntelliJ IDEA**:

    * `File` > `Open` > Select the `qodana/results` directory

2. **View issues directly in the editor** with inline annotations

## Cleanup

Remove temporary containers:

```bash
docker container prune -f
```
