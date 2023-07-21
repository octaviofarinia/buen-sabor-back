package com.tup.buensabor.enums;

public enum EstadoPedido {
    PENDIENTE_PAGO, //Pedido en efectivo que todavia no fue pagado
    PAGADO, //Pedido ya pagado tanto por MP o por EFECTIVO
    PREPARACION, //Pedido en preparacion en la cocina
    EN_CAMINO, //Pedido en delivery luego de ser cocinado
    RECHAZADO, //Pedido que fue rechazado, no se genero factura del mismo
    CANCELADO, //Pedido que fue cancelado y se emitio una nota de credito en su lugar
    COMPLETADO, //Pedido que fue pagado y recibido por el cliente
}
