package com.tup.buensabor.enums;

import java.util.EnumSet;
import java.util.Set;

public enum EstadoPedido {
    PENDIENTE_PAGO { //Pedido en efectivo que todavia no fue pagado
        @Override
        public boolean isValidNextState(EstadoPedido newEstado, FormaPago formaPago) {
            Set<EstadoPedido> validStates = switch (formaPago) {
                case EFECTIVO -> EnumSet.of(EstadoPedido.PREPARACION, EstadoPedido.CANCELADO);
                case MERCADO_PAGO -> EnumSet.of(EstadoPedido.PAGADO, EstadoPedido.CANCELADO);
            };

            return validStates.contains(newEstado);
        }
    },

    PAGADO { //Pedido ya pagado tanto por MP o por EFECTIVO
        @Override
        public boolean isValidNextState(EstadoPedido newEstado, FormaPago formaPago) {
            Set<EstadoPedido> validStates =  switch (formaPago) {
                case EFECTIVO -> EnumSet.of(EstadoPedido.COMPLETADO);
                case MERCADO_PAGO -> EnumSet.of(EstadoPedido.PREPARACION, EstadoPedido.NOTA_CREDITO);
            };

            return validStates.contains(newEstado);
        }
    },

    PREPARACION { //Pedido en preparacion en la cocina
        @Override
        public boolean isValidNextState(EstadoPedido newEstado, FormaPago formaPago) {
            Set<EstadoPedido> validStates =  switch (formaPago) {
                case EFECTIVO -> EnumSet.of(EstadoPedido.CANCELADO, EstadoPedido.PENDIENTE_ENTREGA);
                case MERCADO_PAGO -> EnumSet.of(EstadoPedido.NOTA_CREDITO, EstadoPedido.PENDIENTE_ENTREGA);
            };

            return validStates.contains(newEstado);
        }
    },

    PENDIENTE_ENTREGA { //Pedido cocinado pendiente de ser enviado
        @Override
        public boolean isValidNextState(EstadoPedido newEstado, FormaPago formaPago) {
            Set<EstadoPedido> validStates = switch (formaPago) {
                case EFECTIVO -> EnumSet.of(EstadoPedido.CANCELADO, EstadoPedido.EN_CAMINO, EstadoPedido.PAGADO, EstadoPedido.COMPLETADO);
                case MERCADO_PAGO -> EnumSet.of(EstadoPedido.NOTA_CREDITO, EstadoPedido.EN_CAMINO, EstadoPedido.COMPLETADO);
            };

            return validStates.contains(newEstado);
        }
    },

    EN_CAMINO { //Pedido en delivery
        @Override
        public boolean isValidNextState(EstadoPedido newEstado, FormaPago formaPago) {
            Set<EstadoPedido> validStates = switch (formaPago) {
                case EFECTIVO -> EnumSet.of(EstadoPedido.CANCELADO, EstadoPedido.PENDIENTE_ENTREGA, EstadoPedido.PAGADO, EstadoPedido.COMPLETADO);
                case MERCADO_PAGO -> EnumSet.of(EstadoPedido.NOTA_CREDITO, EstadoPedido.PENDIENTE_ENTREGA, EstadoPedido.COMPLETADO);
            };

            return validStates.contains(newEstado);
        }
    },

    CANCELADO { //Pedido que fue rechazado, no se genero factura del mismo ni se registro pago
        @Override
        public boolean isValidNextState(EstadoPedido newEstado, FormaPago formaPago) {
            return false;
        }
    },

    NOTA_CREDITO { //Pedido que fue cancelado y se emitio una nota de credito en su lugar ya que fue pagado
        @Override
        public boolean isValidNextState(EstadoPedido newEstado, FormaPago formaPago) {
            return false;
        }
    },

    COMPLETADO { //Pedido que fue pagado y recibido por el cliente
        @Override
        public boolean isValidNextState(EstadoPedido newEstado, FormaPago formaPago) {
            Set<EstadoPedido> validStates = switch (formaPago) {
                case EFECTIVO, MERCADO_PAGO -> EnumSet.of(EstadoPedido.NOTA_CREDITO);
            };

            return validStates.contains(newEstado);
        }
    };

    public abstract boolean isValidNextState(EstadoPedido newEstado, FormaPago formaPago);
}
