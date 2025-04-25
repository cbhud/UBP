# 🚄 Railway Management System - SQL Database Project

_Introduction to Databases Final Exam_

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)  
[![University](https://img.shields.io/badge/university-Mediteran%20Podgorica-blue)](https://unimediteran.net)  
[![Faculty](https://img.shields.io/badge/faculty-Information%20Technology-lightgrey)]()

---

## 📍 University "Mediteran" Podgorica

**Faculty of Information Technology**

📚 **Course:** Introduction to Databases  
👨‍🏫 **Professor:** Dr. Ivan Knezević  
🧑‍🎓 **Final Exam Project:** Railway Management System  
🗓️ **Date:** January 2025  
📍 **Location:** Podgorica

---

## 📖 Table of Contents

1. [Introduction](#1-introduction)
2. [Scenario](#2-scenario)
3. [ER Model](#3-er-model)
4. [Relational Model](#4-relational-model)
5. [Diagram](#5-diagram)
6. [Application Description](#6-application-description)
7. [Conclusion](#7-conclusion)

---

## 1. Introduction

This project presents a complete **Railway Management System**, built to model real-world operational needs for scheduling, ticketing, and employee management. It consists of:

- An **Entity-Relationship (ER) model**
- A **normalized relational database**
- A **Java application** for full CRUD capabilities

This work showcases core technical competencies in:

- SQL database design and normalization
- Database schema modeling using MySQL Workbench
- Java development using JDBC and modular object-oriented design
- Integrating backend systems for real-time data manipulation and reporting

---

## 2. Scenario

The project simulates a system for railway business operations, including:

- Employee and driver management (with unique identification)
- Recording of medical examinations (with constrained findings)
- Train and train type classification
- Schedules and pricing per destination
- Ticket sales with dynamic pricing (VIP/Standard)

All entities are interlinked and enforced by foreign key constraints, ensuring **data integrity and normalization**.

---

## 3. ER Model

```
Figure 1. ER Diagram
```

The ER diagram was built based on the scenario, with logical additions such as the `destination` attribute to the `schedule` entity to enhance business relevance.

---

## 4. Relational Model

Below is a simplified representation of the final relational schema:

```
employees(id, personal_id, name, surname, work_experience, hiring_date)
drivers(id, driving_hours, employee_id)
medical_exam(id, exam_date, findings, driver_id)
train_type(id, type_name)
driver_train_type(driver_id, train_type_id)
trains(id, identifier, wagon_count, year_of_manufacture, train_type_id)
schedule(id, creation_date, destination, departure_datetime, standard_seats, vip_seats, employee_id, driver_id, train_id)
pricing(id, price, vip_price, schedule_id)
tickets(id, ticket_number, sale_date, name, surname, seat_type, schedule_id)
```

> 🔍 **Normalization applied:** All pricing details moved to a separate `pricing` table to avoid redundancy.

**Note:** `seat_type` can be either _VIP_ or _Standard_, used to determine ticket pricing.

---

## 5. Diagram

The final ER and relational model were visually represented using **MySQL Workbench**, ensuring a **professional and industry-standard layout** for further implementation and documentation.

```
Figure 2. Full ER Diagram (MySQL Workbench)
```

---

## 6. Application Description

The accompanying **Java application** (using JDBC) enables real-time database access and supports:

- Adding, viewing, updating, and deleting data (CRUD)
- Business statistics: ticket sales, monthly revenue tracking, etc.
- Menu-driven interface for easy user interaction via console

### 🔧 Java Features

- `DbConnector` class handles connection setup and teardown
- Modular design using classes like `Menu`, `TicketManager`, `TrainManager` etc.
- Defensive coding techniques to ensure database consistency

> 💡 **Note:** The project focuses primarily on **database design and sql optimizations**, — the Java application was a secondary addition, so it may contain some coding mistakes and bad coding practices..

---

## 7. Conclusion

This project demonstrates:

✅ Strong understanding of **database architecture** and **normalization**  
✅ Practical **ER modeling** and **relational design** skills  
✅ Proficiency with **MySQL** and **SQL schema implementation**  
✅ Hands-on **Java experience** integrating JDBC and building real-world backend systems  
✅ Ability to design and implement scalable systems and perform complex queries

> This work reflects both academic knowledge and industry-oriented technical skills.

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).

---

## 💬 Feedback & Contributions

Contributions, feedback, and suggestions are welcome.  
Feel free to open issues or submit pull requests.

---
