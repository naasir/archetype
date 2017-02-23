```
                 _          _
   __ _ _ __ ___| |__   ___| |_ _   _ _ __   ___
  / _` | '__/ __| '_ \ / _ \ __| | | | '_ \ / _ \
 | (_| | | | (__| | | |  __/ |_| |_| | |_) |  __/
  \__,_|_|  \___|_| |_|\___|\__|\__, | .__/ \___|
                                |___/|_|

```
_"... when a man has discovered the instrument which is naturally adapted to each work, he must express this natural form"_ - Plato

---
## Summary

This repo is an exercise and discussion in modeling some common subdomains found in most software platforms. These subdomains include:

1. Contact Info
  * EmailAddress
  * Uri
  * LatLong

2. Users & Groups
3. Notifications
The goal here is to model these subdomains using type-driven, event-driven and domain-driven design principles.

## Smart Constructor

Most of the types in this project implement "Smart" constructors, which ensure that _only_ valid instances of a specific type can be instantiated. By ensuring that all instances of a specific type are valid and immutable, consumers of this type are unburdened from having to implement a bunch of boilerplate error handling logic.

## Quickstart

### Requirements

The following software components are required to be pre-installed in order to run the samples and tests. Here is one method of installing all the necessary dependencies on a Mac via [Homebrew](http://brew.sh/):

| Dependency                                   | Version    | Install                                   |
|----------------------------------------------|------------|-------------------------------------------|
|[java](http://www.java.com/en/)               |`v1.7+`     |`brew cask install caskroom/versions/java7`|
|[scala](http://www.scala-lang.org/)           |`v2.11.8+`  |`brew install scala`                       |
|[sbt](http://www.scala-sbt.org/)              |`v0.13.12+` |`brew install sbt`                         |

### Running the samples

Issue the following commands from a shell to run the samples:

    $ cd /path/to/archetype
    $ sbt run

### Running the tests

Issue the following commands from a shell to run the test suite:

    $ cd /path/to/archetype
    $ sbt test

## Technology Stack

* [Scala](http://www.scala-lang.org/): The primary development language of this project.
* [SBT](http://www.scala-sbt.org/): Build tool for Scala and Java projects similar to Maven and Ant.
* [ScalaTest](http://www.scalatest.org/): Unit-testing library for Scala.

## Inspiration

* [F# For Fun & Profit: Designing With Types](https://fsharpforfunandprofit.com/posts/designing-with-types-intro/)
