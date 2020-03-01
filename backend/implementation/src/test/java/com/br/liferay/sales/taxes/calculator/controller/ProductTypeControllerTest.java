package com.br.liferay.sales.taxes.calculator.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.br.liferay.sales.taxes.calculator.controller.impl.ProductTypeCRUDControllerImpl;
import com.br.liferay.sales.taxes.calculator.model.ProductType;
import com.br.liferay.sales.taxes.calculator.service.ICRUDService;
import com.br.liferay.sales.taxes.calculator.utils.Constants;
import com.br.liferay.sales.taxes.calculator.utils.UnitTestUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductTypeCRUDControllerImpl.class)
public class ProductTypeControllerTest extends AbstractControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ICRUDService<ProductType, ProductType> iProductTypeService;
	
	private ProductType createProductType() {
		ProductType productTypeMock = new ProductType();
        productTypeMock.setName("Book");
        productTypeMock.setTax(UnitTestUtils.randomDouble(0, 100, 2));
        return productTypeMock;
	}
	
	@Test
    public void insertSucess() throws Exception{
        
		ProductType productTypeMock = createProductType();
		
        Mockito.when(iProductTypeService.insert(Mockito
                .any(ProductType.class)))
                .thenReturn(productTypeMock);

        mockMvc.perform(MockMvcRequestBuilders
                .post(Constants.PRODUCT_TYPE_URL)
                .content(super.asJsonString(productTypeMock))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
	
	@Test
    public void insertNameIsNull() throws Exception{
        ProductType productTypeMock = createProductType();
        productTypeMock.setName(null);
        
        Mockito.when(iProductTypeService.insert(Mockito
                .any(ProductType.class)))
                .thenReturn(productTypeMock);

        mockMvc.perform(MockMvcRequestBuilders
                .post(Constants.PRODUCT_TYPE_URL)
                .content(super.asJsonString(productTypeMock))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
	
	@Test
    public void insertNameLessThanMinimumValue() throws Exception{
        ProductType productTypeMock = createProductType();
        productTypeMock.setName(RandomStringUtils.randomAlphanumeric(Constants.PRODUCT_TYPE_NAME_MIN_LENGTH-1));
        Mockito.when(iProductTypeService.insert(Mockito
                .any(ProductType.class)))
                .thenReturn(productTypeMock);

        mockMvc.perform(MockMvcRequestBuilders
                .post(Constants.PRODUCT_TYPE_URL)
                .content(super.asJsonString(productTypeMock))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
	
	@Test
    public void insertNameLessThanMaximumValue() throws Exception{
        ProductType productTypeMock = createProductType();
        productTypeMock.setName(RandomStringUtils.randomAlphanumeric(Constants.PRODUCT_TYPE_NAME_MAX_LENGTH+1));
        Mockito.when(iProductTypeService.insert(Mockito
                .any(ProductType.class)))
                .thenReturn(productTypeMock);

        mockMvc.perform(MockMvcRequestBuilders
                .post(Constants.PRODUCT_TYPE_URL)
                .content(super.asJsonString(productTypeMock))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
	
	@Test
    public void insertTaxLessThanMinimumValue() throws Exception{
        ProductType productTypeMock = createProductType();
        productTypeMock.setTax(-0.01);
        Mockito.when(iProductTypeService.insert(Mockito
                .any(ProductType.class)))
                .thenReturn(productTypeMock);

        mockMvc.perform(MockMvcRequestBuilders
                .post(Constants.PRODUCT_TYPE_URL)
                .content(super.asJsonString(productTypeMock))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
	
	@Test
    public void insertTaxLessThanMaximumValue() throws Exception{
        ProductType productTypeMock = createProductType();
        productTypeMock.setTax(100.01);
        Mockito.when(iProductTypeService.insert(Mockito
                .any(ProductType.class)))
                .thenReturn(productTypeMock);

        mockMvc.perform(MockMvcRequestBuilders
                .post(Constants.PRODUCT_TYPE_URL)
                .content(super.asJsonString(productTypeMock))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
	
	@Test
    public void insertTaxIsNull() throws Exception{
        ProductType productTypeMock = createProductType();
        productTypeMock.setTax(null);
        
        Mockito.when(iProductTypeService.insert(Mockito
                .any(ProductType.class)))
                .thenReturn(productTypeMock);

        mockMvc.perform(MockMvcRequestBuilders
                .post(Constants.PRODUCT_TYPE_URL)
                .content(super.asJsonString(productTypeMock))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
	
	 

}
