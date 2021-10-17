# WorkOrderValidationDemo
Demo project for WorkOrder validation

## Description
The role of the service: validation work orders – Analysis, Repair, Replacement. Each work order contains
collection of parts.

## Technical requirements
1. The service shall expose a REST interface consuming work orders in JSON format and returning validation result
   to the client
2. Service shall be flexible to extend the validation logic in terms of:
- new business validation rules
- new types to be supported
3. Service shall store all validation requests history in DB:
- date of receiving request
- type of work order
- department
- status: valid/not valid
4. Service shall have UI parts:
- fill JSON request and check validation result
- get all validation requests history

## Business requirements
  The following validation rules shall be implemented:
* For all work orders
  * department: not empty and in the valid department list
  * start date: not empty and before current date
  * end date: not empty and after start date
  * currency: valid ISO code (ISO 4217)
  * cost: greater than 0
* Repair
  * analysis date: after start date and before end date
  * responsible person: not empty
  * test date: not empty, after analysis date and before end date
  * parts: total count greater than 0
* Replacement
  * factory name: not empty
  * factory order number: the length is 10, first 2 characters - letters, others – numbers.
  * parts: all inventory numbers are not empty

In case of multiple errors - all of them should be in response

## Evaluation criteria
Depends on candidate/position level the following criteria will be checked:

* Basic
  * Data model for work order
  * Validation rules
* Middle
  * Creating standalone application
  * Controller for receiving request and sending validation response
  * Data model for saving validation requests history to DB
* Expert
  * Persistence layer for saving validation requests history to DB
  * Creating UI for checking work order. It should be simple form (jsp) with 2 fields:
  * request – where json will be passed
  * response – result of validation
* Master
  * Controller for sending validation requests history
  * UI for validation requests history
  * Using database version control framework (flyway, liquibase)

## Test data
```
{"type":"ANALYSIS","department":"GOoD analysis department","start_date":"2020-
08-13","end_date":"2020-08-
15","currency":"USD","cost":123.12,"parts":[{"inventory_number":"InventoryNumb
er1","name":"PartNumber1","count":1},{"inventory_number":"InventoryNumber2","n
ame":"PartNumber2","count":2}]}
```

```
{"type":"REPAIR","department":"GOoD repair department","start_date":"2020-08-
13","end_date":"2020-08-16","analysis_date":"2020-08-14","test_date":"2020-08-
15","responsible_person":"GOoD repair 
master","currency":"USD","cost":123.12,"parts":[{"inventory_number":"Inventory
Number3","name":"PartNumber3","count":3},{"inventory_number":"InventoryNumber4
","name":"PartNumber4","count":4}]}
```

```
{"type":"REPLACEMENT","department":"GOoD replacement
department","start_date":"2020-08-13","end_date":"2020-08-
16","factory_name":"GOoD 
factory","factory_order_number":"DE12345678","currency":"USD","cost":123.12,"p
arts":[{"inventory_number":"InventoryNumber5","name":"PartNumber5","count":5},
{"inventory_number":"InventoryNumber6","name":"PartNumber6","count":6}]}
```
