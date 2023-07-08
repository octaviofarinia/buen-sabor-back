package com.tup.buensabor.services;

import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mercadopago.MercadoPagoConfig;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class MercadoPagoService {

    @Value("${mercadopago.access_token}")
    private String mpAccessToken;

    @Value("${mercadopago.back_url.success}")
    private String mpSuccessBackUrl;

    @Value("${mercadopago.back_url.pending}")
    private String mpPendingBackUrl;

    @Value("${mercadopago.back_url.failure}")
    private String mpFailureBackUrl;

    @PostConstruct
    public void initMPConfig() {
        MercadoPagoConfig.setAccessToken(mpAccessToken);
    }

    public Preference createPreference() {
        try {
            PreferenceItemRequest itemRequest =
                    PreferenceItemRequest.builder()
                            .id("1234")
                            .title("Games")
                            .description("PS5")
                            .pictureUrl("http://picture.com/PS5")
                            .categoryId("games")
                            .quantity(2)
                            .currencyId("BRL")
                            .unitPrice(new BigDecimal("4000"))
                            .build();
            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(
                            PreferenceBackUrlsRequest.builder()
                                    .success(mpSuccessBackUrl)
                                    .pending(mpPendingBackUrl)
                                    .failure(mpFailureBackUrl)
                                    .build()
                    )
                    .build();
            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);
            return preference;
        } catch (MPException | MPApiException e) {
            log.error(e);
            return null;
        }
    }

}
