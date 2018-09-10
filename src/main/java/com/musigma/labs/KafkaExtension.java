package com.musigma.labs;

import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.PrimitiveManager;

public class KafkaExtension extends DefaultClassManager {

    public void load(PrimitiveManager primManager) throws ExtensionException {
        primManager.addPrimitive("start-kafka-producer", new CreateKafkaProducer());
        primManager.addPrimitive("stop-kafka-producer", new StopKafkaProducer());
        primManager.addPrimitive("send", new SendKafkaMessage());
        primManager.addPrimitive("send-async", new SendKafkaMessage(true));
        primManager.addPrimitive("send-to-topic", new SendKafkaMessageToTopic());
        primManager.addPrimitive("send-to-topic-async", new SendKafkaMessageToTopic(true));
    }
}

