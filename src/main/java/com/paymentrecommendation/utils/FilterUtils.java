package com.paymentrecommendation.utils;

import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.models.Cart;
import com.paymentrecommendation.models.PaymentInstrument;
import com.paymentrecommendation.models.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.paymentrecommendation.enums.PaymentInstrumentType.UPI;

public class FilterUtils {

    public static List<PaymentInstrument> getAllowedPaymentInstruments(User user, Cart cart, LobFilters lobFilters) {
        // filter on banned instruments
        List<PaymentInstrumentType> bannedInstruments = lobFilters.bannedPaymentInstruments;
        List<PaymentInstrument> paymentInstruments =  user.getUserPaymentInstrument()
                .getPaymentInstruments().stream().filter(
                        paymentInstrument -> !bannedInstruments.contains(paymentInstrument.getPaymentInstrumentType())
                ).collect(Collectors.toList());
        // filter on user context
        if(user.getUserContext()!=null &&
                user.getUserContext().getDeviceContext()!=null
                && !user.getUserContext().getDeviceContext().isUpiEnabled()) {
            paymentInstruments = paymentInstruments.stream()
                    .filter(paymentInstrument -> paymentInstrument.getPaymentInstrumentType()!=UPI)
                    .collect(Collectors.toList());
        }
        // filter on txn limits
        Map<PaymentInstrumentType,Double> instrumentLimits = lobFilters.limits;
        paymentInstruments = paymentInstruments.stream().filter(
                paymentInstrument ->
                        cart.getCartDetail().getCartAmount()<=
                                instrumentLimits.getOrDefault(paymentInstrument.getPaymentInstrumentType(),0d)

        ).collect(Collectors.toList());
        return paymentInstruments;
    }


}
