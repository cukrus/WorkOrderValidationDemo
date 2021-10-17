package net.cukrus.woValidationDemo.validation;

import java.util.Collection;
import java.util.function.Supplier;

public class ValueInValidator<T> extends Validator {
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
        //TODO implement;
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