package com.atlassian.practice.inventory;

public record ReservationResult(boolean successful, String message, int remainingQuantity) {
}
