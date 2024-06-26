package edu.icet.coursework.bo.custom.impl;

import edu.icet.coursework.bo.custom.OrderBo;
import edu.icet.coursework.dao.DaoFactory;
import edu.icet.coursework.dao.custom.OrderDao;
import edu.icet.coursework.dto.Order;
import edu.icet.coursework.entity.OrderEntity;
import edu.icet.coursework.util.DaoType;
import javafx.collections.ObservableList;


public class OrderBoImpl implements OrderBo {

    private OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);

    @Override
    public boolean addOrder(OrderEntity entity) {
        return orderDao.addOrder(entity);
    }

    @Override
    public ObservableList<Order> searchAllOrders() {
        return orderDao.searchAllOrders();
    }

}
