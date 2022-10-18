package egecoskun121.com.crm.model.mapper;

import egecoskun121.com.crm.model.DTO.ProductDTO;
import egecoskun121.com.crm.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);

}
