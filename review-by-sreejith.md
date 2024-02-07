# Service

## createEmployee
- A 400 Bad Request error occurs when null values are provided in the name and email fields.
- Validation is not working as expected in the department field.

## getEmployee
- A 500 Internal Server Error occurs when providing an ID that does not exist in the database.

## Security Concerns
- Consider using environment variables or a secure configuration management system (Regarding db name,password in yml file)

### General issues
- validations,exceptions are not working -> ( getEmployee,createEmployee )
- //Todo: add readme file
