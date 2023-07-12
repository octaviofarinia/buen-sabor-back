package com.tup.buensabor.services;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.tup.buensabor.dtos.detallepedido.AltaPedidoDetallePedidoDto;
import com.tup.buensabor.entities.ArticuloManufacturado;
import com.tup.buensabor.exceptions.ServicioException;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class MercadoPagoService {

    @Autowired
    private ArticuloManufacturadoServiceImpl articuloManufacturadoService;

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

    public Preference createPreference(List<AltaPedidoDetallePedidoDto> detallesPedido) throws ServicioException, MPException, MPApiException {
        List<PreferenceItemRequest> items = new ArrayList<>();

        for (AltaPedidoDetallePedidoDto detalleDto : detallesPedido) {
            PreferenceItemRequest itemRequest = getItemRequest(detalleDto);
            items.add(itemRequest);
        }

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .autoReturn("approved")
                .backUrls(
                        PreferenceBackUrlsRequest.builder()
                                .success(mpSuccessBackUrl)
                                .pending(mpPendingBackUrl)
                                .failure(mpFailureBackUrl)
                                .build()
                )
                .build();
        PreferenceClient client = new PreferenceClient();
        return client.create(preferenceRequest);
    }

    private PreferenceItemRequest getItemRequest(AltaPedidoDetallePedidoDto detalleDto) throws ServicioException {

        if (detalleDto.getCantidad() == null || detalleDto.getCantidad() <= 0) {
            throw new ServicioException("La cantidad a comprar debe ser mayor a cero.");
        }

        Optional<ArticuloManufacturado> optionalArticuloManufacturado = articuloManufacturadoService.findOptionalById(detalleDto.getIdArticuloManufacturado());
        if (optionalArticuloManufacturado.isEmpty()) {
            throw new ServicioException("No existe un articulo manufacturado con el id " + detalleDto.getIdArticuloManufacturado());
        }
        ArticuloManufacturado articuloManufacturado = optionalArticuloManufacturado.get();

        return PreferenceItemRequest.builder()
                .id(String.valueOf(articuloManufacturado.getId()))
                .title(articuloManufacturado.getDenominacion())
                .description(articuloManufacturado.getDescripcion())
                .pictureUrl(articuloManufacturado.getUrlImagen())
                .quantity(detalleDto.getCantidad())
                .currencyId("ARS")
                .unitPrice(articuloManufacturado.getPrecioVenta())
                .build();
    }

}
