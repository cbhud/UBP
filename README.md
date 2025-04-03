## SQL DATABASE PROJECT

- Design database
- Implement database
- Implement Java app for CRUD operations(I'am aware that there are tons of possible optimizations for JAVA app but here was the focus on DATABASE IMPLEMENTATION)

## Documentation:

# UNIVERSITY "MEDITERAN" PODGORICA

## FACULTY OF INFORMATION TECHNOLOGY, PODGORICA

# INTRODUCTION TO DATABASES

# Final Exam - Railway

## Table of Contents:

1. Introduction  
2. Scenario  
3. ER Model  
4. Relational Model  
5. Diagram  
6. Application Description  
7. Conclusion  

---

## 1. Introduction

As part of this project, a railway database management system has been developed to efficiently keep track of key business segments. The project includes an ER model, a relational database, and a Java application that manages data and monitors business statistics in real time, allowing management to plan future operations. The documentation provides a detailed overview of the ER model, relational models, diagrams, MySQL implementation, and additional functionalities. The application will enable basic operations on the database, such as adding new data, viewing existing data, updating, and deleting information.

---

## 2. Scenario

The scenario is as follows:

“You have been hired by the railway management to create a database for their business operations. Their requirement is to maintain records of trains, schedules, employees, and ticket sales. Each employee is uniquely identified by their personal identification number, and additional information such as name, surname, hiring date, and total work experience should be stored. For drivers, the total hours driven must be recorded. Drivers undergo an annual medical examination, and the dates and findings (text up to 250 characters) of these examinations should be stored in the database. Each train has a unique identifier, an allowed number of wagons, and a year of manufacture. There are several types of trains, such as freight and passenger trains. Each train belongs to exactly one type. For each driver, records must be kept of the types of trains they are authorized to drive. Ticket sales are based on schedules. The schedule is created periodically, and information about its creation date and the employee who created it must be stored. The schedule includes details about the train’s departure date and time, the driver operating the train, as well as the number of standard and VIP passenger seats available, ticket prices, and VIP ticket prices. Passengers purchase tickets based on the schedule. When selling tickets, information such as the sale date, ticket number, the name and surname of the ticket holder, and the ticket price (depending on whether the passenger chose a VIP or standard seat) must be recorded.”

---

## 3. ER Model

```
Figure 1. ER Diagram
```

As the first step, based on the given scenario, an ER diagram was created as shown in Figure 1. In addition to the information from the scenario, the schedule entity was given an additional attribute, destination, considering its relevance.

---

## 4. Relational Model

Based on the ER diagram, a relational model was created, where a pricing table was added for normalization to avoid price duplication.

```
employees(id, personal_id, name, surname, work_experience, hiring_date)

drivers(id, driving_hours, employee_id) //employee_id NOT NULL

medical_exam(id, exam_date, findings, driver_id) //driver_id NOT NULL

train_type(id, type_name)

driver_train_type(driver_id, train_type_id) //train_type_id, driver_id NOT NULL

trains(id, identifier, wagon_count, year_of_manufacture, train_type_id) //train_type_id NOT NULL

schedule(id, creation_date, destination, departure_datetime, standard_seats, vip_seats, employee_id, driver_id, train_id) //employee_id, driver_id, train_id NOT NULL

pricing(id, price, vip_price, schedule_id) //schedule_id NOT NULL

tickets(id, ticket_number, sale_date, name, surname, seat_type, schedule_id) //schedule_id NOT NULL
```

**NOTE:** seat_type possible values are VIP and Standard (the price is determined based on the seat type).

---

## 5. Diagram

Based on the work done so far, a diagram was generated using MySQL Workbench.

```
Figure 2. Diagram
```

---

## 6. Application Description

The task related to the Java application involved creating an application that enables access, modification, and deletion of data. For these purposes, the JDBC package was used to connect the created MySQL database with the Java application. This is achieved by creating CRUD functions.

The Java application enables:

- Employee management  
- Train management  
- Schedule management  
- Ticket management  
- Business statistics display (revenue, number of tickets sold per month, etc.)  

CRUD functions refer to create, read, update, and delete operations.

The `DbConnector` class contains a constructor for connection management, as well as `open()` and `close()` methods that open and close the database connection as needed.

The `Menus` classes provide a user-friendly interface for calling basic CRUD methods for each of the mentioned functionalities, allowing easy interaction via keyboard input.

Initially, the user selects which table to work with. The menu is displayed on the screen. Selecting a table opens the next menu that offers all available operations for managing the selected table.

---

## 7. Conclusion

This project demonstrates an understanding of the scenario, ER modeling, and relational diagram development, as well as database integration with a Java application that enables easy data management and business statistics tracking. This system will allow the employer to plan and further develop their business operations effectively.
