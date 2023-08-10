package com.tinqin.academy.api.invoice;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceSingleItem {
    private String itemTitle;
    private Integer quantity;
    private Double price;

}
