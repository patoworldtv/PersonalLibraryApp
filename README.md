# PersonalLibraryApp
* Welcome to PersonalLibraryApp! Whether you're an avid reader looking to organize your physical or digital shelf, or a developer exploring clean Android architecture, this app is designed to make book and author management a breeze. Built natively in Kotlin, it offers a smooth, reliable, and completely offline first experience.
## What It Does
* **Book Cataloging:** Effortlessly add, browse, and manage books in your personal library.
* **Author Tracking:** Keep dedicated records of the minds behind your favorite reads.
* **Offline First Storage:** Powered by the Android Room Database so your data stays safe right on your device.   
* **Reactive Design:** Uses a clean ViewModel and custom adapter setup for fluid, responsive UI updates.   
* **Personalized Preferences:** Features a dedicated settings manager to handle app configurations.  
# Under the Hood (Tech Stack)
* **Language:** Kotlin   
* **Architecture:** MVVM (Model-View-ViewModel) pattern ensuring a clean separation of concerns.   
* **Local Database:** Android Room (LibraryDatabase, BookDao, AuthorDao, and LibraryRepository).   
* **UI And Views:** MainActivity, custom BookAdapter for RecyclerViews, and tailored XML layout files. 
# Project Structure
*Here is a quick look at how the project is organized:*
* src/
* ├── main/
* ├── java/com/example/personallibraryapp/
* │   │   ├── data/
* │   │   │   ├── Author.kt          # Author entity definition
* │   │   │   ├── AuthorDao.kt       # Data Access Object for authors
* │   │   │   ├── Book.kt            # Book entity definition
* │   │   │   ├── BookDao.kt         # Data Access Object for books
* │   │   │   ├── LibraryDatabase.kt # Room database instance
* │   │   │   └── LibraryRepository.kt # Single source of truth repository
* │   │   ├── BookAdapter.kt         # RecyclerView adapter for displaying books
* │   │   ├── LibraryViewModel.kt    # ViewModel managing UI-related data
* │   │   ├── MainActivity.kt        # Entry point activity
* │   │   └── SettingsManager.kt     # Handles user preferences/settings
* │   ├── res/
* │   │   ├── layout/                # UI layouts (activity_main.xml, item_book.xml)
* │   │   ├── values/                # App themes, colors, and string resources
* │   │   └── mipmap/                # Application launcher icons
* │   └── AndroidManifest.xml        # Core app manifest configuration
# Getting Started
*Want to run this project on your machine? Follow these simple steps:*
* **Clone or Download** this repository to your local computer.
* Open **Android Studio** ( Iguana or a newer version is recommended).
* Select **Open an Existing Project** and choose the project root folder.
* Allow Gradle a moment to sync and fetch dependencies.
* Fire up an emulator or connect your physical Android device, then hit Run
