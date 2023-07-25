package com.tinqin.academy.api.item.get;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetItemTagResponse {
    @NotBlank
    private Long id;
    @NotBlank
    private String title;
}
