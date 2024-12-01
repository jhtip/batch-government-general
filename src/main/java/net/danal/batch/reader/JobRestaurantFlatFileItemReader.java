package net.danal.batch.reader;

import lombok.extern.slf4j.Slf4j;
import net.danal.batch.restaurant.domain.RestaurantDto;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.math.BigDecimal;

@Slf4j
@Configuration
public class JobRestaurantFlatFileItemReader {

    public static final String ENCODING = "UTF-8";

    public FlatFileItemReader<RestaurantDto> csvFlatFileItemReader() throws Exception {
        DefaultLineMapper<RestaurantDto> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = getDelimitedLineTokenizer();

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSet -> {
            Long number = fieldSet.readLong("number");
            String openServiceName = fieldSet.readString("open_service_name");
            String openServiceId = fieldSet.readString("open_service_id");
            String localGovernmentCode = fieldSet.readString("local_government_code");
            String managementNumber = fieldSet.readString("management_number");
            String permitDate = fieldSet.readString("permit_date");
            String permitCancellationDate = fieldSet.readString("permit_cancellation_date");
            String businessStatusCode = fieldSet.readString("business_status_code");
            String businessStatusName = fieldSet.readString("business_status_name");
            String detailedBusinessStatusCode = fieldSet.readString("detailed_business_status_code");
            String detailedBusinessStatusName = fieldSet.readString("detailed_business_status_name");
            String closureDate = fieldSet.readString("closure_date");
            String suspensionStartDate = fieldSet.readString("suspension_start_date");
            String suspensionEndDate = fieldSet.readString("suspension_end_date");
            String reopeningDate = fieldSet.readString("reopening_date");
            String locationPhone = fieldSet.readString("location_phone");
            String locationArea = fieldSet.readString("location_area");
            String locationPostalCode = fieldSet.readString("location_postal_code");
            String locationFullAddress = fieldSet.readString("location_full_address");
            String roadNameFullAddress = fieldSet.readString("road_name_full_address");
            String roadNamePostalCode = fieldSet.readString("road_name_postal_code");
            String businessName = fieldSet.readString("business_name");
            String lastModifiedTime = fieldSet.readString("last_modified_time");
            String dataUpdateType = fieldSet.readString("data_update_type");
            String dataUpdateDate = fieldSet.readString("data_update_date");
            String businessTypeName = fieldSet.readString("business_type_name");
            String coordinateX = fieldSet.readString("coordinate_x");
            String coordinateY = fieldSet.readString("coordinate_y");
            String hygieneTypeName = fieldSet.readString("hygiene_type_name");
            Integer maleEmployeeCount = fieldSet.readInt("male_employee_count");
            Integer femaleEmployeeCount = fieldSet.readInt("female_employee_count");
            String businessSurroundingTypeName = fieldSet.readString("business_surrounding_type_name");
            String gradeTypeName = fieldSet.readString("grade_type_name");
            String waterSupplyFacilityTypeName = fieldSet.readString("water_supply_facility_type_name");
            Integer totalEmployeeCount = fieldSet.readInt("total_employee_count");
            Integer headquartersEmployeeCount = fieldSet.readInt("headquarters_employee_count");
            Integer factoryOfficeStaffCount = fieldSet.readInt("factory_office_staff_count");
            Integer factorySalesStaffCount = fieldSet.readInt("factory_sales_staff_count");
            Integer factoryProductionStaffCount = fieldSet.readInt("factory_production_staff_count");
            String buildingOwnershipTypeName = fieldSet.readString("building_ownership_type_name");
            Long guaranteeAmount = fieldSet.readLong("guarantee_amount");
            Long monthlyRentAmount = fieldSet.readLong("monthly_rent_amount");
            String multiUseFacilityYn = fieldSet.readString("multi_use_facility_yn");
            BigDecimal totalFacilitySize = fieldSet.readBigDecimal("total_facility_size");
            String traditionalBusinessDesignationNumber = fieldSet.readString("traditional_business_designation_number");
            String traditionalBusinessMainFood = fieldSet.readString("traditional_business_main_food");
            String website = fieldSet.readString("website");

            return new RestaurantDto(number, openServiceName, openServiceId, localGovernmentCode, managementNumber, permitDate, permitCancellationDate, businessStatusCode, businessStatusName, detailedBusinessStatusCode, detailedBusinessStatusName, closureDate, suspensionStartDate, suspensionEndDate, reopeningDate, locationPhone, locationArea, locationPostalCode, locationFullAddress, roadNameFullAddress, roadNamePostalCode, businessName, lastModifiedTime, dataUpdateType, dataUpdateDate, businessTypeName, coordinateX, coordinateY, hygieneTypeName, maleEmployeeCount, femaleEmployeeCount, businessSurroundingTypeName, gradeTypeName, waterSupplyFacilityTypeName, totalEmployeeCount, headquartersEmployeeCount, factoryOfficeStaffCount, factorySalesStaffCount, factoryProductionStaffCount, buildingOwnershipTypeName, guaranteeAmount, monthlyRentAmount, multiUseFacilityYn, totalFacilitySize, traditionalBusinessDesignationNumber, traditionalBusinessMainFood, website);
        });

        FlatFileItemReader<RestaurantDto> reader =
                new FlatFileItemReaderBuilder<RestaurantDto>()
                        .name("csvFlatFileItemReader")
                        .resource(new ClassPathResource("static/government_general_restaurant_100_ko.csv"))
                        //.resource(new ClassPathResource("static/government_general_restaurant_all_utf8.csv"))
                        .encoding(ENCODING)
                        .linesToSkip(1)
                        .lineMapper(lineMapper)
                        .build();

        reader.afterPropertiesSet();
        return reader;
    }

    private static DelimitedLineTokenizer getDelimitedLineTokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("number", "open_service_name", "open_service_id", "local_government_code", "management_number", "permit_date", "permit_cancellation_date", "business_status_code", "business_status_name", "detailed_business_status_code", "detailed_business_status_name", "closure_date", "suspension_start_date", "suspension_end_date", "reopening_date", "location_phone", "location_area", "location_postal_code", "location_full_address", "road_name_full_address", "road_name_postal_code", "business_name", "last_modified_time", "data_update_type", "data_update_date", "business_type_name", "coordinate_x", "coordinate_y", "hygiene_type_name", "male_employee_count", "female_employee_count", "business_surrounding_type_name", "grade_type_name", "water_supply_facility_type_name", "total_employee_count", "headquarters_employee_count", "factory_office_staff_count", "factory_sales_staff_count", "factory_production_staff_count", "building_ownership_type_name", "guarantee_amount", "monthly_rent_amount", "multi_use_facility_yn", "total_facility_size", "traditional_business_designation_number", "traditional_business_main_food", "website");
        return tokenizer;
    }

}
