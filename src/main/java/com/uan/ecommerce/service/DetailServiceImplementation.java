package com.uan.ecommerce.service;

import com.uan.ecommerce.model.Detail;
import com.uan.ecommerce.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailServiceImplementation implements DetailService{
    @Autowired
    private DetailRepository detailRepository;

    @Override
    public Detail save(Detail detail) {
        return detailRepository.save(detail);
    }
}
