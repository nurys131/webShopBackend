package pl.project.shop.order.service.mapper;

import pl.project.shop.order.model.Order;

public class OrderEmailMessageMapper {

    public static String createEmailMessage(Order order) {
        return "Twoje zamówienie o id: " + order.getId() +
                "\nWartość: " + order.getGrossValue() + " PLN " +
                "\n\n" +
                "\nPłatnośc: " + order.getPayment().getName() +
                (order.getPayment().getNote() != null ? "\n" + order.getPayment().getNote() : "") +
                "\n\nDziękujemy za zakupy w WebShop.";
    }
}
