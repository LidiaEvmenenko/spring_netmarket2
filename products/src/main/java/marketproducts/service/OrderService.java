package marketproducts.service;


import lombok.RequiredArgsConstructor;
import marketproducts.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;


}
