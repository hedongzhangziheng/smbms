package club.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;

@Data
public class Bill {

  private Long id;
  private String billCode;
  private String productName;
  private String productDesc;
  private String productUnit;
  private Double productCount;
  private Double totalPrice;
  private Long isPayment;
  private Long createdBy;
  private Date creationDate;
  private Long modifyBy;
  private Date modifyDate;
  private Long providerId;

  @TableField(exist = false)
  private Provider provider;

}
