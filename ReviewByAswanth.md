## Code Review

### General Comments
- Add a README file to get an idea about the project.

### Service class
- Inside getEmployeeByDepartment an exception can be thrown where no employees with requested department is not found.

### Repository class
- Add @Repository annotation in the class.

### Controller class
- The class is already annotated with @RestController annotation, so @ResponseBody can be removed from the methods.
