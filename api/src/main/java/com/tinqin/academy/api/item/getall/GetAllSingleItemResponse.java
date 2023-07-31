package com.tinqin.academy.api.item.getall;

import com.tinqin.academy.api.base.OperationResponse;
import com.tinqin.academy.api.item.get.GetItemVendorResponse;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllSingleItemResponse {
        private UUID id;
        private String title;
        private String description;
        private UUID vendor;
        private Set<GetAllTagResponse> tags;
        private boolean archive;
        private int quantity;
        private double price;
}
