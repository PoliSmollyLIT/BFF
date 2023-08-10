package com.tinqin.academy.api.invoice;

import com.tinqin.academy.api.base.OperationRequest;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest implements OperationRequest {
    private String userFistName;
    private String userLastName;
    private String phone;
    private Date dateMade;
    private List<InvoiceSingleItem> items;
    private Double price;
    private Double discount;
    private Double finalPrice;
    private UUID orderId;
}
