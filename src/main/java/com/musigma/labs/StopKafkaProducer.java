package com.musigma.labs;

import org.nlogo.api.Argument;
import org.nlogo.api.Command;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

public class StopKafkaProducer implements Command {

    public void perform(Argument[] args, Context context) throws ExtensionException {
        KafkaProducerFactory.delete();
    }

    public Syntax getSyntax() {
        return SyntaxJ.commandSyntax();
    }
}
