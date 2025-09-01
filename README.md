# quarkus-cdi-specializes

This minimal Quarkus app (created via `quarkus create app`) shall show that currently (Quarkus version 3.26.1)
does not seem to handle `@Specializes` (correctly):
* given
  * an interface `Strategy`
  * a default injectable bean `DefaultStrategy` (annotated with `@Default`) implementing that interface
  * a specialized injectable bean `SpecializedStrategy` (annotated with `@Specializes`) extending `DefaultStrategy`
* when injecting an instance of the interface in another bean `GreetingResource`
* then an AmbiguousResolutionException is thrown

```
java.lang.RuntimeException: io.quarkus.builder.BuildException: Build failure: Build failed due to errors
	[error]: Build step io.quarkus.arc.deployment.ArcProcessor#validate threw an exception: jakarta.enterprise.inject.spi.DeploymentException: jakarta.enterprise.inject.AmbiguousResolutionException: Ambiguous dependencies for type io.specializes.Strategy and qualifiers [@Default]
	- injection target: io.specializes.GreetingResource#strategy
	- declared on CLASS bean [types=[io.specializes.GreetingResource, java.lang.Object], qualifiers=[@Any, @Default], target=io.specializes.GreetingResource]
	- available beans:
		- CLASS bean [types=[io.specializes.DefaultStrategy, io.specializes.SpecializedStrategy, io.specializes.Strategy, java.lang.Object], qualifiers=[@Any, @Default], target=io.specializes.SpecializedStrategy]
		- CLASS bean [types=[io.specializes.DefaultStrategy, io.specializes.Strategy, java.lang.Object], qualifiers=[@Any, @Default], target=io.specializes.DefaultStrategy]
	at io.quarkus.arc.processor.BeanDeployment.processErrors(BeanDeployment.java:1594)
	at io.quarkus.arc.processor.BeanDeployment.init(BeanDeployment.java:341)
	at io.quarkus.arc.processor.BeanProcessor.initialize(BeanProcessor.java:178)
	at io.quarkus.arc.deployment.ArcProcessor.validate(ArcProcessor.java:488)
	at java.base/java.lang.invoke.MethodHandle.invokeWithArguments(MethodHandle.java:732)
	at io.quarkus.deployment.ExtensionLoader$3.execute(ExtensionLoader.java:874)
	at io.quarkus.builder.BuildContext.run(BuildContext.java:255)
	at org.jboss.threads.ContextHandler$1.runWith(ContextHandler.java:18)
	at org.jboss.threads.EnhancedQueueExecutor$Task.doRunWith(EnhancedQueueExecutor.java:2651)
	at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2630)
	at org.jboss.threads.EnhancedQueueExecutor.runThreadBody(EnhancedQueueExecutor.java:1622)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1589)
	at java.base/java.lang.Thread.run(Thread.java:840)
	at org.jboss.threads.JBossThread.run(JBossThread.java:501)
```

In contrast, injection works as expected when using `@Alternative` and `@Priority` instead:
* given
    * an interface `PrioStrategy`
    * a default injectable bean `LowPrioStrategy` (annotated with `@Alternative` and `@Priority(1)`) implementing that interface
    * a specialized injectable bean `HighPrioStrategy` (annotated with `@Alternative` and `@Priority(2)`) extending `LowPrioStrategy`
* when injecting an instance of the interface in another bean `GreetingResource`
* then an instance of `HighPrioStrategy` is injected

How to reproduce: 
1. in the `GreetingResource`
   - activate `Strategy` and deactivate `PrioStrategy` to see the AmbiguousResolutionException:
     ```
       @Inject private Strategy strategy;
       // @Inject private PrioStrategy strategy;
     ```
    - activate `PrioStrategy` and deactivate `Strategy` to see CDI working:
     ```
       // @Inject private Strategy strategy;
       @Inject private PrioStrategy strategy;
     ```
1. run `GreetingResourceTest`


---

Generated README below

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-cdi-specializes-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
