package club.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Address {

  private Long id;
  private String contact;
  private String addressDesc;
  private String postCode;
  private String tel;
  private Long createdBy;
  private Date creationDate;
  private Long modifyBy;
  private Date modifyDate;
  private Long userId;

}
