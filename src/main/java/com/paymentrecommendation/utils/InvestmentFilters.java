package com.paymentrecommendation.utils;

import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.enums.PaymentInstrumentType;

import java.util.HashMap;
import java.util.List;

public class InvestmentFilters extends LobFilters{
    public InvestmentFilters(LineOfBusiness lineOfBusiness) {
        super(lineOfBusiness);
        this.limits = new HashMap<>() {{
            put(PaymentInstrumentType.UPI,100000d);
            put(PaymentInstrumentType.DEBIT_CARD,150000d);
            put(PaymentInstrumentType.NETBANKING,150000d);
        }};
        this.preference = new HashMap<>(){{
            put(PaymentInstrumentType.UPI,1);
            put(PaymentInstrumentType.NETBANKING,2);
            put(PaymentInstrumentType.DEBIT_CARD,3);
        }};
        this.bannedPaymentInstruments = List.of(PaymentInstrumentType.CREDIT_CARD);;
    }
}
