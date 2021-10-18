package net.cukrus.woValidationDemo.validation;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

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

    public static <T> ValueInValidator inArray(T toValidate, T... vvArray){
        return new ValueInValidator<>(ValueInValidationMode.IN_ARRAY, toValidate, vvArray, null, null);
    }

    public static <T> ValueInValidator inCollection(T toValidate, Collection<T> vvCollection){
        return new ValueInValidator<>(ValueInValidationMode.IN_COLLECTION, toValidate, null, vvCollection, null);
    }

    public static <T> ValueInValidator inSupplier(T toValidate, Supplier<Collection<T>> vvSupplier){
        return new ValueInValidator<>(ValueInValidationMode.IN_SUPPLIER, toValidate, null, null, vvSupplier);
    }

    public enum ValueInValidationMode {
        IN_ARRAY,
        IN_COLLECTION,
        IN_SUPPLIER;
    }
}