package com.inventory.rootPackage.ReportModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Report {
	private String itemName;
	private int totalStock;
	private int sold;

}
