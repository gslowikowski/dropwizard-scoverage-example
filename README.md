# Introduction

* Project based on [official Dropwizard example project](https://github.com/dropwizard/dropwizard/tree/master/dropwizard-example)
with Scala sources and Scoverage Maven Plugin configuration added.

* Scala class call was added to `com.example.helloworld.core.Template.render` method.

* Tests were removed intentionally.

# Running The Application and reporting code coverage

* To package the example run:

        mvn scoverage:package

This is a trick. It will generate `target/scoverage-dropwizard-example-1.0.0-SNAPSHOT.jar` file,
(with `scoverage-` prefix to avoid overriding original, non-instrumented file).

Check `target/scoverage-data` directory. There is no `scoverage.coverage.xml` file. It means that
Scoverage-instrumented code hasn't been executed yet.

* Setup the h2 database.

        java -jar target/scoverage-dropwizard-example-1.0.0-SNAPSHOT.jar db migrate example.yml

* Run the server.

        java -jar target/scoverage-dropwizard-example-1.0.0-SNAPSHOT.jar server example.yml

* Check the coverage (in separate console).

        mvn generate-sources scoverage:report-only

Open `target/site/scoverage/index.html` file. It should show no (0%) coverage. This is correct because
no tests were executed on instrumented code.

* Hit the Hello World example:

	http://localhost:8080/hello-world

`service.HelloServiceScala.hello` method was executed during request processing. Instrumented code
added information to `target/scoverage-data/scoverage.coverage.xml` file (actually this file was created).

* Check the coverage again.

        mvn generate-sources scoverage:report-only

The coverage should be 100% now, because there is only one line of Scala code and it was executed
during request processing.

# Notes

* The `generate-sources` phase must be executed before `scoverage:report-only` to add `src/main/scala` source root
(to execute `sbt-compiler:addScalaSources` task). Without it Scoverage report will not be generated.
