package egecoskun121.com.crm.model.mapper;

import egecoskun121.com.crm.model.DTO.ProductInquiryDTO;
import egecoskun121.com.crm.model.entity.ProductInquiry;
import org.mapstruct.Mapper;

@Mapper
public interface ProductInquiryMapper {
    ProductInquiryDTO toProductInquiryDTO(ProductInquiry productInquiry);

    ProductInquiry toProductInquiry(ProductInquiryDTO productInquiryDTO);
}
