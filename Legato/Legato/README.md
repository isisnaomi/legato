# Legato Swift

Legato is an opinionated mobile navigation flow handler.

## Requirements
* Xcode 9 or above
* iOS 11 or above

## Project structure

* [Common](./Common)
  * [Navigation](./Navigation)
* [Presentation](./Presentation)
  * [Dispatch](./Presentation/Dispatch)
    * [Handlers](./Presentation/Dispatch/Handlers)
  * [Error](./Presentation/Error)
  * [Login](./Presentation/Login)
  * [Main](./Presentation/Main)
* [Utils](./Utils)


## Usage

### 1. First clone this repo

``` bash
git clone git@github.com:yellowme/legato.git
```
### 2. Set Data Providers

`Utils/DataFaker.swift` has fake data providers in order to provide examples of
implementation.

Example:

```swift
class SessionFaker: DataProvider {
    func hasSessionStarted() -> Bool {
        return true
    }
}
```


### 3. Add Navigation Helpers
Right click above your main project folder and select Add Files to "ProjectName".. then select the folder `Common/Navigation` from the `Legato` library. Inside this folder do the following:

  3.1 Add your own Storyboard identifiers and controllers by name to the file `Navigation+Captain.swift` as enums.

  Example:

  ```swift
  public enum Storyboard: String {
      case login = "Login"
      case dispatch = "Dispatch"
  }

  public enum Controller: String {
      case login = "LoginViewController"
      case dispatch = "DispatchViewController"
  }
  ```

  3.2 Add your own transition combinations to the file `Navigation+Transition.swift` as enums.

  Example:

  ```swift
    public enum Flows: TransitionAccessor {
        case main
        case login

        func getTransition() -> Transition? {
            switch self {
            case .main:
                return (.main, .main)
            case .login:
                return (.login, .login)
            }
        }
    }
  ```

### 4. Add Flow Handlers for Dispatch Flow
Right click above your main project folder and select Add Files to "ProjectName".. and select the folder `Presentations/Dispatch` from the `Legato` library.


  4.1 Add all your "sendToScreen" methods mapping each case of the "Screen" enum to the file `DispatchContract.swift` and update `DispatchViewController` to implement contract.

```swift
    func sendToMain();
    func sendToLogin();
```

  4.2 Implement your custom flows inside file `DispatchPresenter.swift`.

```swift
      required init(view: DispatchView) {
          self.view = view
          flowHandler = SessionFlowHandler(
              SessionFaker(), concreteFlow: .login
          )

          _ = flowHandler
              .link(with: ValidSessionFlowHandler(
                  ValidSessionFaker(), concreteFlow: .login
              ))
              .link(with: FetchUserFlowHandler(
                  UserFaker(), concreteFlow: .error
              ))
      }
```
