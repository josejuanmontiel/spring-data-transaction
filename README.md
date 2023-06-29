# Overview [![Build status](https://drone.io/github.com/manish-in-java/spring-data-transaction/status.png)](https://drone.io/github.com/manish-in-java/spring-data-transaction/latest) [![Code coverage](https://coveralls.io/repos/manish-in-java/spring-data-transaction/badge.svg?branch=master)](https://coveralls.io/r/manish-in-java/spring-data-transaction?branch=master)
Demonstrates various techniques with the Spring framework for managing transactions
in a typical application.

* [AOP based transaction support](src/main/resources/springServiceContext.xml)
* [Declarative transaction management with the `@Transactional` annotation](src/main/java/org/example/service/BillingService.java)
* [Declarative retry support using Spring Retry](src/main/java/org/example/service/BillingService.java)

// TODO SpringBoot update...

@EnableRetry
public class Application extends SpringBootServletInitializer {


# License
This sample application and its associated source code in its entirety is being made
available under the following licensing terms.

    Copyright (C) 2015

    Permission is hereby granted, free of charge, to any person obtaining a copy of
    this software and associated documentation files (the "Software"), to deal in the
    Software without restriction, including without limitation the rights to use, copy,
    modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
    and to permit persons to whom the Software is furnished to do so, subject to the
    following conditions:

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
    PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
    HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
    CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
    OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
