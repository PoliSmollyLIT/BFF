package com.tinqin.academy.core;

import com.example.storage.api.operations.itemStorage.get.GetItemResponse;
import com.example.storage.restexport.StorageRestClient;
import com.example.zooostore.api.operations.item.getall.GetAllItemsRequest;
import com.example.zooostore.api.operations.item.getall.GetAllItemsResponse;
import com.example.zooostore.api.operations.item.getall.GetSingleItemResponse;
import com.example.zooostore.restexport.ZooStoreRestClient;
import com.tinqin.academy.api.item.getall.GetAllOperation;
import com.tinqin.academy.api.item.getall.GetAllRequest;
import com.tinqin.academy.api.item.getall.GetAllResponse;
import com.tinqin.academy.api.item.getall.GetAllSingleItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllItemsImpl implements GetAllOperation {
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
       for (GetSingleItemResponse item:itemsResponse.getItems() ) {
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
        }
        GetAllResponse response = GetAllResponse.builder()
                .items(items)
                .build();
        return response;
    }
}
