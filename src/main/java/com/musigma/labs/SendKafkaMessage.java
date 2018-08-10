package com.musigma.labs;

import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class SendKafkaMessage implements Command {

    public void perform(Argument[] args, Context context) throws ExtensionException {
        try {
            String key = args[0].getString();
            String value = args[1].getString();

            KafkaProducerFactory.getProducer().sendMessage(key, value);
        } catch (ExtensionException e) {
            throw new ExtensionException(e.getMessage());
        }
    }

    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.StringType(), Syntax.StringType()});
    }
}
