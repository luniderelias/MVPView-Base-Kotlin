# MVPView-Base-Kotlin
This project contains an example of MVPView architecture for kotlin-based android apps.
I'm trying to set the best architecture in order to unit test UI.
MVPView is a mix of MVP and MVVM, some might call this architecture MVPVM (Model View Presenter ViewModel), which consists in an additional ViewModel layer on MVP architecture.

## Observations
This is the MVPView implementation, I've also developed [MVVM](https://github.com/luniderelias/MVVM-Base-Kotlin) and MVPLiveData examples.
This project is a study to understand the better approach in order to unit test UI of Android Apps without using any instrumented test.
The key points of MVPView is that this architecture fits with LiveData as it uses ViewModel, thus contributing to reactive programming and enabling UI unit tests. But it also has a Presenter, so we can input business logic at presenter only and more complex UI logic goes into ViewModel.
Although its a good architecture, I think its more complex than MVVM and its easy for beginner developers to make a mistake by including business logic on ViewModel or updating UI directly from Presenter. 
Some might say MVPView architecture uses ViewModel for complex UI only and Presenter-View communication for simple UI. But I think its even worse, cause we are setting two different ways to access the View and this might lead to complex UI code going directly to View or simple UI code passing through ViewModel. 
I prefer to center all View communication in a single way in order to avoid mistakes. 

## Libraries
Mockito,
Koin,
Lifecycle,
MockitoKotlin2

## Architecture
MVPView with Repositories and LiveData


