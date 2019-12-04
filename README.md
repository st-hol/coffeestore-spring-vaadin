# coffeestore-spring-vaadin

Used next technologies: 
Spring, 
Hibernate, 
Vaadin view framework, 
patterns (decorator, factory method, observer) - just for learning purpose - yes, i know its bad idea to use them all in such small project.

Challenges i faced during writing this:
1)persisting all this "decorated" entities with ORM -> solved with the help of 

        @Entity 
        @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
        @DiscriminatorColumn(name="discr") --- on DecoratedComponent

        and  @MappedSuperclass on concreteDecorator

        (see code in "domain" package)

2)I learned to use @NamedNativeQuery and @SqlResultSetMapping since i haven't used them before.
