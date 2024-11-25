package com.zerobase.model;

import com.zerobase.model.type.PGMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestConfirmPayDeposit {
    Long depositId;
    PGMethod pgMethod;
}
