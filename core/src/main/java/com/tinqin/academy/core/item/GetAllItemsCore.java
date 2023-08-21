package com.tinqin.academy.core.item;

import com.example.storage.api.operations.itemStorage.get.GetItemResponse;
import com.example.storage.restexport.StorageRestClient;
import com.tinquinstore.zooostore.api.operations.item.getall.GetAllItemsRequest;
import com.tinquinstore.zooostore.api.operations.item.getall.GetAllItemsResponse;
import com.tinqin.academy.api.item.getall.GetAllOperation;
import com.tinqin.academy.api.item.getall.GetAllRequest;
import com.tinqin.academy.api.item.getall.GetAllResponse;
import com.tinqin.academy.api.item.getall.GetAllSingleItemResponse;
import com.tinquinstore.zooostore.restexport.ZooStoreRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetAllItemsCore implements GetAllOperation {
    private final ZooStoreRestClient zooStoreRestClient;
    private final StorageRestClient storageRestClient;
    @Override
    public GetAllResponse process(GetAllRequest request) {
        GetAllItemsRequest itemRequest = GetAllItemsRequest.builder()
                .tagTitle(request.getTagTitle())
                .page(request.getPage())
                .build();
        GetAllItemsResponse itemsResponse = zooStoreRestClient.getAllItemsByTag(itemRequest.getTagTitle(), itemRequest.getPage());
        Set<GetAllSingleItemResponse> items = new HashSet<>();

        itemsResponse.getItems().parallelStream()
                .forEach(item -> {
                    GetItemResponse getItemResponse = storageRestClient.getItemById(item.getId().toString());
                    GetAllSingleItemResponse singleItem = GetAllSingleItemResponse.builder()
                            .id(item.getId())
                            .title(item.getTitle())
                            .description(item.getDescription())
                            .vendor(item.getVendorId())
                            .archive(item.isArchive())
                            .price(getItemResponse.getPrice())
                            .quantity(getItemResponse.getQuantity())
                            .build();
                    items.add(singleItem);
                } );
        GetAllResponse response = GetAllResponse.builder()
                .items(items)
                .build();
        return response;
    }
}
