# BankApp

**BankApp** to prosta aplikacja bankowa stworzona w języku Java, która pozwala użytkownikom na zarządzanie kontami bankowymi. Umożliwia dodawanie, usuwanie kont, wpłacanie, wypłacanie oraz przelewanie pieniędzy pomiędzy kontami. Aplikacja wykorzystuje bazę danych MySQL do przechowywania informacji o kontach oraz transakcjach.

## Funkcjonalności

Aplikacja oferuje następujące funkcjonalności:

- **Dodawanie konta bankowego**: Możliwość dodawania nowych użytkowników z imieniem, nazwiskiem i początkowym saldem.
- **Usuwanie konta bankowego**: Umożliwia usunięcie konta na podstawie jego ID.
- **Pokazywanie wszystkich kont**: Wyświetla listę wszystkich kont w bazie danych.
- **Wpłacanie pieniędzy**: Umożliwia wpłatę pieniędzy na konto użytkownika.
- **Wypłacanie pieniędzy**: Umożliwia wypłatę pieniędzy z konta użytkownika.
- **Przelewanie pieniędzy**: Pozwala na przelanie środków pomiędzy kontami.

## Technologie

- **Java**: Język programowania używany do stworzenia aplikacji.
- **MySQL**: Baza danych używana do przechowywania informacji o kontach i transakcjach.
- **JDBC**: Używane do komunikacji z bazą danych MySQL.
  
## Wymagania

- Java JDK 8 lub nowszy
- MySQL Server
- Odpowiednia biblioteka JDBC dla MySQL

## Instalacja

1. Sklonuj to repozytorium na swój komputer:

   ```bash
   git clone https://github.com/RadekSzczurek/Basic-Java-Banking-App

2. Skonfiguruj bazę danych MySQL:

- Importuj bazę danych dostępną w repo
  
3. Skonfiguruj połączenie z bazą danych w klasie DatabaseConnection
   ```java
    private static final String url = "jdbc:mysql://localhost:3306/bankJava";
    private static final String user = "root";
    private static final String password = "";
-Ustaw te wartości na swoje dane
5. Uruchom aplikację, wlączając klasę BankApp.

## Jak używać
Po uruchomieniu aplikacji pojawi się menu, w którym możesz wybierać dostępne opcje, wpisując odpowiedni numer.
Postępuj zgodnie z instrukcjami wyświetlanymi na ekranie.

## Autor
Radek
