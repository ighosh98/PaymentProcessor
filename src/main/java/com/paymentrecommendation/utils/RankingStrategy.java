package com.paymentrecommendation.utils;

import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.models.PaymentInstrument;

import java.util.Comparator;
import java.util.Map;

public class RankingStrategy {
    Map<PaymentInstrumentType, Integer> preference;
    public RankingStrategy(Map<PaymentInstrumentType, Integer> preference) {
        this.preference = preference;
    }
    public final Comparator<PaymentInstrument> rankingComparator = (instrument1, instrument2) -> {
        if(instrument1.getPaymentInstrumentType()!=instrument2.getPaymentInstrumentType()) {
            return preference.getOrDefault(instrument1.getPaymentInstrumentType(),Integer.MAX_VALUE)
                    .compareTo(preference.getOrDefault(instrument2.getPaymentInstrumentType(),Integer.MAX_VALUE));
        }
        return instrument2.getRelevanceScore().compareTo(instrument1.getRelevanceScore());
    };
}
