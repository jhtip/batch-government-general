package net.danal.batch.restaurant.repository;

import lombok.RequiredArgsConstructor;
import net.danal.batch.restaurant.domain.RestaurantDto;
import org.springframework.batch.item.Chunk;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void write(Chunk<? extends RestaurantDto> chunk) {
        List<? extends RestaurantDto> items = chunk.getItems();

        jdbcTemplate.batchUpdate("INSERT INTO government_general_restaurant (number,open_service_name,open_service_id,local_government_code,management_number,permit_date,permit_cancellation_date,business_status_code,business_status_name,detailed_business_status_code,detailed_business_status_name,closure_date,suspension_start_date,suspension_end_date,reopening_date,location_phone,location_area,location_postal_code,location_full_address,road_name_full_address,road_name_postal_code,business_name,last_modified_time,data_update_type,data_update_date,business_type_name,coordinate_x,coordinate_y,hygiene_type_name,male_employee_count,female_employee_count,business_surrounding_type_name,grade_type_name,water_supply_facility_type_name,total_employee_count,headquarters_employee_count,factory_office_staff_count,factory_sales_staff_count,factory_production_staff_count,building_ownership_type_name,guarantee_amount,monthly_rent_amount,multi_use_facility_yn,total_facility_size,traditional_business_designation_number,traditional_business_main_food,website) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        RestaurantDto restaurantDto = items.get(i);
                        ps.setLong(1, restaurantDto.getNumber());
                        ps.setString(2, restaurantDto.getOpenServiceName());
                        ps.setString(3, restaurantDto.getOpenServiceId());
                        ps.setString(4, restaurantDto.getLocalGovernmentCode());
                        ps.setString(5, restaurantDto.getManagementNumber());
                        ps.setString(6, restaurantDto.getPermitDate());
                        ps.setString(7, restaurantDto.getPermitCancellationDate());
                        ps.setString(8, restaurantDto.getBusinessStatusCode());
                        ps.setString(9, restaurantDto.getBusinessStatusName());
                        ps.setString(10, restaurantDto.getDetailedBusinessStatusCode());
                        ps.setString(11, restaurantDto.getDetailedBusinessStatusName());
                        ps.setString(12, restaurantDto.getClosureDate());
                        ps.setString(13, restaurantDto.getSuspensionStartDate());
                        ps.setString(14, restaurantDto.getSuspensionEndDate());
                        ps.setString(15, restaurantDto.getReopeningDate());
                        ps.setString(16, restaurantDto.getLocationPhone());
                        ps.setString(17, restaurantDto.getLocationArea());
                        ps.setString(18, restaurantDto.getLocationPostalCode());
                        ps.setString(19, restaurantDto.getLocationFullAddress());
                        ps.setString(20, restaurantDto.getRoadNameFullAddress());
                        ps.setString(21, restaurantDto.getRoadNamePostalCode());
                        ps.setString(22, restaurantDto.getBusinessName());
                        ps.setString(23, restaurantDto.getLastModifiedTime());
                        ps.setString(24, restaurantDto.getDataUpdateType());
                        ps.setString(25, restaurantDto.getDataUpdateDate());
                        ps.setString(26, restaurantDto.getBusinessTypeName());
                        ps.setString(27, restaurantDto.getCoordinateX());
                        ps.setString(28, restaurantDto.getCoordinateY());
                        ps.setString(29, restaurantDto.getHygieneTypeName());
                        ps.setInt(30, restaurantDto.getMaleEmployeeCount());
                        ps.setInt(31, restaurantDto.getFemaleEmployeeCount());
                        ps.setString(32, restaurantDto.getBusinessSurroundingTypeName());
                        ps.setString(33, restaurantDto.getGradeTypeName());
                        ps.setString(34, restaurantDto.getWaterSupplyFacilityTypeName());
                        ps.setInt(35, restaurantDto.getTotalEmployeeCount());
                        ps.setInt(36, restaurantDto.getHeadquartersEmployeeCount());
                        ps.setInt(37, restaurantDto.getFactoryOfficeStaffCount());
                        ps.setInt(38, restaurantDto.getFactorySalesStaffCount());
                        ps.setInt(39, restaurantDto.getFactoryProductionStaffCount());
                        ps.setString(40, restaurantDto.getBuildingOwnershipTypeName());
                        ps.setLong(41, restaurantDto.getGuaranteeAmount());
                        ps.setLong(42, restaurantDto.getMonthlyRentAmount());
                        ps.setString(43, restaurantDto.getMultiUseFacilityYn());
                        ps.setBigDecimal(44, restaurantDto.getTotalFacilitySize());
                        ps.setString(45, restaurantDto.getTraditionalBusinessDesignationNumber());
                        ps.setString(46, restaurantDto.getTraditionalBusinessMainFood());
                        ps.setString(47, restaurantDto.getWebsite());
                    }

                    @Override
                    public int getBatchSize() {
                        return items.size();
                    }
                });
    }

}
