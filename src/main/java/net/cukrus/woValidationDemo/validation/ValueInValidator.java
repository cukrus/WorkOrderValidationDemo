package net.cukrus.woValidationDemo.validation;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Validator for checking if a value is in valid values
 * @param <T> the type of validation object
 */
public class ValueInValidator<T> extends Validator {
    private static final String VALUE_IS_NULL = "unable to compare, value is null";
    private static final String MISSING_COMPARABLES = "unable to compare, missing comparable values";
    private static final String NOT_VALID = "is not in valid values";

    private final ValueInValidationMode mode;
    private final T toValidate;
    private final T[] vvArray;
    private final Collection<T> vvCollection;
    private final Supplier<Collection<T>> vvSupplier;

    private ValueInValidator(ValueInValidationMode mode, T toValidate, T[] vvArray, Collection<T> vvCollection, Supplier<Collection<T>> vvSupplier) {
        this.mode = mode;
        this.toValidate = toValidate;
        this.vvArray = vvArray;
        this.vvCollection = vvCollection;
        this.vvSupplier = vvSupplier;
    }

    @Override
    protected String internalValidate() {
        if (toValidate == null) {
            return VALUE_IS_NULL;
        }
        switch (mode) {
            case IN_ARRAY:
                if (vvArray == null || vvArray.length == 0) {
                    return MISSING_COMPARABLES;
                }
                if (!Arrays.stream(vvArray).anyMatch(val -> toValidate.equals(val))) {
                    return NOT_VALID;
                }
                break;
            case IN_COLLECTION:
                if (CollectionUtils.isEmpty(vvCollection)) {
                    return MISSING_COMPARABLES;
                }
                if (!vvCollection.stream().anyMatch(val -> toValidate.equals(val))) {
                    return NOT_VALID;
                }
                break;
            case IN_SUPPLIER:
                Collection<T> validValues;
                if (vvSupplier == null) {
                    return MISSING_COMPARABLES;
                } else {
                    validValues = vvSupplier.get();
                    if (CollectionUtils.isEmpty(validValues)) {
                        return MISSING_COMPARABLES;
                    }
                }
                if (!validValues.stream().anyMatch(val -> toValidate.equals(val))) {
                    return NOT_VALID;
                }
                break;
        }
        return null;
    }

    /**
     * method for building a Validator for checking if a value is in valid values array
     * @param toValidate value to check
     * @param vvArray valid value array to check in
     * @param <T> the type of validation object
     * @return ValueInValidator that checks if <i>toValidate</i> is in <i>vvArray</i>
     */
    public static <T> ValueInValidator inArray(T toValidate, T... vvArray){
        return new ValueInValidator<>(ValueInValidationMode.IN_ARRAY, toValidate, vvArray, null, null);
    }

    /**
     * method for building a Validator for checking if a value is in valid values collection
     * @param toValidate value to check
     * @param vvCollection valid value collection to check in
     * @param <T> the type of validation object
     * @return ValueInValidator that checks if <i>toValidate</i> is in <i>vvCollection</i>
     */
    public static <T> ValueInValidator inCollection(T toValidate, Collection<T> vvCollection){
        return new ValueInValidator<>(ValueInValidationMode.IN_COLLECTION, toValidate, null, vvCollection, null);
    }

    /**
     * method for building a Validator for checking if a value is in valid values collection supplier
     * @param toValidate value to check
     * @param vvSupplier valid value collection supplier to check in
     * @param <T> the type of validation object
     * @return ValueInValidator that checks if <i>toValidate</i> is in collection supplied by <i>vvSupplier</i>
     */
    public static <T> ValueInValidator inSupplier(T toValidate, Supplier<Collection<T>> vvSupplier){
        return new ValueInValidator<>(ValueInValidationMode.IN_SUPPLIER, toValidate, null, null, vvSupplier);
    }

    public enum ValueInValidationMode {
        IN_ARRAY,
        IN_COLLECTION,
        IN_SUPPLIER;
    }
}