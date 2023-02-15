package com.raiseTech.lastSubject.entity;

import java.sql.Timestamp;

public record MedicineInformation(int id, int userId, Timestamp visitDate, String pharmacy, String medicine) {
}
