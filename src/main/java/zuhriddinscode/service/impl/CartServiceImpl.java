package zuhriddinscode.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zuhriddinscode.domain.model.Perfume;
import zuhriddinscode.domain.model.UserEntity;
import zuhriddinscode.repository.PerfumeRepository;
import zuhriddinscode.service.CartService;
import zuhriddinscode.service.UserService2;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserService2 userService;
    private final PerfumeRepository perfumeRepository;

    @Override
    public List<Perfume> getPerfumesInCart() {
        UserEntity user = userService.getAuthenticatedUser();
        return user.getPerfumeList();
    }

    @Override
    @Transactional
    public void addPerfumeToCart(Long perfumeId) {
        UserEntity user = userService.getAuthenticatedUser();
        Perfume perfume = perfumeRepository.getOne(perfumeId);
        user.getPerfumeList().add(perfume);
    }

    @Override
    @Transactional
    public void removePerfumeFromCart(Long perfumeId) {
        UserEntity user = userService.getAuthenticatedUser();
        Perfume perfume = perfumeRepository.getOne(perfumeId);
        user.getPerfumeList().remove(perfume);
    }

}
