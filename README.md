# My Personal Project: Clean Cuts

### What will the application do? 

The application I will be creating will be an appointment booking application for a landscaping company called 
Clean Cuts. The application will allow customers to book a specific landscaping service that the 
customer needs. The application will give the customer a chance to pick a worker and which day and time they need the 
service as long as the worker is eligible for the service and is available for that day. The application will allow
bookings for a week at a time. 


## **Services Include:**
- Lawn Mowing & Trimming
- Yard Cleanup
- Gardening & Weed Control
- Flower Services

### Who will use it?

The application will be used by anyone in need of the landscaping services our company provides. 

### Why is this project of interest to you? 

This project is in interest to me because for the last few summers I have opened by own landscaping business called 
**Clean Cuts** were I mainly focused on lawn mowing and trimming. I had come across the problem that it was hard to 
manage all the calls and jobs received, and I always thought how nice it would be to have an automated booking system.
Therefore, this project is the perfect chance to do just that! As if I am ever to hire employees it would be easy to 
add them to my list workers for customers to book, which makes the whole process easier for the business owner and the 
customer. 


## User Stories 

- As a user, I want to be able to select a service. 
- As a user, I want to be able to select a worker for that service.
- As a user, I want to be able to select a day for that service and worker.
- As a user, I want to be able to add that service, worker and day to the scheduled appointments list. 
- As a user, I want to be able to view a list of my scheduled appointments. 
- As a user, I want to be able to save my scheduled appointments to file after booking at least one appointment. 
- As a user, I want to be able to load my scheduled appointments from file after booking at least one appointment. 

# Instructions for Grader

- You can generate the first required event by typing in the JTextField with the name of one of the given services 
and clicking the book button.
- You can generate the second required event by clicking the button that is labeled remove appointments, which will 
remove the last added appointment.
- You can locate my visual component by at least booking one full service, by booking a service, worker and date, then 
clicking the view booked appointments button. This will show the company logo which is my visual component.
- You can save the state of my application by booking a full service and clicking the button that is labeled save. 
- You can reload the state of my application by clicking the load button and then clicking the view booked appointments
button.

# Phase 4 : Task 2
Thu Aug 11 16:10:24 PDT 2022
Booked a service!

Thu Aug 11 16:10:24 PDT 2022
Booked a worker!

Thu Aug 11 16:10:25 PDT 2022
Booked a date!

Thu Aug 11 16:10:32 PDT 2022
Removed last added appointment!

# Phase 4 : Task 3

- If I had more time I would for sure do some major refactoring. My BookingAppGUi in my ui package which is my mine GUI
class has Low cohesion. The class does too many jobs, so first off I would refactor every job into its own class in the
ui. 
- Also, I would make an interface for Services, Workers and Dates as there functionality is quite similar.






