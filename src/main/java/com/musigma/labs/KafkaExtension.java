package com.musigma.labs;

import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.PrimitiveManager;

public class KafkaExtension extends DefaultClassManager {

    public void load(PrimitiveManager primManager) throws ExtensionException {
        primManager.addPrimitive("start-kafka-producer", new CreateKafkaProducer());
        primManager.addPrimitive("stop-kafka-producer", new StopKafkaProducer());
        primManager.addPrimitive("kafka-message", new SendKafkaMessage());
        primManager.addPrimitive("kafka-message-to-topic", new SendKafkaMessageToTopic());
    }
}

