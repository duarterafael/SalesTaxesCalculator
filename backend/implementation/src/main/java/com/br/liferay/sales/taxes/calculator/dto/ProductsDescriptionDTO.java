package com.br.liferay.sales.taxes.calculator.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "CalculatorSalesTaxesDTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductsDescriptionDTO {
	@NotNull(message = "{validation.product.name.notnull}")
	@ApiModelProperty(value = "Products Descriptions")
	private List<String> productsDescriptions;
}
