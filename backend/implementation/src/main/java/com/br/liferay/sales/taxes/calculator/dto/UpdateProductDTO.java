package com.br.liferay.sales.taxes.calculator.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.br.liferay.sales.taxes.calculator.utils.Constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "UpdateProductDTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateProductDTO{

	@NotNull(message = "{validation.product.id.notnull}")
    @Min(value = Constants.ID_MIN_VALUE, message = "{validation.product.type.id.min.value}")
	@ApiModelProperty(value = "Product id")
	private Long id;
}
