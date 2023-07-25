package com.tinqin.academy.api.item.get;

import com.tinqin.academy.api.base.OperationResponse;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetItemInfoResponse implements OperationResponse {
    private UUID id;
    private String title;
    private String description;
    private GetItemVendorResponse vendor;
    private boolean archive;
    private int quantity;
    private double price;
}
