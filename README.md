# SongBook

## Main Goals:
- sesbirat pisne ze stranky [velky zpevnik](http://velkyzpevnik.cz) (webscraping) a zobrazit texty pisny a jejich akordy
- mit moznost spravovat ostatni zarizeni v siti, ktere pouzivaji v te chvili aplikaci, aby uzivatele byli synchronizovani

## Nice To Have:
- zobrazit i akordy piana
- transponovat noty pisni
- local cache (zobrazeni obrazku daneho akordu) ze stranky velkyzpevnik

## Domain Entities:
- ### Song: 
    - title
    - url (ref. velkyzpevnik.cz)
    - baseTranspose
    - indexesOfVerses (e.g.: 1, R1, 2, R1, 3)
- ### SongPart\<T>:
    - number
    - type (chorus, verse)
    - songId
    - chords (array\<string>)?
    - lyrics (blob)
    - __SongPart subtypes__ (based on type enum):
        - SongPart\<Chorus>
        - SongPart\<Verse>
- ### Chords:
    - type
    - chords
    - songPartId
    - __Chords subtypes__ (based on type enum):
        - Chords\<Piano>
        - Chords\<Guitar>

## Tech Stack:
- [Kotlin]() - 1.7.10
- [RxAndroid]() - 3.0.0
- [RxKotlin]() (just kotlin extension methods)
- [JetPack Compose](https://developer.android.com/jetpack/compose) (Presentation layer)
- [Moshi](https://github.com/square/moshi) (json parser)
- [Android KTX](https://developer.android.com/kotlin/ktx?ref=hackernoon.com)
- [Koin](https://insert-koin.io/?ref=hackernoon.com) (DI)
- [kotest](https://kotest.io/) (test framework)
- [ktor](https://ktor.io/docs/getting-started-ktor-client.html#make-request) (request client)

- ### [Nearby Connections API](https://developers.google.com/nearby/connections/overview)

## Useful links:
- [Kotlin Native Clean Architecture](https://proandroiddev.com/clean-architecture-example-with-kotlin-multiplatform-c361bb283fd0?gi=da2a47bbfaa5)
- [Clean Architecture In Android](https://www.geeksforgeeks.org/what-is-clean-architecture-in-android/#:~:text=Clean%20architecture%20is%20a%20method,goal%20should%20be%20rendered%20obsolete.)
- [JetPack Compose basics](https://www.youtube.com/watch?v=qvDo0SKR8-k)
- [Jetpack Compose State Management](https://www.raywenderlich.com/30172122-managing-state-in-jetpack-compose)