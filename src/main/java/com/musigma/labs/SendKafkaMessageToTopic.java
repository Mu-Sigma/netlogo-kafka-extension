package com.musigma.labs;

import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class SendKafkaMessageToTopic implements Command {

    private boolean isAsync = false;

    SendKafkaMessageToTopic() {

    }

    public SendKafkaMessageToTopic(boolean isAsync) {
        this.isAsync = isAsync;
    }

    public void perform(Argument[] args, Context context) throws ExtensionException {
        try {
            String topic = args[0].getString();
            String key = args[1].getString();
            String value = args[2].getString();

            if(this.isAsync) {
                KafkaProducerFactory.getProducer().sendMessageAsync(topic, key, value);
            } else {
                KafkaProducerFactory.getProducer().sendMessage(topic, key, value);
            }
        } catch (LogoException e) {
            throw new ExtensionException(e.getMessage());
        }
    }

    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[] {Syntax.StringType(), Syntax.StringType(), Syntax.StringType()});
    }
}
