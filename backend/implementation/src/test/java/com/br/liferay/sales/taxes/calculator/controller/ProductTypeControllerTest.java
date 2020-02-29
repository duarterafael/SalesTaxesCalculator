package com.br.liferay.sales.taxes.calculator.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.br.liferay.sales.taxes.calculator.model.ProductType;
import com.br.liferay.sales.taxes.calculator.service.IProductTypeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductTypeController.class)
public class ProductTypeControllerTest extends AbstractControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IProductTypeService iProductTypeService;
	
	private ProductType createProductType() {
		ProductType productTypeMock = new ProductType();
        productTypeMock.setName("Book");
        productTypeMock.setTax(20.99);
        return productTypeMock;
	}
	
	@Test
    public void insertSucess() throws Exception{
        
		ProductType productTypeMock = createProductType();
		
        Mockito.when(iProductTypeService.insert(Mockito
                .any(ProductType.class)))
                .thenReturn(productTypeMock);

        mockMvc.perform(MockMvcRequestBuilders
                .post(super.BASE_URL+"product/types")
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
                .post(super.BASE_URL+"product/types")
                .content(super.asJsonString(productTypeMock))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
	
	 

}
