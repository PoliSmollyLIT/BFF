package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.api.item.get.GetItemRequest;
import com.tinqin.academy.api.item.getall.GetAllRequest;
import com.tinqin.academy.core.item.GetAllItemsCore;
import com.tinqin.academy.core.item.GetItemInfoCore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/item")
@Tag(name = "Storage", description = "API for working with storage with items")
@RequiredArgsConstructor
public class ItemsController {
    private final GetItemInfoCore getItemInfo;
    private final GetAllItemsCore getAllItems;

    @GetMapping("/{id}")
    @Operation(summary = "Get info for Item in Storage", description = "Gets info for Item in Storage")
    ResponseEntity getItemInfoById(@PathVariable UUID id) {
        GetItemRequest itemRequest = GetItemRequest.builder()
                .itemID(id)
                .build();
        return ResponseEntity.ok(getItemInfo.process(itemRequest));
    }

    @GetMapping("/tag/{tag}")//?page={page}
    ResponseEntity getAllItemsByTagTitle(@PathVariable String tag, @RequestParam Integer page){
        GetAllRequest request = GetAllRequest.builder()
                .tagTitle(tag)
                .page(page)
                .build();
        return ResponseEntity.ok(getAllItems.process(request));
    }
}
