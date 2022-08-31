package marketfront.service;


import lombok.RequiredArgsConstructor;
import marketfront.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;


}
