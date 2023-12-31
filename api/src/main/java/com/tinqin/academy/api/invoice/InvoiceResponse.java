package com.tinqin.academy.api.invoice;

import com.tinqin.academy.api.base.OperationResponse;
import lombok.*;

import java.io.ByteArrayInputStream;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse implements OperationResponse {
    private ByteArrayInputStream response;
}
