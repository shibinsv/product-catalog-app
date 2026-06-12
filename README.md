# Product Catalog App

This is an Android application that I built using Jetpack Compose and MVVM architecture. The Product Catalog App allows users to browse products, filter by category and view product information.

---

# Setup Instructions

To set up the Product Catalog App follow these steps:

1. Clone the repository.

2. Open the project in Android Studio.

3. Sync Gradle dependencies.

4. Run the application.

5. Login using:

* Any valid email address

* Password, with 8 characters

---

# Features

## Authentication

The Product Catalog App has the following authentication features:

* Email and Password login for the Product Catalog App

* Email format validation to ensure email addresses

* Password validation that requires a minimum of 8 characters for the Product Catalog App

* Session persistence using DataStore to keep users logged in

* login for previously authenticated users of the Product Catalog App

* Logout functionality to securely log out of the Product Catalog App

## Home Screen

The Home Screen of the Product Catalog App does the following:

* Fetches products from a remote API for the Product Catalog App

* Allows category filtering to find specific products in the Product Catalog App

* Displays the product count to show how many products are available in the Product Catalog App

* Displays product cards that show the following information for the Product Catalog App:

* Product image to visually represent the product in the Product Catalog App

* Product title to identify the product in the Product Catalog App

* Price to show the cost of the product in the Product Catalog App

* Discount percentage to show any savings on the product in the Product Catalog App

* Rating to show how other users have rated the product in the Product Catalog App

* Stock status to show if the product is available in the Product Catalog App

* Handles loading states to prevent the Product Catalog App from freezing

* Handles error states to prevent the Product Catalog App from crashing

* Allows users to retry failed API requests to ensure the Product Catalog App stays

## Product Detail Screen

The Product Detail Screen of the Product Catalog App shows the following information:

* A product image carousel to display images of the product in the Product Catalog App

* Image page indicators to navigate through the product images in the Product Catalog App

* Product title to identify the product in the Product Catalog App

* Brand to show the manufacturer of the product in the Product Catalog App

* Price to show the cost of the product in the Product Catalog App

* Discount percentage to show any savings on the product in the Product Catalog App

* Rating to show how other users have rated the product in the Product Catalog App

* Category to show what type of product it is in the Product Catalog App

* Stock information to show if the product is available in the Product Catalog App

* Product description to provide details about the product in the Product Catalog App

* Displays the logged-in users email address in the Product Catalog App

---

# Tech Stack

## Architecture

The Product Catalog App uses the following architecture:

* MVVM (Model-View-ViewModel) to separate the data and business logic

* Repository Pattern to manage data storage and retrieval

* StateFlow for UI state management to ensure an user experience in the Product Catalog App

## Libraries

The Product Catalog App uses the following libraries:

* Jetpack Compose to build the user interface

* Navigation Compose to handle navigation between screens

* Hilt for dependency injection to simplify the code

* Retrofit to make API requests to the Product Catalog App

* Kotlin Coroutines to handle tasks

* StateFlow to manage UI state in the Product Catalog App

* DataStore to store user data locally

* Coil to load images in the Product Catalog App

---

# Project Structure

The project structure of the Product Catalog App is as follows:

```text

data

├── mapper

├── remote

└── repository

datastore

di

domain

└── model

presentation

├── composables

├── detail

├── home

├── login

├── navigation

└── splash

```

---

# API Endpoints

The Product Catalog App uses the following API endpoints:

## Product List

```text

https://api.freeapi.app/api/v1/public/randomproducts?page=1&limit=100

```

## Product Detail

```text

https://api.freeapi.app/api/v1/public/randomproducts/{id}

```

---

# Architecture Overview

## Presentation Layer

The Presentation Layer of the Product Catalog App is responsible for rendering the UI and handling user interactions. It consists of the following components:

* Compose Screens to display the UI

* ViewModels to manage the data and business logic

* UI State classes to manage the state of the UI in the Product Catalog App

## Data Layer

The Data Layer of the Product Catalog App is responsible for networking and data mapping. It consists of the following components:

* Retrofit APIs to make API requests

* Repository implementation to manage data storage and retrieval

* DTO to Domain mappers to convert data between formats

## Persistence Layer

The Persistence Layer of the Product Catalog App is responsible for session management. It consists of the following components:

* DataStore to store user data locally

* User login state to manage the users login status

* User email storage to store the users email address

---

# State Handling

The Product Catalog App properly handles the following states:

## Loading State

Displays loading indicators while data is being fetched from the Product Catalog App.

## Success State

Displays product data after API responses from the Product Catalog App.

## Error State

Displays user-friendly error messages and allows retrying failed requests in the Product Catalog App.

---

# Bonus Features Implemented

The Product Catalog App has the following bonus features:

## Retry Mechanism

Users can retry failed product requests directly from the Home Screen of the Product Catalog App.

## UX Improvements

* Image carousel indicators to navigate through product images in the Product Catalog App

* Automatic list scroll-to-top when changing categories in the Product Catalog App

* category filtering to find specific products in the Product Catalog App

---

# Known Limitation

The Product Catalog App has a known limitation. The product image URLs returned by the API were not loading during testing. The application uses Coil for image loading and image rendering was verified using image URLs, which loaded successfully.

This indicates that the issue is related to the image resources provided by the API than the image loading implementation in the Product Catalog App.

# Screenshots

## Login Screen

<img width="250" height="500" alt="image" src="https://github.com/user-attachments/assets/44f9a379-31a8-4394-a244-0ba980695d2c" />

<img width="250" height="500" alt="image" src="https://github.com/user-attachments/assets/86f5bbb3-4fb1-424b-955d-5989d9886ba2" />

## Home Screen

<img width="250" height="500" alt="image" src="https://github.com/user-attachments/assets/91c27a40-3994-4f63-8951-2745da78bac1" />

<img width="250" height="500" alt="image" src="https://github.com/user-attachments/assets/ec22cd2a-5029-42fc-a041-f4544d2648a5" />

## Product Detail Screen

<img width="250" height="500" alt="image" src="https://github.com/user-attachments/assets/55344f7d-c748-4a9e-a38a-16f713ff06ba" />

<img width="250" height="500" alt="image" src="https://github.com/user-attachments/assets/693ef92b-4849-42a3-9c7b-9b6da7b94985" />

---

# Future Improvements

The Product Catalog App can be improved in the following ways:

* Room database caching to improve performance

* Offline support to allow users to access the app without an internet connection

* Pull-to-refresh functionality to refresh the data

* Additional UI animations to enhance the user experience

* Unit testing to ensure the app is stable and functional

* UI testing to ensure the app is user-

---
