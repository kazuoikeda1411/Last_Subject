package com.raisetech.lastsubject.entity;

import java.sql.Timestamp;

public record MedicineInformation(int id, int userId, Timestamp visitDate, String pharmacy, String medicine) {
}
