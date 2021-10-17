package net.cukrus.woValidationDemo.validation;

import java.util.Date;

public class DateValidator extends Validator {
    private final DateValidationMode mode;
    private final Date toValidate;
    private final Date toCompare;
    private final Date[] betweenDates;

    private DateValidator(DateValidationMode mode, Date toValidate, Date toCompare, Date[] betweenDates) {
        this.mode = mode;
        this.toValidate = toValidate;
        this.toCompare = toCompare;
        this.betweenDates = betweenDates;
    }

    @Override
    protected String internalValidate() {
        //TODO implement;
        return null;
    }

    public static DateValidator before(Date toValidate, Date toCompare) {
        return new DateValidator(DateValidationMode.BEFORE, toValidate, toCompare, null);
    }

    public static DateValidator after(Date toValidate, Date toCompare) {
        return new DateValidator(DateValidationMode.AFTER, toValidate, toCompare, null);
    }

    public static DateValidator between(Date toValidate, Date... betweenDates) {
        return new DateValidator(DateValidationMode.BETWEEN, toValidate, null, betweenDates);
    }

    public enum DateValidationMode {
        BEFORE, AFTER, BETWEEN;
    }
}