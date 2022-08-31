package marketfront.service;


import lombok.RequiredArgsConstructor;
import marketfront.entity.User;
import marketfront.exceptions.ResourceNotFoundException;
import marketfront.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public void updateUserBalance(Long id, Double itog){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id = " + id + " not found")));
        if (user.get().getBalance() >= itog){
            Double newBalance = user.get().getBalance() - itog;
            userRepository.updateUserBalance(newBalance, id);
        }
    }
}
