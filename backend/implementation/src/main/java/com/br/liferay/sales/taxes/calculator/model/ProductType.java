package com.br.liferay.sales.taxes.calculator.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.br.liferay.sales.taxes.calculator.utils.Constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Product Type")
public class ProductType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(value = "Product Type Id")
    private Long id;

    @Column(length = Constants.PRODUCT_TYPE_NAME_MAX_LENGTH, nullable = false, unique = true)
    @ApiModelProperty(value = "Product name")
	private String name;

	@Column(nullable = false)
	@ApiModelProperty(value = "Tax percentage")
	private Double tax;
	
	 @OneToMany(cascade = CascadeType.ALL,
	            fetch = FetchType.EAGER,
	            mappedBy = "productType")
    private List<Product> products;
    
}