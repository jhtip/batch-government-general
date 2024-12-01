package net.danal.batch.restaurant.domain;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class RestaurantDto {

    private Long number;
    private String openServiceName;
    private String openServiceId;
    private String localGovernmentCode;
    private String managementNumber;
    private String permitDate;
    private String permitCancellationDate;
    private String businessStatusCode;
    private String businessStatusName;
    private String detailedBusinessStatusCode;
    private String detailedBusinessStatusName;
    private String closureDate;
    private String suspensionStartDate;
    private String suspensionEndDate;
    private String reopeningDate;
    private String locationPhone;
    private String locationArea;
    private String locationPostalCode;
    private String locationFullAddress;
    private String roadNameFullAddress;
    private String roadNamePostalCode;
    private String businessName;
    private String lastModifiedTime;
    private String dataUpdateType;
    private String dataUpdateDate;
    private String businessTypeName;
    private String coordinateX;
    private String coordinateY;
    private String hygieneTypeName;
    private Integer maleEmployeeCount;
    private Integer femaleEmployeeCount;
    private String businessSurroundingTypeName;
    private String gradeTypeName;
    private String waterSupplyFacilityTypeName;
    private Integer totalEmployeeCount;
    private Integer headquartersEmployeeCount;
    private Integer factoryOfficeStaffCount;
    private Integer factorySalesStaffCount;
    private Integer factoryProductionStaffCount;
    private String buildingOwnershipTypeName;
    private Long guaranteeAmount;
    private Long monthlyRentAmount;
    private String multiUseFacilityYn;
    private BigDecimal totalFacilitySize;
    private String traditionalBusinessDesignationNumber;
    private String traditionalBusinessMainFood;
    private String website;
}