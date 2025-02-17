package io.rsg.cqrs;

import io.rsg.cqrs.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CqrsApplicationTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void getOrderById() throws Exception {

        String expectedContent = "{\"orderId\":\"ORD123458\",\"customer\":{\"customerId\":\"CUST78910\",\"name\":\"Sedat Goc\",\"email\":\"rsedatgoc@outlook.com\",\"phone\":\"+900000000\",\"address\":{\"street\":\"İstiklal Caddesi No: 10\",\"city\":\"İstanbul\",\"postalCode\":\"34000\",\"country\":\"Türkiye\"}},\"items\":[{\"productId\":\"PROD001\",\"name\":\"Kablosuz Kulaklık\",\"quantity\":1,\"price\":799.99,\"currency\":\"TRY\"},{\"productId\":\"PROD002\",\"name\":\"Mekanik Klavye\",\"quantity\":1,\"price\":1299.5,\"currency\":\"TRY\"}],\"totalAmount\":2099.49,\"currency\":\"TRY\",\"payment\":{\"method\":\"Credit Card\",\"transactionId\":\"TXN987654321\",\"status\":\"Pending\"},\"status\":\"SHIPPED\",\"orderDate\":\"2025-02-16T14:30:00Z\",\"deliveryDate\":\"2025-02-20T18:00:00Z\"}";

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/order/ORD123458")).andExpect(status().isOk()).andReturn();

        Assertions.assertEquals(result.getResponse().getContentAsString(), expectedContent);

    }

}
