
# Soostone Pokemon Assingnment

The app listing pokemons and their description




## Features

- Compose
- Coroutines
- MVVM & Modular Architecture Combined
- Offline Data
- Minimized API calls
- Dagger-Hilt


## Architectre

Hilt-Dagger and MVVM Combined. There is a single activity in the application.

- ***Soostone Applicationn***
    -   **App Module**
        - view
        - model
        - viewmodel

    -   **Core Module**
        -   UI Module
        -   Data Module
        -   Database Module

## Offline Data

Since the application is just a pokemon application, it could be a dictionary application too. That is why the **offline mode** is added.

Application uses Room Database to save the remote data to local db. There are 2 entities:
-   **pokemon entity**
-   **image entity**
-       this entity keeps the base64 version of the
        images that are dowloaded from url. That is
        how images are shown to user when there is no
        internet connection, or no new data
## Minimized API Call
-   **The app retrieves the remote data in 2 sceneraios:**
    -   If the local db is empty (Which refers that
        the user downloaded app for the first time
        or storage is removed from settings)
    -   If the data retrieved is more than local data
        which refers that new datas added to remote db.
        -   In this case you may ask, anyways the app
            still make requests and tries to retrieve
            remote data to be able to check if the
            statement is satisfied or not so in all
            case it will make the api call. In this
            case the app tries to retrieve only non-
            existing data in the local db. **What if
            that api wouldnt be free and adjusts
            pricing according to data size retrieved?**

-   **The app uses local db (Offline Mode) in 2 sceneraios:**
    -   When networking is failed no matter what the
        problem is.
    -   Retrieved data size is not more than existing
        data size.

## API Reference

Since the given data for the task is not a dynamic data, app logic is still supporting dynamic data

#### To simulate dynamic data, follow these steps:

1 - Ensure the internet is connected for all operations.

2 - Use the following endpoint for the task:

    "DavidCorrado/8912aa29d7c4a5fbf03993b32916d601/raw/"

3 - Close and destroy the app.

4 - Change the endpoint to:

    "anilclskn1/57a9d138efa689bdc661fd917f88723a/raw/"

5 -  Run the app to observe the retrieval of new data.

6 -  Optionally, write to Logcat:

    "package:mine New Data Found"

## API Reference

#### Things can be improved:

1 - Loading State can be added.

2 - When there is no internet connection, if the app is launched
    for the first time. Show dialog to user
