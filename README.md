# challenge
Restful API in Java with Spring-Boot that 
contains two APIs, one processes the transactions and other one shows the statistics.


### Table of Contents

- [Run](#Run)
- [Resources](#Resources)
- [Testing](#Testing)
- [Authors / Contributors](#authors-contributors)

<a name="Run"></a>
## Run

1. Clone the repository:

  ```bash
  $ git clone git@github.com:iShuga139/challenge.git
  $ cd challenge
  ```
2. Execute the applications by running a bash file:

  ```bash
  $ sh startup.sh
  ```

* NOTE: The terminal will continuosly receive the output from the processes; if you want to shutdown the processes, press ```enter``` and execute the following command:

  ```bash
  $ sh shutdown.sh
  ```

<a name="Resources"></a>
## Resources

## => Transactions API
* POST /transactions
* Port: 8080
* Body params
  ```
    "amount": double
    "timestamp": Long
  ```
* E.G.
  ```
  curl -i -X POST "http://localhost:8080/transactions" -H "Accept: application/json" -H "Content-Type: application/json" -d "{ \"amount\": 90.5, \"timestamp\":1478995219000}"
  ```
  - Response
    ```
    HTTP/1.1 204
    Date: Sun, 08 Apr 2018 21:34:05 GMT
    ```

## => RT-Statistics API
* GET /statistics
* Port: 8090
* E.G.
  ```
  curl -X GET http://localhost:8090/statistics | jq
  ```
  - Response
    ```
    {
      "sum": 103,
      "avg": 51.5,
      "min": 12.5,
      "max": 90.5,
      "count": 2
    }
    ```

Note: You can use any consumer API as POSTMAN or SOAPUI, we used cURL command line. If you don't have jq just removed it from the GET request example.

<a name="Testing"></a>
## Testing

This program uses defaults of Spring-Boot with Maven scope.
Each API contains his own tests.

To test the Transactions API run:

  ```bash
  $ cd transactions
  $ ./mvnw test
  ```
To test the Statistics API run:

  ```bash
  $ cd rt-statistics
  $ ./mvnw test
  ```

<a name="authors-contributors"></a>
## Authors / Contributors

- **Maintainer:** iShuga
- **Author:** Jonathan Estrada - <jeaworks@hotmail.com>
- **Contributors:**