package com.paymentrecommendation.utils;

import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.enums.PaymentInstrumentType;

import java.util.HashMap;
import java.util.List;

public class CreditCardFilters extends LobFilters {



    public CreditCardFilters(LineOfBusiness lineOfBusiness) {
        super(lineOfBusiness);
         this.limits = new HashMap<>() {{
            put(PaymentInstrumentType.UPI,200000d);
            put(PaymentInstrumentType.DEBIT_CARD,200000d);
            put(PaymentInstrumentType.NETBANKING,200000d);

        }};
        this.preference = new HashMap<>(){{
            put(PaymentInstrumentType.UPI,1);
            put(PaymentInstrumentType.NETBANKING,2);
            put(PaymentInstrumentType.DEBIT_CARD,3);
        }};
        this.bannedPaymentInstruments = List.of(PaymentInstrumentType.CREDIT_CARD);
    }
}
