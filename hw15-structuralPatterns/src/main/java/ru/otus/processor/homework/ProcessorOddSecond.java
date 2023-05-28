package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;

public class ProcessorOddSecond implements Processor {

    private DateTimeProvider dateTimeProvider;

    public void setDateTimeProvider(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        int second;
        if (dateTimeProvider == null) {
            second = LocalDateTime.now().getSecond();
        } else {
            second = dateTimeProvider.getDateTime().getSecond();
        }
        if (isEven(second)) {
            throw new EvenSecondException("Processing a message in an even second");
        }
        return message;
    }

    private boolean isEven(int second) {
        return second % 2 == 0;
    }

    public static class EvenSecondException extends RuntimeException {
        public EvenSecondException(String message) {
            super(message);
        }
    }
}
