package net.cukrus.woValidationDemo.validation;

import java.util.Date;

/**
 * Validator for logic tied to Date comparison
 */
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

    /**
     * method for building a Validator for checking if one Date is before another
     * @param toValidate Date to check if its before
     * @param toCompare Date to compare to
     * @return DateValidator that checks if <i>toValidate</i> is before <i>toCompare</i>
     */
    public static DateValidator before(Date toValidate, Date toCompare) {
        return new DateValidator(DateValidationMode.BEFORE, toValidate, toCompare, null);
    }

    /**
     * method for building a Validator for checking if one Date is after another
     * @param toValidate Date to check if its after
     * @param toCompare Date to compare to
     * @return DateValidator that checks if <i>toValidate</i> is after <i>toCompare</i>
     */
    public static DateValidator after(Date toValidate, Date toCompare) {
        return new DateValidator(DateValidationMode.AFTER, toValidate, toCompare, null);
    }

    /**
     * method for building a Validator for checking if one Date is between two others
     * @param toValidate Date to check if its between
     * @param betweenDates Date array with expected length of 2 for comparison
     * @return DateValidator that checks if <i>toValidate</i> is between <i>betweenDates[0]</i> and <i>betweenDates[1]</i>
     */
    public static DateValidator between(Date toValidate, Date... betweenDates) {
        return new DateValidator(DateValidationMode.BETWEEN, toValidate, null, betweenDates);
    }

    public enum DateValidationMode {
        BEFORE, AFTER, BETWEEN;
    }
}