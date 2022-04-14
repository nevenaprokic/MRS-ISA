package com.booking.ISAbackend.model;

public enum OwnerCategory {
    REGULAR,
    SILVER,
    GOLD;

    public static OwnerCategory fromInteger(int x) {
        switch(x) {
            case 1:
                return REGULAR;
            case 2:
                return SILVER;
            default:
                return REGULAR;
        }

    }
}
