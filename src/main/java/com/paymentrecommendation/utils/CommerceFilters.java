package com.paymentrecommendation.utils;

import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.enums.PaymentInstrumentType;

import java.util.HashMap;
import java.util.List;

public class CommerceFilters extends LobFilters {

    public CommerceFilters(LineOfBusiness lineOfBusiness) {
        super(lineOfBusiness);
        this.limits = new HashMap<>() {{
            put(PaymentInstrumentType.UPI,100000d);
            put(PaymentInstrumentType.DEBIT_CARD,200000d);
            put(PaymentInstrumentType.CREDIT_CARD,250000d);
        }};
        this.preference = new HashMap<>(){{
            put(PaymentInstrumentType.CREDIT_CARD,1);
            put(PaymentInstrumentType.UPI,2);
            put(PaymentInstrumentType.DEBIT_CARD,3);
        }};
        this.bannedPaymentInstruments = List.of(PaymentInstrumentType.NETBANKING);
    }
}
