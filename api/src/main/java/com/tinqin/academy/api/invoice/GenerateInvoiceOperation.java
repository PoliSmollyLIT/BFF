package com.tinqin.academy.api.invoice;

import com.tinqin.academy.api.base.OperationProcessor;

public interface GenerateInvoiceOperation extends OperationProcessor<InvoiceResponse, InvoiceRequest> {
    InvoiceResponse process(InvoiceRequest request);
}
