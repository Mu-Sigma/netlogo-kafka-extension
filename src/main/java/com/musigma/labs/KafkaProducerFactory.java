package com.musigma.labs;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducerFactory {

    private static Logger logger = Logger.getLogger(KafkaProducerUtil.class);

    private static KafkaProducerUtil producer;

    public static KafkaProducerUtil getProducer() throws RuntimeException {
        if(producer == null)
            throw new RuntimeException("Not initialized");

        return producer;
    }

    public static void create(String bootstrapServer, String defaultTopic) {
        if(KafkaProducerFactory.producer != null) {
            logger.warn("Deleting the old producer and creating a new one");
            delete();
        }

        KafkaProducerFactory.producer = new KafkaProducerUtil(bootstrapServer, defaultTopic);
    }

    public static void delete()  {
        if(producer != null) {
            producer.stopProducer();
            producer = null;
        }
    }

    public static class KafkaProducerUtil {

        private String topic;
        private static final String CLIENT_ID = "KafkaNetLogo";

        private Producer<String, String> producer;

        /**
         * Create a producer (String key and String value)
         *
         * @param bootstrapServers
         */
        private KafkaProducerUtil(String bootstrapServers, String defaultTopic) {

            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                    bootstrapServers);
            props.put(ProducerConfig.CLIENT_ID_CONFIG,
                    CLIENT_ID);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                    StringSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                    StringSerializer.class);

            this.topic = defaultTopic;
            this.producer =  new KafkaProducer<String, String>(props);
        }

        public void sendMessageAsync(String topic, String key, String value)  {
            final ProducerRecord<String, String> record =
                    new ProducerRecord<String, String>(topic, key, value);

            //Fire and forget. No worry about failures
            producer.send(record);
        }


        public void sendMessageAsync(String key, String value) {
            sendMessageAsync(this.topic, key, value);
        }

        public void sendMessage(String topic, String key, String value)  {
            final ProducerRecord<String, String> record =
                    new ProducerRecord<String, String>(topic, key, value);

            try {
                RecordMetadata recordMetadata = producer.send(record).get();

                if(logger.isDebugEnabled()) {
                    logger.debug(String.format("Record -> key: %s, value: %s, topic: %s, partition: %s, offset: %s",
                            key, value, topic, recordMetadata.partition(), recordMetadata.offset()));
                }
            } catch (InterruptedException e) {
                logger.error("Exception when sending a message", e);
            } catch (ExecutionException e) {
                logger.error("Exception when sending a message", e);
            }
        }

        public void sendMessage(String key, String value) {
            sendMessage(this.topic, key , value);
        }

        public void stopProducer() {
            producer.flush();
            producer.close();

        }
    }

}
