package com.tinqin.academy.api.item.get;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetItemVendorResponse {
    @NotBlank
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
}
