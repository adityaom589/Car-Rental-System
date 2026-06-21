# 🚗 Car Rental System

A console-based **Car Rental Management System** built in **Java**, applying core **Object-Oriented Programming** principles with a **MySQL** database for persistent storage via **JDBC**.

This project simulates a real-world car rental workflow — viewing available cars, renting them out, returning them, and adding new cars to the fleet — with all data persisted in a relational database instead of temporary in-memory storage.

---

## ✨ Features

- 📋 **View Available Cars** — Real-time list pulled directly from the database
- 🚘 **Rent a Car** — Select a car, enter rental duration, auto-calculates total price
- 🔁 **Return a Car** — Updates availability instantly
- ➕ **Add a New Car** — Dynamically expand the fleet through the console menu
- 💾 **Persistent Storage** — Data survives program restarts (unlike a basic ArrayList-based system)
- 🔒 **Secure Credentials** — Database password is read from an environment variable, never hardcoded

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java (JDK 25) |
| Database | MySQL |
| Connectivity | JDBC (MySQL Connector/J) |
| Build & Run | Shell script (`run.sh`) — no Maven/Gradle required |

---

## 🏗️ Project Architecture

```
Main.java  →  CarRentalSystem.java  →  Car.java / Customer.java / Rental.java  →  DatabaseConnection.java  →  MySQL
(console UI)    (business logic)         (entity classes)                          (JDBC connection handler)
```

### Folder Structure

```
Car-Rental-System/
├── src/
│   ├── Main.java                 # Entry point, console menu
│   ├── Car.java                  # Car entity + DB operations
│   ├── Customer.java             # Customer entity + DB operations
│   ├── Rental.java                # Rental transaction entity
│   ├── CarRentalSystem.java       # Business/service layer
│   └── DatabaseConnection.java    # JDBC connection handler
├── lib/
│   └── mysql-connector-j-x.x.x.jar
├── run.sh                         # Compile + run automation script
├── .gitignore
└── README.md
```

---

## 🧠 OOP Concepts Applied

- **Encapsulation** — All entity fields are private, accessed only via getters/setters
- **Abstraction** — `CarRentalSystem` exposes simple methods (`rentCar()`, `returnCar()`) while hiding SQL complexity
- **Single Responsibility** — Each class manages exactly one concern (Car, Customer, Rental, DB connection)
- **Composition** — `Rental` is composed of a `Car` and a `Customer`, modeling a real-world relationship

## 🗄️ Database Design

Three normalized relational tables:

```sql
cars        (car_id PK, brand, model, base_price_per_day, is_available)
customers   (customer_id PK, name)
rentals     (rental_id PK, car_id FK, customer_id FK, rental_days, total_price)
```

- **Primary Keys** uniquely identify each car, customer, and rental
- **Foreign Keys** in `rentals` enforce that a rental always references a valid car and customer
- **PreparedStatements** are used throughout to prevent SQL injection

---


## 🚀 Usage

```
=== Car Rental System ===
1. View Available Cars
2. Rent a Car
3. Return a Car
4. Add a New Car
5. Exit
```

Simply enter the number corresponding to your choice and follow the prompts.

---

## 🔮 Future Improvements

- [ ] Add SQL transaction handling for multi-step operations (atomic rent/return)
- [ ] Replace the binary `is_available` flag with date-range based booking
- [ ] Migrate to a web-based frontend using **Spring Boot** + **Thymeleaf**, replacing console I/O with REST controllers and HTML views
- [ ] Add proper user authentication (Spring Security)


---
