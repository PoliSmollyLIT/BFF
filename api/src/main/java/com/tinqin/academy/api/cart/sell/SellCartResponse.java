package com.tinqin.academy.api.cart.sell;

import com.tinqin.academy.api.base.OperationResponse;
import lombok.*;

import java.io.ByteArrayInputStream;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellCartResponse implements OperationResponse {
    private ByteArrayInputStream pdfFile;
    private String filename;
}
