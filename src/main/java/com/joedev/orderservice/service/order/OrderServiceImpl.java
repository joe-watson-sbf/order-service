package com.joedev.orderservice.service.order;

import com.joedev.orderservice.dto.*;
import com.joedev.orderservice.entity.Client;
import com.joedev.orderservice.entity.Order;
import com.joedev.orderservice.entity.OrderDetails;
import com.joedev.orderservice.entity.Product;
import com.joedev.orderservice.handler.BusinessException;
import com.joedev.orderservice.mapper.DataMapper;
import com.joedev.orderservice.repository.ClientRepository;
import com.joedev.orderservice.repository.OrderDetailsRepository;
import com.joedev.orderservice.repository.OrderRepository;
import com.joedev.orderservice.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.joedev.orderservice.utils.ServiceUtils.validateOrder;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ClientRepository clientRepository;
    private final DataMapper mapper;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderDetailsRepository orderDetailsRepository,
                            ClientRepository clientRepository, DataMapper mapper,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.clientRepository = clientRepository;
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    @Override
    public void createOrder(OrderCommand command) {
        validateOrder(command);
        Client client = clientRepository.save(mapper.dtoToEntity(command.clientDto()));
        Order order = new Order(client.getId(), getDetails(command.orderDetailsDto()));
        orderRepository.save(order);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return mapper.entityToDto(
                orderRepository
                        .findById(orderId)
                        .orElseThrow(()->
                                new BusinessException("No order found with id: " + orderId,
                                        HttpStatus.NOT_FOUND))
        );
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> {
                    OrderDto orderDto= mapper.entityToDto(order);
                    List<OrderDetailsDto> detailsDto =  order.getOrderDetails()
                            .stream()
                            .map(orderDetails -> mapper.entityToDto(orderDetails))
                            .toList();
                    orderDto.setOrderDetailsDto(detailsDto);
                    return orderDto;
                })
                .toList();
    }


    private List<OrderDetails> getDetails(List<OrderDetailsDto> orderDetailsDto){
        return orderDetailsDto.stream()
                .map(details ->
                        checkProductAvailability(
                                details.getProductDto().getId(),
                                details.getQuantity())
                ).toList();
    }


    private OrderDetails checkProductAvailability(Long productId, Integer quantity){
        Product product = getProduct(productId);
        validateOrderDetailsQuantity(product, quantity);
        return generateOrderDetails(product, quantity);
    }

    private void validateOrderDetailsQuantity(Product product, Integer quantity){
        if(product.getQuantity() < quantity || Boolean.TRUE.equals(!product.getActive())){
            throw new BusinessException("We don't have enough "+ product.getName() +" in stock!",
                    HttpStatus.BAD_REQUEST);
        }
    }


    private OrderDetails generateOrderDetails(Product product, Integer quantity){
        return orderDetailsRepository.save(new OrderDetails(null, product, quantity));
    }

    private Product getProduct(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(()->
                        new BusinessException("Product with id " + productId + " not available",
                        HttpStatus.FORBIDDEN));
    }


}
