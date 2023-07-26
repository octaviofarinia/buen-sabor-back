package com.tup.buensabor.services;

import com.tup.buensabor.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendAltaPedidoEmail(String to, Pedido pedido) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Argentina/Buenos_Aires")));
        String strHoraEstimadaFinalizacion = dateFormat.format(pedido.getHoraEstimadaFinalizacion());
        this.sendEmail(
                to,
                "Alta pedido confirmada Buen Sabor",
                "¡Hola " + pedido.getCliente().getUsuario().getUsername() + "!\n\n" +
                        "Tu pedido ha sido confirmado en Buen Sabor.\n" +
                        "Detalles del pedido:\n" +
                        "Hora estimada de finalización: " + strHoraEstimadaFinalizacion + "\n" +
                        "Total: $" + pedido.getTotal() + "\n" +
                        "Estado del pedido: " + pedido.getEstado() + "\n" +
                        "Tipo de envío: " + pedido.getTipoEnvio() + "\n" +
                        "Forma de pago: " + pedido.getFormaPago() + "\n" +
                        "\nGracias por elegir Buen Sabor. ¡Esperamos que disfrutes tu pedido!\n" +
                        "Atentamente,\n" +
                        "El equipo de Buen Sabor");
    }

}
