## Spring Boot Data JPA Batch Inserts

## Overview
Going out to the database is expensive. We may be able to improve performance and consistency by batching multiple inserts into one.

we’ll look at how to do this with Spring Data JPA.

We need to execute saveAll method, which will batch several inserts into one.

```
Customer c1 = new Customer("James", "Gosling");
Customer c2 = new Customer("Doug", "Lea");
Customer c3 = new Customer("Martin", "Fowler");
Customer c4 = new Customer("Brian", "Goetz");
List<Customer> customers = Arrays.asList(c1, c2, c3, c4);
customerRepository.saveAll(customers);
```

### Are We Sure We’re Batching?

<b>So, actually, there is a just a bit more configuration to do</b> – if you do a quick test to illustrate the difference.

First, let’s add the following property to application.properties to see some statistics:

```
spring.jpa.properties.hibernate.generate_statistics=true
```

At this point, if we run the test, we’ll see statistics like the following:

```
11232586 nanoseconds spent preparing 4 JDBC statements;
4076610 nanoseconds spent executing 4 JDBC statements;
0 nanoseconds spent executing 0 JDBC batches;
```

So, we created four customers, which is great, <b>but note that none of them were inside a batch.</b>

<b>The reason is that batching isn’t switched on by default in some cases.</b>

In our case, it’s because we are using id auto-generation. <b>So, by default, saveAll does each insert separately.</b>

So, let’s switch it on:

```
spring.jpa.properties.hibernate.jdbc.batch_size=4
spring.jpa.properties.hibernate.order_inserts=true
```

The first property tells Hibernate to collect inserts in batches of four. The order_inserts property tells Hibernate to take the time to group inserts by entity, creating larger batches.

<b>So, the second time we run our test, we’ll see the inserts were batched:</b>

```
16577314 nanoseconds spent preparing 4 JDBC statements;
2207548 nanoseconds spent executing 4 JDBC statements;
2003005 nanoseconds spent executing 1 JDBC batches;
```

We can apply the same approach to deletes and updates (<b>remembering that Hibernate also has an order_updates property</b>).

```
spring.jpa.properties.hibernate.order_updates=true
```