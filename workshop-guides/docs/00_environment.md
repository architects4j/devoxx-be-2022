# Preparing your environment

## Prerequisites

* Maven CLI;
* Git CLI;
* Java 11;
* An IDE of your preference;
* Command line (terminal). _If using Windows, PowerShell is recommended;_
* Helidon CLI _(optional)_;
* REST client tools like cURL or postman _(Optional)_;

!!! TIP

    The [Helidon CLI](https://helidon.io/docs/v2/#/about/05_cli) can be used alternatively if you want to rely on features only available through this tool. 

To check if you have the above tools installed, open your terminal and running the following the commands. You should be able to see the version of each of the tools:

```shell
java -version
mvn -version
git --version
```

### Installing helidon CLI _(optional)_

To install Helidon, you can run the commands below according to your O.S:

MacOS:
```bash
curl -O https://helidon.io/cli/2.3.3/darwin/helidon
chmod +x ./helidon
sudo mv ./helidon /usr/local/bin/
```

Linux:
```bash
curl -O https://helidon.io/cli/2.3.3/linux/helidon
chmod +x ./helidon
sudo mv ./helidon /usr/local/bin/
```

Windows:
```bat
PowerShell -Command Invoke-WebRequest -Uri "https://helidon.io/cli/2.3.3/windows/helidon.exe" -OutFile "C:\Windows\system32\helidon.exe"
```

You can verify the installation by running in the terminal:

```shell
helidon version
```

Helidon CLI is available for Helidon 2+.

## Prepare your working directory

To get started with the labs, first you need to clone the foundation project and have it available in your local development environment.

!!! INFO

	The guided exercises are built on top of quickstart projects, a foundation set of preconfigured projects.


To get the foundation projects and build them, execute: 

```shell
git clone https://github.com/architects4j/devoxx-be-2022.git
cd helidon-microstream-training-labs-foundation
mvn clean package
```

The build and packaging should complete successfully.
