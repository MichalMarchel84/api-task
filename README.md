# api-task

Application contains single endpoint:

```
/api/{userName}
```

It responds with aggregated information about given GitHub user repositories in the following format:

```
[
    {
        repositoryName: string,
        ownerLogin: string,
        branches: [
            {
                branchName: string,
                lastCommitSha: string
            },
            ...
        ]
    },
    ...
]
```

> **_NOTE:_**  Application contains Quarkus Integration Test, so before running tests, it needs to be packaged

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.
