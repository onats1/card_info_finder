# card_info_finder

This project makes use of MVI and Clean Code software architectures.

## Project Structure
1. Network Datasource
2. Repository
3. Use Case
4. View Model
5. User Interface

The application is split into two major parts which are the business and framework directories. 
The business section contains code/components that are independent
of the android framework. These include: Network Datasource, Repository and the Use Case.
The framework section contains android specific code: These include the View Model and the User Interface code. Dependency management is also handled in this section.

#Error Handling
Network errors are handled at the Repository layer and then propagated to the Use Case which converts the error to a user-friendly error message.


