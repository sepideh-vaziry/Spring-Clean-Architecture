## Spring Boot Clean Architecture

An example of clean architecture with Spring Boot.
As we can see, an important part is to divide our system into specific layers. Even more important is to preserve a particular direction of dependencies between layers. 
Dependencies should go from the outside to the inside.
A lot of people argue that the domain layer should be also completely free from frameworks code. 
There is a lot of sense in this opinion, but I think that in practice, we should always be driven by pragmatism and apply this rule with common sense. 