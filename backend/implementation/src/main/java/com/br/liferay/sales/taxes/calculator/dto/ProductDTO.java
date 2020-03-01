package com.br.liferay.sales.taxes.calculator.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.br.liferay.sales.taxes.calculator.model.ProductType;
import com.br.liferay.sales.taxes.calculator.utils.Constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "ProductDTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO{

	@NotNull(message = "{validation.product.name.notnull}")
    @Length(min = Constants.PRODUCT_NAME_MIN_LENGTH, max = Constants.PRODUCT_NAME_MAX_LENGTH, message = "{validation.product.name.length}")
	@ApiModelProperty(value = "Product name")
	private String name;
	
	@NotNull(message = "{validation.product.type.id.notnull}")
	@Min(value = Constants.ID_MIN_VALUE, message = "{validation.product.type.id.min.value}")
	@ApiModelProperty(value = "Product type name")
	private Long productTypeId;

}
