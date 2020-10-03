# cucumber-micronaut

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![Pull Request][pr-shield]][pr-url]
[![Apache License][license-shield]][license-url]



<!-- PROJECT LOGO -->
<br />
<p align="center">
  <h3 align="center">Cucumber Micronaut</h3>

  <p align="center">
    
    <br />
    <a href="https://github.com/david-romero/cucumber-micronaut"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/david-romero/cucumber-micronaut">View Demo</a>
    ·
    <a href="https://github.com/david-romero/cucumber-micronaut/issues">Report Bug</a>
    ·
    <a href="https://github.com/david-romero/cucumber-micronaut/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Features](#features)
  * [Build](#build)
  * [Installation](#installation)
* [Usage](#usage)
* [Roadmap](#roadmap)
* [Contributing](#contributing)
* [License](#license)
* [Contact](#contact)



<!-- ABOUT THE PROJECT -->
## About The Project

A lightweight library for using dependency injection in cucumber steps for Micronaut based project.

### Built With

* [Java 11](https://adoptopenjdk.net/)
* [Micronaut](https://micronaut.io/index.html)
* [Maven](https://maven.apache.org/)



<!-- GETTING STARTED -->
## Getting Started

### Features

- Cucumber steps dependency injection using the Micronaut dependency injection mechanism. **Only constructor dependency injection is supported**

- Bean discovering by type
- Bean discovering by name. See micronaut @Named annotation usage.
- Bean discovering by parameterized type


### Build

1. Clone the repo

```sh
git@github.com:david-romero/cucumber-micronaut.git
```

2. Build the library

```sh
mvn clean package
```

### Installation

1. Clone the repo

```sh
git@github.com:david-romero/shared-payments.git
```

2. Install the library

```sh
mvn clean install
```


<!-- USAGE EXAMPLES -->
## Usage

### Dependency

Add the maven dependency

```xml
  <dependency>
    <groupId>com.davromalc</groupId>
    <artifactId>cucumber-micronaut</artifactId>
    <version>0.1.0</version>
  </dependency>
```

### Configuration

The Micronaut Object Factory needs to be configured as cucumber object factory.

```java
import com.davromalc.cucumber.micronaut.MicronautObjectFactory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class) //Cucumber does not support JUnit 5 yet
@CucumberOptions(
    plugin = {"pretty", "html:target/features"},
    glue = {"com.davromalc.cucumber.micronaut.examples.acceptance"},
    objectFactory = MicronautObjectFactory.class,
    features = "classpath:features", snippets = SnippetType.CAMELCASE)
public class CucumberRunnerITCase {

}
```

### Steps usage


1. Beans definitions

```java
import javax.inject.Singleton;

@Singleton
public class World {
  
}
```

```java
@Singleton
final class AddFriend implements UseCase<AddFriendParams, User> {

  private final FriendRepository friendRepository;

  public AddFriend(FriendRepository friendRepository) {
    this.friendRepository = friendRepository;
  }

  @Override
  public Either<Validation, User> execute(AddFriendParams params) {
     //Implementation...
  }
}
```

2. Step implementation

```java
package com.davromalc.cucumber.micronaut.examples.acceptance.steps;

public class AddFriendStep {

  private final UseCase<AddFriendParams, User> addFriend;

  private final FriendRepository friendRepository;

  private final World world;

  public AddFriendStep(@Named("addFriend") UseCase<AddFriendParams, User> addFriend, FriendRepository friendRepository,
      World world) {
    this.addFriend = addFriend;
    this.friendRepository = friendRepository;
    this.world = world;
  }

  @Given("a new friend with name {string}")
  public void aNewFriendWithName(String friendName) {
    world.setFriendParams(new AddFriendParams(friendName));
  }


  @When("the friend is added")
  public void theFriendIsAdded() {
    addFriend.execute(world.getFriendParams());
  }

  @Then("the new friend {string} has been saved")
  public void theNewFriendHasBeenSaved(String newFriendName) {
    final Optional<String> newFriendNameFromDatabase = friendRepository.findByName(newFriendName)
        .map(User::getName)
        .filter(newFriendName::equals)
        .findAny();
    assertThat(newFriendNameFromDatabase)
        .matches(Optional::isPresent)
        .get()
        .asString()
        .isEqualTo(newFriendName);
  }
}
```

_For more examples, please refer to the [Examples Repository](https://github.com/david-romero/cucumber-micronaut-examples)_



<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/david-romero/cucumber-micronaut/issues) for a list of proposed features (and known issues).

### Proposed features

- Releasing with GitHun Actions

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the Apache License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

David Romero - [@davromalc](https://twitter.com/davromalc)

Project Link: [https://github.com/david-romero/cucumber-micronaut](https://github.com/david-romero/cucumber-micronaut)





<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=flat-square
[contributors-url]: https://github.com/david-romero/cucumber-micronaut/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/david-romero/cucumber-micronaut?style=flat-square
[forks-url]: hhttps://github.com/david-romero/cucumber-micronaut/network/members
[stars-shield]: https://img.shields.io/github/stars/david-romero/cucumber-micronaut?style=flat-square
[stars-url]: https://github.com/david-romero/cucumber-micronaut/stargazers
[issues-shield]: https://img.shields.io/github/issues/david-romero/cucumber-micronaut?style=flat-square
[issues-url]: https://github.com/david-romero/cucumber-micronaut/issues
[pr-shield]: https://img.shields.io/github/issues-pr/david-romero/cucumber-micronaut?style=flat-square
[pr-url]: https://github.com/david-romero/cucumber-micronaut/pulls
[license-shield]: https://img.shields.io/github/license/david-romero/cucumber-micronaut?style=flat-square
[license-url]: https://github.com/david-romero/cucumber-micronaut/blob/master/LICENSE
