package com.paymentrecommendation.service;

import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.models.Cart;
import com.paymentrecommendation.models.PaymentInstrument;
import com.paymentrecommendation.models.User;
import com.paymentrecommendation.utils.*;

import java.util.*;

import static com.paymentrecommendation.utils.FilterUtils.getAllowedPaymentInstruments;

public class PaymentRecommenderImpl implements PaymentRecommender {
    @Override
    public List<PaymentInstrument> recommendPaymentInstruments(User user, Cart cart) {
        if(user==null || cart==null) {
            return new ArrayList<>();
        }
        if(cart.getLineOfBusiness()==null) {
            throw new RuntimeException("The line of business is not supported");
        }
        LobFilters lobFilter = getLobFilter(cart.getLineOfBusiness());
        if(lobFilter==null) {
            return new ArrayList<>();
        }
        // remove not allowed methods
        List<PaymentInstrument> paymentInstruments = getAllowedPaymentInstruments(user,cart, lobFilter);
        Map<PaymentInstrumentType, Integer> preference = lobFilter.preference;

        RankingStrategy rankingStrategy = new RankingStrategy(preference);
        // rank payment instruments
         paymentInstruments.sort(rankingStrategy.rankingComparator);
         return paymentInstruments;
    }

    private LobFilters getLobFilter(LineOfBusiness lineOfBusiness) {
        switch(lineOfBusiness) {
            case CREDIT_CARD_BILL_PAYMENT:
                return new CreditCardFilters(lineOfBusiness);
            case COMMERCE:
                return new CommerceFilters(lineOfBusiness);
            case INVESTMENT:
                return new InvestmentFilters(lineOfBusiness);
            default:
                return null;
        }
    }

}
