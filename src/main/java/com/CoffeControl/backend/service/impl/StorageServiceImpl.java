package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.StorageDetailedDto;
import com.CoffeControl.backend.dto.StorageDto;
import com.CoffeControl.backend.dto.StorageLessDetailedDto;
import com.CoffeControl.backend.exception.GenericException;
import com.CoffeControl.backend.form.ProductPostForm;
import com.CoffeControl.backend.form.ProductUpdateForm;
import com.CoffeControl.backend.form.StorageUpdateForm;
import com.CoffeControl.backend.form.StorageUpdateQuantityForm;
import com.CoffeControl.backend.model.Product;
import com.CoffeControl.backend.model.Storage;
import com.CoffeControl.backend.repository.ProductRepository;
import com.CoffeControl.backend.repository.SolicitationRepository;
import com.CoffeControl.backend.repository.StorageRepository;
import com.CoffeControl.backend.repository.UserRepository;
import com.CoffeControl.backend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SolicitationRepository solicitationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<StorageDto> list(Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<Storage> storage = storageRepository.findAll(pagination);
        return StorageDto.convert(storage);
    }

    @Override
    public Storage insertNewProduct(Product p, ProductPostForm form) {
        Storage storage =new Storage(p,form);
        storageRepository.save(storage);
        return storage;
    }

    @Override
    public StorageDto updateQuantity(Integer id, StorageUpdateQuantityForm form) throws Exception {
        Storage storage= storageRepository.findById(id).orElseThrow(() -> new GenericException("STORAGE NOT FOUND!", "Storage with id " + id + " not found during the update of quantity in storage", HttpStatus.BAD_REQUEST));
        storage.setCurrentAmount(form.getQtd());
        storageRepository.save(storage);
        return new StorageDto(storage);
    }

    @Override
    public StorageDetailedDto getSpecificStorage(Integer id) throws Exception {
        Storage storage= storageRepository.findById(id).orElseThrow(() -> new GenericException("STORAGE NOT FOUND!", "Storage with id " + id + " not found during the request of specific storage", HttpStatus.BAD_REQUEST));
        return new StorageDetailedDto(storage);
    }

    @Override
    public StorageDetailedDto getByProductId(Integer product_id) throws Exception {
        Optional<Storage> optionalStorage= Optional.of(storageRepository.findByProductId(product_id).get(0));
        Storage storage =optionalStorage.orElseThrow(() ->  new GenericException("PRODUCT NOT FOUND!", "Product with id " + product_id + " not found during the request of storage using id product", HttpStatus.BAD_REQUEST));
        return new StorageDetailedDto(storage);
    }

    @Override
    public StorageDetailedDto updateProduct(Integer id, ProductUpdateForm form) throws Exception{
        Storage storage = storageRepository.findById(id).orElseThrow(() -> new GenericException("STORAGE NOT FOUND!", "Storage with id " + id + " not found during the update of product", HttpStatus.BAD_REQUEST));
        Product product =productRepository.findById(storage.getProduct().getId()).orElseThrow(() -> new GenericException("PRODUCT NOT FOUND!", "Product with id " + storage.getProduct().getId() + " not found during the update of product", HttpStatus.BAD_REQUEST));
        product.setName(form.getName() != null ? form.getName() :  product.getName());
        product.setDescription(form.getDescription() != null ? form.getDescription() :  product.getDescription());
        product.setEnabled(form.getEnabled() != null ? form.getEnabled() :  product.getEnabled());
        product.setMinUserAmount(form.getMinUserAmount() != null ? form.getMinUserAmount() :  product.getMinUserAmount());
        productRepository.save(product);
        storageRepository.save(storage);
        return new StorageDetailedDto(storage);
    }

    @Override
    public StorageDto deleteStorage(Integer id) throws Exception {
        Storage storage= storageRepository.findById(id).orElseThrow(() -> new GenericException("STORAGE NOT FOUND!", "Storage with id " + id + " not found during the delete of one!", HttpStatus.BAD_REQUEST));
        storageRepository.deleteById(id);
        productRepository.deleteById(storage.getProduct().getId());
        return new StorageDto(storage);
    }

    @Override
    public StorageDetailedDto updateStorage(Integer id, StorageUpdateForm form) throws Exception {
        Storage storage= storageRepository.findById(id).orElseThrow(() -> new GenericException("STORAGE NOT FOUND!", "Storage with id " + id + " not found during the update of one!", HttpStatus.BAD_REQUEST));
        Product product =productRepository.findById(storage.getProduct().getId()).orElseThrow(() -> new GenericException("PRODUCT NOT FOUND!", "Product with id " + storage.getProduct().getId() + " not found during the update of storage!", HttpStatus.BAD_REQUEST));
        product.setName(form.getName() != null ? form.getName() :  product.getName());
        product.setDescription(form.getDescription() != null ? form.getDescription() :  product.getDescription());
        product.setEnabled(form.getEnabled() != null ? form.getEnabled() :  product.getEnabled());
        product.setMinUserAmount(form.getMinUserAmount() != null ? form.getMinUserAmount() :  product.getMinUserAmount());
        storage.setCurrentAmount(form.getCurrentAmount() != null ? form.getCurrentAmount() : storage.getCurrentAmount());
        storage.setMinAmount(form.getMinAmount() != null ? form.getMinAmount() : storage.getMinAmount());
        productRepository.save(product);
        storageRepository.save(storage);
        return new StorageDetailedDto(storage);
    }

    @Override
    public Page<StorageLessDetailedDto> startsWithProductName(String name, Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<Storage> storage= storageRepository.findByProductName(name,pagination);
        return StorageLessDetailedDto.convert(storage);
    }

}
