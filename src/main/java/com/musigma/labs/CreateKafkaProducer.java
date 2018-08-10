package com.musigma.labs;

import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class CreateKafkaProducer implements Command {

    public void perform(Argument[] args, Context context) throws ExtensionException {
        try {
            String servers = args[0].getString();
            String topic = args[1].getString();

            KafkaProducerFactory.create(servers, topic);
        }
        catch(Exception e) {
            throw new ExtensionException( e.getMessage() , e) ;
        }
    }

    /**
     * Command has 2 string arguments to the right
     *
     * 1. bootstrap-servers (comma separated)
     * 2. Topic name for this producer
     *
     * @return
     */
    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax(new int[]{Syntax.StringType(), Syntax.StringType()});
    }
}
