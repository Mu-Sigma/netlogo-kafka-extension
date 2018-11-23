## Introduction

### Objective of NetLogo kafka Extension

This netlogo extension lets you stream events from your NetLogo model to a kafa topic. You can connect to a kafka cluster 
and push to any topic in the cluster.

Kafka accepts data in the key:value format. This extension lets the user provide
both the key and value in String format. No other data type is supported in this
version.

Currently, you can only push to one kafka cluster from your simulation.

## Updates

### Version 2.0 (September 10 2018)

* The primitives have been renamed for better understanding and easier use
* Introduction of asyc 'send' primitives for faster throughput (Fire and forget)   

#### Available primitives (Version 2.0)

* start-kafka-producer *list_of_brokers(comma-separated)* *default_topic*
* stop-kafka-producer
* send *key* *value*
* send-to-topic *topic* *key* *value*
* send-async *key* *value*
* send-to-topic-async *topic* *key* *value*

**kafka-message** primitive streams data to the *default_topic* defined when the 
**start-kafka-producer** is used

If you want to stream data to a different topic than the default one use 
**kafka-message-to-topic** primitive.

## Usage

### Build

1. Clone the project. You would need Java and Maven before you proceed
2. Run 
  ```
  mvn clean install
```
3. The target folder should have `kafka-producer-jar-with-dependencies.jar`

### Add extension to NetLogo

1. Create a folder called 'kafka-producer' in app/extensions directory of your
NetLogo installation.
2. Copy the jar to that folder and rename it to kafka-producer.jar

You can now use kafka-producer extension in your simulation

### Using it in Simulation

```
;; declare extension
extensions [
  kafka-producer
]

;;setup kafka on init.
to setup-kafka
  kafka-producer:start-kafka-producer "localhost:9092" "netLogo"
end


;; push message to topic "netLogo"
to push-message
   kafka-producer:kafka-message (word self) (word date-and-time "hello")
end

;; stop the producer when simulation is done
to stop-kafa
   kafka-producer:stop-kafka-producer
end
```

  
