Online Appointment Booking Application:
Overview:

The Online Appointment Booking Application is designed to facilitate appointment scheduling between patients and doctors.
Patients can register, search for doctors based on specialization, and request slots for appointments.
Admins manage doctors, appointment slots, and generate reports.
The system also includes payment and document management features.

Features:
1. User Roles:-
Admin: Manages doctors, appointment slots, patient approvals, reports, and document uploads.
Employee/Staff: Assists in managing appointment requests.
Doctor: Views assigned appointments, manages profiles, and updates availability.
Patient: Registers, searches for appointments, requests slots, cancels appointments, and makes payments.

2. Modules:
Specialization: Admin adds various doctor specializations like Cardiology, General Surgery, etc.
Doctor: Admin registers doctors; doctors can update their profiles and manage appointments.
Appointment: Admin assigns available slots, including date, fee, and available slots.
Patient: Patients register, book appointments, and request approval.
Slots: Tracks appointment status - Pending, Approved, Rejected, or Cancelled.
Documents: Admin manages hospital-related documents, doctor certificates, and contracts.
Reports: Generates PDF/Excel reports on appointments, doctors, and patients.

3. Payment & Invoicing:
Patients can download invoices and pay via different payment channels.

Technologies Used:
Backend: Spring Boot 3.4.3, Spring Security (JWT), Spring REST, Data JPA
Database: MySQL
Cloud & Others: AWS (File storage, hosting), Java Mail API, Log4J, Maven, JUnit

Future Enhancements:
Integration with Razorpay for seamless payments.
Adding Real-time Notifications (via email or SMS) for appointment status updates.
Implementing AI-based Doctor Recommendations based on patient history.

Contributors:
Sandeep Verma (Java Developer)
