package com.paymentrecommendation.utils;

import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.enums.PaymentInstrumentType;

import java.util.List;
import java.util.Map;

public class LobFilters {
    public LineOfBusiness lineOfBusiness;

    public Map<PaymentInstrumentType,Double> limits;
    public List<PaymentInstrumentType> bannedPaymentInstruments;
    public Map<PaymentInstrumentType, Integer> preference;

    public LobFilters(LineOfBusiness lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }
}
