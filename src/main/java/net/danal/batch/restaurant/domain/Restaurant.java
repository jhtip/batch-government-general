package net.danal.batch.restaurant.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "government_general_restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long number;
    private String open_service_name;
    private String open_service_id;
    private String local_government_code;
    private String management_number;
    private String permit_date;
    private String permit_cancellation_date;
    private String business_status_code;
    private String business_status_name;
    private String detailed_business_status_code;
    private String detailed_business_status_name;
    private String closure_date;
    private String suspension_start_date;
    private String suspension_end_date;
    private String reopening_date;
    private String location_phone;
    private String location_area;
    private String location_postal_code;
    private String location_full_address;
    private String road_name_full_address;
    private String road_name_postal_code;
    private String business_name;
    private String last_modified_time;
    private String data_update_type;
    private String data_update_date;
    private String business_type_name;
    private String coordinate_x;
    private String coordinate_y;
    private String hygiene_type_name;
    private Integer male_employee_count;
    private Integer female_employee_count;
    private String business_surrounding_type_name;
    private String grade_type_name;
    private String water_supply_facility_type_name;
    private Integer total_employee_count;
    private Integer headquarters_employee_count;
    private Integer factory_office_staff_count;
    private Integer factory_sales_staff_count;
    private Integer factory_production_staff_count;
    private String building_ownership_type_name;
    private Long guarantee_amount;
    private Long monthly_rent_amount;
    private String multi_use_facility_yn;
    private BigDecimal total_facility_size;
    private String traditional_business_designation_number;
    private String traditional_business_main_food;
    private String website;
}