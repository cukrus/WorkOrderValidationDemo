package net.cukrus.woValidationDemo.validation;

import java.util.Date;

public class DateValidator extends Validator {
    private static final String UNABLE_TO_COMPARE = "unable to compare, one or more dates is null";
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
        switch (mode) {
            case BEFORE:
                if (toValidate == null || toCompare == null) {
                    return UNABLE_TO_COMPARE;
                }
                if (!toValidate.before(toCompare)) {
                    return "not before compared date";
                }
                break;
            case AFTER:
                if (toValidate == null || toCompare == null) {
                    return UNABLE_TO_COMPARE;
                }
                if (!toValidate.after(toCompare)) {
                    return "not after compared date";
                }
                break;
            case BETWEEN:
                if (toValidate == null || betweenDates == null || betweenDates.length != 2 || betweenDates[0] == null || betweenDates[1] == null) {
                    return UNABLE_TO_COMPARE;
                }
                if (!toValidate.after(betweenDates[0]) || !toValidate.before(betweenDates[1])) {
                    return "not between compared dates";
                }
                break;
        }
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