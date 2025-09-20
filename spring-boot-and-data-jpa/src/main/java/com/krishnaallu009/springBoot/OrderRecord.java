package com.krishnaallu009.springBoot;

public record OrderRecord(
        int id,
        String name,
        double price,
        int quantity
) {
}
