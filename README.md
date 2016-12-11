# AndroidModularSample

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AndroidModularSample-brightgreen.svg?style=flat)](http://android-arsenal.com/details/3/4810)

A sample Android application to demonstrate how to build screens as fully independent modules.

It relies on a Finite State Machine ([EasyFlow](https://github.com/Beh01der/EasyFlow)), to handle screen as states and trigger/consume events.
Events allows the application to handle navigation between screens.
The global context used by the FSM is used to pass arguments as a Bundle.
It also shows how to use dependency injection (via [Dagger 2](https://google.github.io/dagger/) for example) in modules.

This way:

- modules are fully independent
- modules declare the dependencies they need
- modules declare the states they correspond to
- modules declare the events that can be triggered
- the hosting application set up the states it uses (i.e., "screens")
- the hosting application set up how to navigation between states (with events)
- the hosting application set up the dependency injection mechanism

## Conclusion

- A screen module is fully reusable in another application
- It allows a clear structure in the Android project hierarchy
- A new module is very easy to add
- The navigation is handled in a very fluent way

```java

from(FirstController.States.WAITING_LOGIN)
    .transit(
        on(FirstController.Events.loginProvided)
            .to(SecondController.States.SHOWING_WELCOME)
            .transit(
                on(Events.backClicked)
                    .to(FirstController.States.WAITING_LOGIN)
            )
    )

```

## Perspectives

- EasyFlow allows to manage internal states of a screen
    - for example, concerning a network call
        - states can be: waiting for response, error, success
        - events can be: on start network request, on failure, on success, on retry clicked
- Globally, this approach can be used in an Android application to set up screens and navigation (i.e., no need to define screens as modules)
