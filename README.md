# Archer

[![Archer](docs/archer.jpg)](https://pixabay.com/en/archer-bronze-sta-statue-arrow-772979/) 

[![Build Status](https://img.shields.io/travis/whiskeysierra/archer.svg)](https://travis-ci.org/whiskeysierra/archer)
[![Coverage Status](https://img.shields.io/coveralls/whiskeysierra/archer.svg)](https://coveralls.io/r/whiskeysierra/archer)
[![Release](https://img.shields.io/github/release/whiskeysierra/archer.svg)](https://github.com/zalando/whiskeysierra/archer)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.whiskeysierra/archer.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.whiskeysierra/archer)

*Archer* is a pre-defined set of *architectural enforcement* rules. The aspect-oriented enforcement of rules is guided
by annotations and any violation is reported early at compile time. 

## Features

- prevents bad code from compiling
- lightweight API
- retroactively applicable
- independent of package structure

*Archer* defines several different layers:

| Annotation     | Description                           |
|----------------|---------------------------------------|
| `@Gateway`     | External service client               |
| `@Library`     | Shared code between any of the layers |
| `@Logic`       | Business logic                        |
| `@Model`       | Business or domain model              |
| `@Persistence` | Database access layer                 |
| `@Queue`       | Asynchronous message queue client     |
| `@Resource`    | Web Service/API                       |
| `@Scheduler`   | Background Job                        |

[![Access policy diagram](https://docs.google.com/drawings/d/1bGUg6tv4zDea3-akWn33ky5NoAOf4988peUxEZGYKd8/pub?w=600)](https://docs.google.com/drawings/d/1bGUg6tv4zDea3-akWn33ky5NoAOf4988peUxEZGYKd8/pub?w=888&h=772)

## Dependencies

- Java 8
- AspectJ compiler

## Installation

```xml
<dependency>
  <groupId>io.github.whiskeysierra</groupId>
  <artifactId>archer</artifactId>
  <version>${archer.version}</version>
</dependency>
```

TODO example to configure aspectj maven plugin

## Usage

You need to annotate **every class** with one of the layer annotations:

```java
@Model
public class Person {

}

@Repository
public class PersonRepository {

}

@Logic
public class PersonService {

}

@Resource
public class PersonResource {

}
```

Any call between two layers that is not allowed by a rule will result in a compiler error. The same is true if a class
is missing an annotation. 

## Known issues

- No support for package-level annotations, see [here](https://eclipse.org/aspectj/doc/next/adk15notebook/annotations-pointcuts-and-advice.html#package-and-parameter-annotations)

## Getting help

If you have questions, concerns, bug reports, etc, please file an issue in this repository's Issue Tracker.

## Getting involved

To contribute, simply make a pull request and add a brief description (1-2 sentences) of your addition or change. For
more details check the [contribution guidelines](CONTRIBUTING.md).

## Open source licensing info

[Apache License 2.0](LICENSE)

## Credits and references

- http://www.jayway.com/2010/03/28/architectural-enforcement-with-aid-of-aspectj/
