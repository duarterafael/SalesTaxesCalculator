package com.br.liferay.sales.taxes.calculator.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.br.liferay.sales.taxes.calculator.utils.Constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "ProductTypeDTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductTypeDTO{

	@NotNull(message = "{validation.product.type.name.notnull}")
    @Length(min = Constants.PRODUCT_TYPE_NAME_MIN_LENGTH, max = Constants.PRODUCT_TYPE_NAME_MAX_LENGTH, message = "{validation.model.length}")
	@ApiModelProperty(value = "Product name")
	private String name;

	@DecimalMin(value=Constants.PRODUCT_TYPE_TAX_MIN_LENGTH, inclusive=true, message= "{validation.product.type.tax.length}")
	@DecimalMax(value=Constants.PRODUCT_TYPE_TAX_MAX_LENGTH, inclusive=true, message= "{validation.product.type.tax.length}")
	@NotNull(message = "{validation.product.type.tax.notnull}")
	@ApiModelProperty(value = "Tax percentage")
	private Double tax;

}
