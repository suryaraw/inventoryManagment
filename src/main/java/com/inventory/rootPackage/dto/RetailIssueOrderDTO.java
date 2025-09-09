package com.inventory.rootPackage.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailIssueOrderDTO {
    private Long id;
    private String issueNumber;
    private String issueDate;
    private String status;

    private String retailerName;
    private String shopName;
    private String itemName;

    private Integer quantity;
}
