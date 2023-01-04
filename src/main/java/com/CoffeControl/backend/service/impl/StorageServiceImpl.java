package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.StorageDetailedDto;
import com.CoffeControl.backend.dto.StorageDto;
import com.CoffeControl.backend.dto.StorageLessDetailedDto;
import com.CoffeControl.backend.form.ProductPostForm;
import com.CoffeControl.backend.form.ProductUpdateForm;
import com.CoffeControl.backend.form.StorageUpdateForm;
import com.CoffeControl.backend.form.StorageUpdateQuantityForm;
import com.CoffeControl.backend.model.Product;
import com.CoffeControl.backend.model.Storage;
import com.CoffeControl.backend.repository.ProductRepository;
import com.CoffeControl.backend.repository.SolicitationRepository;
import com.CoffeControl.backend.repository.StorageRepositoy;
import com.CoffeControl.backend.repository.UserRepository;
import com.CoffeControl.backend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepositoy storageRepositoy;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SolicitationRepository solicitationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<StorageDto> list(Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<Storage> storage = storageRepositoy.findAll(pagination);
        return StorageDto.convert(storage);
    }

    @Override
    public Storage insertNewProduct(Product p, ProductPostForm form) {
        Storage storage =new Storage(p,form);
        storageRepositoy.save(storage);
        return storage;
    }

    @Override
    public StorageDto updateQuantity(Integer id, StorageUpdateQuantityForm form) throws Exception {
        Storage storage=storageRepositoy.findById(id).orElseThrow(() -> new Exception("storage id not found"));
        storage.setCurrentAmount(form.getQtd());
        storageRepositoy.save(storage);
        return new StorageDto(storage);
    }

    @Override
    public StorageDetailedDto getSpecificStorage(Integer id) throws Exception {
        Storage storage=storageRepositoy.findById(id).orElseThrow(() -> new Exception("id doesnt represent storage"));
        return new StorageDetailedDto(storage);
    }

    @Override
    public StorageDetailedDto getByProductId(Integer product_id) throws Exception {
        Optional<Storage> optionalStorage= Optional.of(storageRepositoy.findByProductId(product_id).get(0));
        Storage storage =optionalStorage.orElseThrow(() ->  new Exception("no product found"));
        System.out.println("storage :");
        System.out.println("id :" +storage.getId());
        System.out.println("current amount :" +storage.getCurrentAmount());
        System.out.println("min amount :" +storage.getMinAmount());
        System.out.println("product_id : " +storage.getProduct().getId());
        System.out.println("product_name: :" +storage.getProduct().getName());
        System.out.println("product_minUseramount : " +storage.getProduct().getMinUserAmount());
        return new StorageDetailedDto(storage);
    }

    @Override
    public StorageDetailedDto updateProduct(Integer id, ProductUpdateForm form) throws Exception{
        Storage storage =storageRepositoy.findById(id).orElseThrow(() -> new  Exception("storage not found"));
        Product product =productRepository.findById(storage.getProduct().getId()).orElseThrow(() -> new Exception("product not found"));
        product.setName(form.getName() != null ? form.getName() :  product.getName());
        product.setDescription(form.getDescription() != null ? form.getDescription() :  product.getDescription());
        product.setEnabled(form.getEnabled() != null ? form.getEnabled() :  product.getEnabled());
        product.setMinUserAmount(form.getMinUserAmount() != null ? form.getMinUserAmount() :  product.getMinUserAmount());
        productRepository.save(product);
        storageRepositoy.save(storage);
        return new StorageDetailedDto(storage);
    }

    @Override
    public StorageDto deleteStorage(Integer id) throws Exception {
        Storage storage= storageRepositoy.findById(id).orElseThrow(() -> new Exception("no storage found"));
        storageRepositoy.deleteById(id);
        productRepository.deleteById(storage.getProduct().getId());

        return new StorageDto(storage);
    }

    @Override
    public StorageDetailedDto updateStorage(Integer id, StorageUpdateForm form) throws Exception {
        Storage storage=storageRepositoy.findById(id).orElseThrow(() -> new Exception("storage not found"));
        Product product =productRepository.findById(storage.getProduct().getId()).orElseThrow(() -> new Exception("product not found"));
        product.setName(form.getName() != null ? form.getName() :  product.getName());
        product.setDescription(form.getDescription() != null ? form.getDescription() :  product.getDescription());
        product.setEnabled(form.getEnabled() != null ? form.getEnabled() :  product.getEnabled());
        product.setMinUserAmount(form.getMinUserAmount() != null ? form.getMinUserAmount() :  product.getMinUserAmount());
        storage.setCurrentAmount(form.getCurrentAmount() != null ? form.getCurrentAmount() : storage.getCurrentAmount());
        storage.setMinAmount(form.getMinAmount() != null ? form.getMinAmount() : storage.getMinAmount());
        productRepository.save(product);
        storageRepositoy.save(storage);
        return new StorageDetailedDto(storage);
    }

    @Override
    public Page<StorageLessDetailedDto> startsWithProductName(String name, Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<Storage> storage= storageRepositoy.findByProductName(name,pagination);
        return StorageLessDetailedDto.convert(storage);
    }

}
