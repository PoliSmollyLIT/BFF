package com.tinqin.academy.core.item;

import com.example.storage.restexport.StorageRestClient;
import com.example.zooostore.api.operations.item.get.GetItemResponse;
import com.example.zooostore.restexport.ZooStoreRestClient;
import com.tinqin.academy.api.item.get.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetItemInfoImpl implements GetItemOperation {
    private final ZooStoreRestClient zooStoreRestClient;
    private final StorageRestClient storageRestClient;

    @Override
    public GetItemInfoResponse process(GetItemRequest request) {
        GetItemResponse itemFromZooStore = zooStoreRestClient.getItemById(request.getItemID().toString());
        com.example.storage.api.operations.itemStorage.get.GetItemResponse itemFromStorage = storageRestClient.getItemById(request.getItemID().toString());
        GetItemVendorResponse vendor = GetItemVendorResponse.builder()
                .id(itemFromZooStore.getVendor().getId())
                .name(itemFromZooStore.getVendor().getName())
                .phone(itemFromZooStore.getVendor().getPhone())
                .build();
        GetItemInfoResponse response = GetItemInfoResponse.builder()
                .id(itemFromZooStore.getId())
                .title(itemFromZooStore.getTitle())
                .description(itemFromZooStore.getDescription())
                .vendor(vendor)
                .archive(itemFromZooStore.isArchive())
                .quantity(itemFromStorage.getQuantity())
                .price(itemFromStorage.getPrice())
                .build();
        return response;
    }
}
