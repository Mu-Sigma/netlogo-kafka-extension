### NetLog kafka Extension

Stream events from your NetLogo model to a kafa topic. You can connect to a kafka cluster 
and push to any topic in the cluster.

Kafka accepts data in the key:value format. This extension lets the user provide
both the key and value in String format. No other data type is supported in this
version.

Currently, you can only push to one kafka cluster from your simulation.   

#### Available primitives

* start-kafka-producer *list_of_brokers(comma-separated)* *default_topic*
* stop-kafka-producer
* kafka-message *key* *value*
* kafka-message-to-topic *topic* *key* *value*

**kafka-message** primitive streams data to the *default_topic* defined when the 
**start-kafka-producer** is used

If you want to stream data to a different topic than the default one use 
**kafka-message-to-topic** primitive.

#### Downloads

You can download the jar from [IRD archiva](http://ird.mu-sigma.com/archiva/repository/internal/com/musigma/labs/kafka-netlogo/1.0/kafka-netlogo-1.0.jar).
User your I&D credentials to download.

#### How to add this extension to NetLogo

1. Download the jar.
2. Create a folder called 'kafka-producer' in app/extensions directory of your
NetLogo installation.
3. Copy the jar to that folder and rename it to kafka-producer.jar

You can now use kafka-producer extension in your simulation

#### Using it in Simulation

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

  