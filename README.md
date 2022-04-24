# Pokéfit

A Pokémon breeding based fitness gamification app for Android, written in Kotlin, powered by PokéAPI and the PokéKotlin wrapper

You can use this as a starting point to create new apps from scratch.

## Structure

* `app` - the main project folder
* `app/build.gradle` - project gradle config file, contains dependencies for the projecy
* `app/src` - main project source directory
* `app/src/main/*/pokefit` - main project directory
* `app/src/main/*/pokefit/activities` - Main project activity screens
* `app/src/main/*/pokefit/model` - Folders containing the model files for PokéAPI calls
* `app/src/main/*/pokefit/ui` - AndroidStudio generated menu fragment controllers
* `app/src/main/*/pokefit/utils` - Utility classes to support the application
* `app/src/main/res` - resources directory

## Building

It is recommended that you run Gradle with the `--daemon` option, as starting
up the tool from scratch often takes at least a few seconds. You can kill the
java process that it leaves running once you are done running your commands.

## Target device

It is recommended to build the application on to a physical device as opposed to a virtual device

## Further reading

* [Build System Overview](https://developer.android.com/sdk/installing/studio-build.html)
* [Gradle Plugin User Guide](http://tools.android.com/tech-docs/new-build-system/user-guide)
* [Gradle Plugin Release Notes](http://tools.android.com/tech-docs/new-build-system)

## Reuse and redistribution

This project is not licensed for any redistribution and the code contained within is not for re-use.
This project is publicly available for users to compile their own version of the game as it is in development.


## Application status

As the project is currently in the very early stages, it cannot be guaranteed that the application will function as expected.
Feel free to make bug reports, although it's likely I am already aware of them.