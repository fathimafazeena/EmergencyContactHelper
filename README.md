# Emergency Contact Helper 

A lightweight Android application designed to help users store and quickly access emergency contact information. 

## Project Description
The **Emergency Contact Helper** allows users to manage a personal directory of emergency contacts. In urgent situations, users can quickly find phone numbers, relationships, and specific medical or situational notes. The app features a "Quick Access" dashboard for the most critical contacts.

## Key Features
- **User Authentication:** Secure Registration and Login system.
- **Contact Management:** Full CRUD (Create, Read, Update, Delete) operations for emergency contacts.
- **Quick Access:** Mark specific contacts for immediate visibility on the dashboard.
- **Data Privacy:** Users can only access and manage their own saved contacts.
- **Input Validation:** Ensures all entries are correctly formatted before saving.

## Technologies Used
- **Language:** Java
- **Database:** SQLite (Local storage)
- **UI Framework:** Android XML Layouts
- **Version Control:** Git & GitHub

## Database Schema
The app utilizes two primary tables:
1. `users`: Stores user credentials (`id`, `name`, `email`, `password`, `phone`).
2. `contacts`: Stores emergency contact details (`id`, `user_id` (FK), `name`, `phone`, `relationship`, `notes`, `is_quick_access`).

## Team Members
- **Member 1 M.F.F.Fazeena** - (Index-5631/Registration No-ICT/2022/024)
- **Member 2 J.B.H.S.Hansani** - (Index-5632/Registration No-ICT/2022/025)
- **Member 3 K.D.A.Nethmini** - (Index-5633/Registration No-ICT/2022/026)

