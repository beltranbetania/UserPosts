# User Posts
## About
On this repository you will find a Project with Clean Architecture, MVVM, Retrofit, Coroutines, Dagger Hilt, which:
* Show a list of all users and their details (user & post) from JSONPlaceholder. 
* filter users by name
* Implement unit testing for use a case and view model
* Implement unit testing for searching user

### Requirements
* Android Studio (latest version)
* Java 8
* Gradle v4.1.3

## Arquitecture
This project implements MVVM with Clean Architecture using Retrofit and Coroutines, Dagger Hilt Dependency Injection, Data Persistence with ROOM.
Implementing MVVM with Room and LiveData provides several advantages such as:
* Allows separation of user interface logic from business logic.
* Helps keep UI code simple
* The UI will always display the most up-to-date data. LiveData is still a vitaminized Observable.
* No  bugs or defensive code to avoid idle activities/fragments. 
* Avoid memory leaks. Observers are associated with a lifecycle object.


