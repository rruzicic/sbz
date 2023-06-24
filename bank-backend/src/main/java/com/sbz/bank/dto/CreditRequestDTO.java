package com.sbz.bank.dto;

import com.sbz.bank.model.EmploymentStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreditRequestDTO {
    Long clientId;

    double clientIncome;

    double moneySum;

    int rateNumber;

    EmploymentStatus clientEmploymentStatus;

    @Nullable
    Date clientContractStart;

    @Nullable
    Date clientContractEnd;

    Date creditEndMin;

    Date creditEndMax;
}
