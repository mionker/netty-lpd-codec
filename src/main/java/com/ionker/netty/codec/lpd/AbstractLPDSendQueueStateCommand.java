package com.ionker.netty.codec.lpd;

import java.util.Arrays;
import java.util.List;

public class AbstractLPDSendQueueStateCommand extends AbstractLPDCommand {

    final private List<String> list;

    public static AbstractLPDSendQueueStateCommand fromCommandOperands(String operands) throws LPDException {

        final List<String> operandsList = Arrays.asList(operands.split("\\s+"));

        switch (operandsList.size()) {

        case (1):
            return new AbstractLPDSendQueueStateCommand(operandsList.get(0), null);

        case (0):
            throw new LPDException("Incomplete send queue state command, no queue specified!");
            
        default:
            return new AbstractLPDSendQueueStateCommand(operandsList.get(0),
                    operandsList.subList(1, operandsList.size() - 1));
        }
    }

    public AbstractLPDSendQueueStateCommand(String queue, List<String> list) {
        super(queue);
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

}
